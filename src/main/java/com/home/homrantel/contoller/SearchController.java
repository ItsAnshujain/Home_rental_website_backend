package com.home.homrantel.contoller;

import com.home.homrantel.entities.housedata;
import com.home.homrantel.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchController {

    @Autowired
    private SearchService searchService;

    @GetMapping("/search")
    public ResponseEntity<Page<housedata>> findHouseByCityAndPrice(
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String minPrice,
            @RequestParam(required = false) String maxPrice,
            @RequestParam(value = "pageNumber",defaultValue = "10", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize",defaultValue = "1", required = false) Integer pageSize ){
            
        try {
            Page<housedata> houses = searchService.getAllHousesByCityAndPrice(city, minPrice, maxPrice, pageNumber, pageSize);
            if (houses.isEmpty()) {
                return ResponseEntity.noContent().build(); // Return 204 No Content if no houses found
            }
            return ResponseEntity.ok(houses);
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Handle internal server error
        }
    }
}
