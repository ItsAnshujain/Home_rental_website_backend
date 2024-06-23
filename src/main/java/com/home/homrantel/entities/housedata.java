package com.home.homrantel.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class housedata {
    @Id

    @Column(name = "houseid")
    private Integer houseId;

    @Column(name = "title")
    private String title;

    @Column(name = "location")
    private String location;

    @Column(name = "address")
    private String address;

    @Column(name = "area_type")
    private String areaType;

    @Column(name = "price")
    private String price;

    @Column(name = "sqrt")
    private String squareFeet;

    @Column(name = "size")
    private String size;

    @Column(name = "bath")
    private String bath;

    @Column(name = "rating")
    private String rating;

    @Column(name = "laltitude")
    private String latitude;

    @Column(name = "longitude")
    private String longitude;

    @Column(name = "available")
    private String available;

    @Column(name = "images")
    private String images;

    @Column(name = "city")
    private String city;

    public String getCity() {
        return city;
    }

    public housedata(String city) {
        this.city = city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAreaType() {
        return areaType;
    }

    public void setAreaType(String areaType) {
        this.areaType = areaType;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSquareFeet() {
        return squareFeet;
    }

    public void setSquareFeet(String squareFeet) {
        this.squareFeet = squareFeet;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getBath() {
        return bath;
    }

    public void setBath(String bath) {
        this.bath = bath;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public housedata(Integer houseId, String title, String location, String address, String areaType, String price,
            String squareFeet, String size, String bath, String rating, String latitude, String longitude,
            String available, String images) {
        this.houseId = houseId;
        this.title = title;
        this.location = location;
        this.address = address;
        this.areaType = areaType;
        this.price = price;
        this.squareFeet = squareFeet;
        this.size = size;
        this.bath = bath;
        this.rating = rating;
        this.latitude = latitude;
        this.longitude = longitude;
        this.available = available;
        this.images = images;
    }

    public housedata() {
    }

}
