package com.isa.users.dto;

public class RegistrationDTO {
    private String name;
    private String surname;
    private String streetName;
    private String number;
    private String city;
    private String state;
    private String email;
    private String mobile;
    private String password;

    public RegistrationDTO(){}
    public RegistrationDTO(String name, String surname, String streetName, String number, String city, String state, String email, String mobile, String password) {
        this.name = name;
        this.surname = surname;
        this.streetName = streetName;
        this.number = number;
        this.city = city;
        this.state = state;
        this.email = email;
        this.mobile = mobile;
        this.password = password;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
