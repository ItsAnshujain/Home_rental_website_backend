package com.home.homrantel.contoller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.home.homrantel.Exception.FetchException;
import com.home.homrantel.Exception.UserException;
import com.home.homrantel.Response.ApiResponse;
import com.home.homrantel.Response.PaymentLinkResponse;
import com.home.homrantel.entities.User;
import com.home.homrantel.entities.UserPaymentDetails;
import com.home.homrantel.entities.housedata;
import com.home.homrantel.repo.HouseRepository;
import com.home.homrantel.repo.UserPaymentRepository;
import com.home.homrantel.service.FetchService;
import com.home.homrantel.service.FetchServiceImpl;
import com.home.homrantel.service.UserServiceImplementation;
import com.razorpay.Payment;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api")
public class PaymentController {
    @Value("${razorpay.api.key}")
    private String apiKey;

    @Value("${razorpay.api.secret}")
    private String apiSecret;

    private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);
    @Autowired
    private FetchService fetchService;

    @Autowired
    private RazorpayClient razorpayClient;

    @Autowired
    private FetchServiceImpl fetchServiceImpl;

    @Autowired
    private UserPaymentRepository userPaymentRepository;

    @Autowired
    private UserServiceImplementation userServiceImplementation;

    @Autowired
    private HouseRepository houseRepository;

    @PostMapping("/payments/{houseId}")
    public ResponseEntity<PaymentLinkResponse> createPaymentLink(@PathVariable Integer houseId,
            @RequestParam Long userId,
            @RequestHeader("Authorization") String jwt) throws FetchException, UserException {

        try {
            housedata house = fetchService.findHouseById(houseId);
            User user = userServiceImplementation.findUserById(userId);

            JSONObject paymentLinkRequest = new JSONObject();
            String priceString = house.getPrice();
            // Remove any non-numeric characters from the price string
            priceString = priceString.replaceAll("[^\\d]", "");
            // Convert the string to a numeric value
            int originalAmount = Integer.parseInt(priceString);
            // Calculate 30% of the original amount
            int amount = (int) (originalAmount * 0.3); // 30% of the price

            paymentLinkRequest.put("amount", amount * 100);
            paymentLinkRequest.put("currency", "INR");

            JSONObject notify = new JSONObject();
            notify.put("sms", true);
            notify.put("email", true);
            paymentLinkRequest.put("notify", notify);

            paymentLinkRequest.put("callback_url", "http://localhost:3000/payment/" + houseId + "/" + userId);
            paymentLinkRequest.put("callback_method", "get");

            PaymentLink payment = razorpayClient.paymentLink.create(paymentLinkRequest);

            String paymentLinkId = payment.get("id");
            String paymentLinkUrl = payment.get("short_url");

            PaymentLinkResponse res = new PaymentLinkResponse();
            res.setPayment_link_id(paymentLinkId);
            res.setPayment_link_url(paymentLinkUrl);
            return new ResponseEntity<>(res, HttpStatus.CREATED);

        } catch (FetchException | RazorpayException e) {
            logger.error("Error creating payment link for house ID " + houseId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new PaymentLinkResponse());
        }
    }

    @GetMapping("/payments")
    public ResponseEntity<ApiResponse> redirect(@RequestParam(name = "payment_id") String paymentId,
                                                @RequestParam(name = "houseId") Integer houseId,
                                                @RequestParam(name = "userId") Long userId) {
        try {
            housedata house = fetchServiceImpl.findHouseById(houseId);
            User user = userServiceImplementation.findUserById(userId);
            Payment payment = razorpayClient.payments.fetch(paymentId);
            if (payment.get("status").equals("captured")) {
                // Check if payment details already exist
                boolean exists = userPaymentRepository.existsByPaymentId(paymentId);
                if (exists) {
                    // Payment details already exist, no need to save again
                    ApiResponse res = new ApiResponse();
                    res.setMessage("Payment details already exist.");
                    res.setStatus(true);
                    return new ResponseEntity<>(res, HttpStatus.ACCEPTED);
                }

                UserPaymentDetails userPaymentDetails = new UserPaymentDetails(); // Initialize userPaymentDetails

                userPaymentDetails.setFirstName(user.getFirstName());
                userPaymentDetails.setLastName(user.getLastName());
                userPaymentDetails.setEmail(user.getEmail());
                userPaymentDetails.setPaymentId(paymentId);
                userPaymentDetails.setAreaType(house.getAreaType());
                userPaymentDetails.setBath(house.getBath());
                userPaymentDetails.setLocation(house.getLocation());
                userPaymentDetails.setPrice(house.getPrice());
                userPaymentDetails.setSize(house.getSize());
                userPaymentDetails.setSquareFeet(house.getSquareFeet());
                userPaymentDetails.setTitle(house.getTitle());
                userPaymentDetails.setStatus("COMPLETED");
                house.setAvailable("Not Available");

                userPaymentRepository.save(userPaymentDetails); // Use userPaymentRepository to save
                houseRepository.save(house);
                ApiResponse res = new ApiResponse();
                res.setMessage("Your house booked successfully.");
                res.setStatus(true);
                return new ResponseEntity<>(res, HttpStatus.ACCEPTED);
            } else {
                // Handle other payment statuses
                ApiResponse res = new ApiResponse();
                res.setMessage("Payment not captured.");
                res.setStatus(false);
                return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
            }
        } catch (RazorpayException e) {
            logger.error("Error processing payment for house ID " + houseId, e);
            ApiResponse res = new ApiResponse();
            res.setMessage("Error processing payment.");
            res.setStatus(false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(res);
        } catch (Exception e) {
            logger.error("Error saving payment details for house ID " + houseId, e);
            ApiResponse res = new ApiResponse();
            res.setMessage("Error saving payment details.");
            res.setStatus(false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(res);
        }
    }

}
