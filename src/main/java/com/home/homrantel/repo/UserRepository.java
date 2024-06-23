package com.home.homrantel.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.home.homrantel.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findByEmail(String email);
}