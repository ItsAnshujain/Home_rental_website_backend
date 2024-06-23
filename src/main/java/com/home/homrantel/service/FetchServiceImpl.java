package com.home.homrantel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.home.homrantel.Exception.FetchException;
import com.home.homrantel.entities.housedata;
import com.home.homrantel.repo.FetchRepository;

import java.util.List;

@Service
public class FetchServiceImpl implements FetchService {

    @Autowired
    private FetchRepository fetchRepository;

    @Override
    public housedata findHouseById(Integer houseId) throws FetchException {
        List<housedata> houses = fetchRepository.findHouseByHouseId(houseId);
        if (!houses.isEmpty()) {
            return houses.get(0); 
        }
        throw new FetchException("House not found with this id - " + houseId);
    }
}
