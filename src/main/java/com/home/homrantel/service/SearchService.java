package com.home.homrantel.service;

import com.home.homrantel.entities.housedata;
import com.home.homrantel.repo.HouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
@Service
public class SearchService {

    @Autowired
    private HouseRepository houseRepository;

    public Page<housedata> getAllHousesByCityAndPrice(String city, String minPrice, String maxPrice, Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        if (city != null && minPrice != null && maxPrice != null) {
            // Implement logic to search houses by location and price
            return houseRepository.findByCityAndPriceBetween(city, minPrice, maxPrice, pageable);
        } else if (city != null) {
            // Implement logic to search houses by location only
            return houseRepository.findByCity(city, pageable);
        } else if (minPrice != null && maxPrice != null) {
            // Implement logic to search houses by price only
            return houseRepository.findByPriceBetween(minPrice, maxPrice, pageable);
        } else {
            // Implement default logic if no search criteria provided
            return houseRepository.findAll(pageable);
        }
    }
}
