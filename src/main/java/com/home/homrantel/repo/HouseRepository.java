package com.home.homrantel.repo;

import com.home.homrantel.entities.housedata;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HouseRepository extends JpaRepository<housedata, Integer> {

    Page<housedata> findByCityAndPriceBetween(String city, String minPrice, String maxPrice, Pageable pageable);

    Page<housedata> findByCity(String city, Pageable pageable);

    Page<housedata> findByPriceBetween(String minPrice, String maxPrice, Pageable pageable);


}
