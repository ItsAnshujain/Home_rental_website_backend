package com.home.homrantel.service;

import com.home.homrantel.Exception.UserException;
import com.home.homrantel.entities.User;

public interface UserService {
    public User findUserById(Long userId) throws UserException;

    public User findUserProfileByJwt(String jwt) throws UserException;
}
