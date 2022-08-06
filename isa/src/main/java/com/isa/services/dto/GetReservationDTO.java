package com.isa.services.dto;

import com.isa.services.AdditionalInfo;


import java.time.LocalDateTime;
import java.util.Set;

public class GetReservationDTO {

    private Long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer maxCapacity;
    private Float price;
    private String city;
    private Set<AdditionalInfo> additionalInfoSet;


    public GetReservationDTO(){}

    public GetReservationDTO(Long id, LocalDateTime startTime, LocalDateTime endTime, Integer maxCapacity,
                                  Float price, String city, Set<AdditionalInfo> additionalInfoSet) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.maxCapacity = maxCapacity;
        this.price = price;
        this.city = city;
        this.additionalInfoSet = additionalInfoSet;

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


    public Set<AdditionalInfo> getAdditionalInfoSet() {
        return additionalInfoSet;
    }

    public void setAdditionalInfoSet(Set<AdditionalInfo> additionalInfoSet) {
        this.additionalInfoSet = additionalInfoSet;
    }
}
