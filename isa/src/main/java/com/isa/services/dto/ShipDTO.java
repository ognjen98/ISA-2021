package com.isa.services.dto;

public class ShipDTO {

    private Long id;
    private String name;
    private Float price;
    private Float grade;
    private Integer noGuests;
    private String type;
    private Float length;
    private Integer noEngines;
    private Float maxSpeed;
    private Float enginePower;

    public ShipDTO(){

    }

    public ShipDTO(Long id, String name, Float price, Float grade, Integer noGuests, String type, Float length, Integer noEngines, Float maxSpeed, Float enginePower) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.grade = grade;
        this.noGuests = noGuests;
        this.type = type;
        this.length = length;
        this.noEngines = noEngines;
        this.maxSpeed = maxSpeed;
        this.enginePower = enginePower;
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

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getGrade() {
        return grade;
    }

    public void setGrade(Float grade) {
        this.grade = grade;
    }

    public Integer getNoGuests() {
        return noGuests;
    }

    public void setNoGuests(Integer noGuests) {
        this.noGuests = noGuests;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Float getLength() {
        return length;
    }

    public void setLength(Float length) {
        this.length = length;
    }

    public Integer getNoEngines() {
        return noEngines;
    }

    public void setNoEngines(Integer noEngines) {
        this.noEngines = noEngines;
    }

    public Float getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(Float maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public Float getEnginePower() {
        return enginePower;
    }

    public void setEnginePower(Float enginePower) {
        this.enginePower = enginePower;
    }
}
