package com.isa.subscriptions.dto;

public class SubscriptionDTO {

    private Long id;
    private String serviceName;
    private String city;

    public SubscriptionDTO(){}

    public SubscriptionDTO(Long id, String serviceName, String city) {
        this.id = id;
        this.serviceName = serviceName;
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
