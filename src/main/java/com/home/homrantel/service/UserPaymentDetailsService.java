package com.home.homrantel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.home.homrantel.entities.UserPaymentDetails;
import com.home.homrantel.repo.UserPaymentDetailsRepository;

import java.util.List;

@Service
public class UserPaymentDetailsService {
    
    @Autowired
    private UserPaymentDetailsRepository repository;

    public List<UserPaymentDetails> getAllUserPaymentDetails() {
        return repository.findAll();
    }
}

