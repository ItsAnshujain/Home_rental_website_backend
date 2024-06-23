package com.home.homrantel.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.home.homrantel.Exception.FetchException;
import com.home.homrantel.Exception.UserException;
import com.home.homrantel.entities.User;
import com.home.homrantel.entities.housedata;
import com.home.homrantel.service.FetchService;
import com.home.homrantel.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private FetchService fetchService;

    @GetMapping("/profile")
    public ResponseEntity<User> getUserProfileHandler(@RequestHeader("Authorization") String jwt) throws UserException {
        User user = userService.findUserProfileByJwt(jwt);
        return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
    }

    @GetMapping("/fetch/{houseId}")
    public ResponseEntity<housedata> findHouseByIdHandler(@PathVariable Integer houseId) throws FetchException {
        housedata house = fetchService.findHouseById(houseId);
        return new ResponseEntity<housedata>(house, HttpStatus.ACCEPTED);
    }
}
