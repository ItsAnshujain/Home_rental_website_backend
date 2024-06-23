package com.home.homrantel.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.home.homrantel.entities.UserPaymentDetails;

public interface UserPaymentRepository  extends JpaRepository<UserPaymentDetails, Integer>{
    boolean existsByPaymentId(String paymentId);
}
