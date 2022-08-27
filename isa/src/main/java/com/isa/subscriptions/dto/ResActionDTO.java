package com.isa.subscriptions.dto;

public class ResActionDTO {

    private Float price;
    private Float discount;
    private Long serviceId;

    public ResActionDTO(){}

    public ResActionDTO(Float price, Float discount, Long serviceId) {
        this.price = price;
        this.discount = discount;
        this.serviceId = serviceId;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }
}
