package com.home.homrantel.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.home.homrantel.entities.UserPaymentDetails;
import com.home.homrantel.service.UserPaymentDetailsService;

import java.util.List;

@RestController
@RequestMapping("/api/payment")
public class UserPaymentDetailsController {

    @Autowired
    private UserPaymentDetailsService service;

    @GetMapping("/allUser")
    public List<UserPaymentDetails> getAllUserPaymentDetails() {
        return service.getAllUserPaymentDetails();
    }
}
