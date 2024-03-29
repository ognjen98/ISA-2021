package com.isa.services.dto;

public class ServiceDTO {

    private Long id;
    private String name;
    private Float grade;
    private Float price;
    private String streetName;
    private String number;
    private String city;
    private String state;
    private String image;

    public ServiceDTO(){}

    public ServiceDTO(Long id, String name, Float grade, Float price, String streetName, String number, String city,
                      String state, String image) {
        this.name = name;
        this.grade = grade;
        this.price = price;
        this.streetName = streetName;
        this.number = number;
        this.city = city;
        this.state = state;
        this.id = id;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getGrade() {
        return grade;
    }

    public void setGrade(Float grade) {
        this.grade = grade;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
