package com.isa.users.dto;

public class UpdateInfoDTO {

    private String name;
    private String surname;
    private String password;
    private String city;
    private String state;
    private String streetName;
    private String number;
    private String mobile;

    public UpdateInfoDTO(){}

    public UpdateInfoDTO(String name, String surname, String password, String city, String state,
                         String streetName, String number, String mobile) {
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.city = city;
        this.state = state;
        this.streetName = streetName;
        this.number = number;
        this.mobile = mobile;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

}
