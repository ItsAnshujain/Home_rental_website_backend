package com.home.homrantel.service;

import com.home.homrantel.Exception.FetchException;
import com.home.homrantel.entities.housedata;

public interface FetchService {
    public housedata findHouseById(Integer id) throws FetchException;

}
