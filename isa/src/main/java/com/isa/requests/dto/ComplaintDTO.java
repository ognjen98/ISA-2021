package com.isa.requests.dto;

public class ComplaintDTO {

    private  Long id;
    private String text;
    private String type;
    private Long serviceId;

    public ComplaintDTO(){}

    public ComplaintDTO(Long id, String text, String type, Long serviceId) {
        this.id = id;
        this.text = text;
        this.type = type;
        this.serviceId = serviceId;
    }

    public ComplaintDTO(String text, String type, Long serviceId) {
        this.text = text;
        this.type = type;
        this.serviceId = serviceId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }
}
