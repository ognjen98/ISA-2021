package com.isa.services.dto;

import com.isa.services.AdditionalInfo;

import java.time.LocalDateTime;
import java.util.Set;

public class DiscountReservationDTO {

    private Long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer maxCapacity;
    private Float price;
    private String city;
    private Float discPrice;
    private Set<AdditionalInfo> additionalInfos;
    private String image;

    public DiscountReservationDTO(){}

    public DiscountReservationDTO(Long id, LocalDateTime startTime, LocalDateTime endTime, Integer maxCapacity,
                                  Float price, String city, Float discPrice, Set<AdditionalInfo> additionalInfos,
                                  String image) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.maxCapacity = maxCapacity;
        this.price = price;
        this.city = city;
        this.discPrice = discPrice;
        this.additionalInfos = additionalInfos;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Integer getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(Integer maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Float getDiscPrice() {
        return discPrice;
    }

    public void setDiscPrice(Float discPrice) {
        this.discPrice = discPrice;
    }

    public Set<AdditionalInfo> getAdditionalInfos() {
        return additionalInfos;
    }

    public void setAdditionalInfos(Set<AdditionalInfo> additionalInfos) {
        this.additionalInfos = additionalInfos;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
