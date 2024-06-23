package com.home.homrantel.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.home.homrantel.entities.UserPaymentDetails;

public interface UserPaymentDetailsRepository extends JpaRepository<UserPaymentDetails, Long> {

}
