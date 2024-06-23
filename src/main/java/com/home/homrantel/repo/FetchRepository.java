package com.home.homrantel.repo;

import java.util.List;

import com.home.homrantel.entities.housedata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FetchRepository extends JpaRepository<housedata, Integer> {
    List<housedata> findHouseByHouseId(Integer houseId);
}
