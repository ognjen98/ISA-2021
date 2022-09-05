package com.isa.services.dto;

public class InhrShipDTO extends ServiceDTO{

    private Integer noGuests;
    private String type;
    private Float length;
    private Integer noEngines;
    private Float maxSpeed;
    private Float enginePower;

    public InhrShipDTO(){}

    public InhrShipDTO(Long id, String name, Float grade, Float price, String streetName, String number, String city,
                       String state, Integer noGuests, String type, Float length, Integer noEngines, Float maxSpeed,
                       Float enginePower, String image) {
        super(id, name, grade, price, streetName, number, city, state, image);
        this.noGuests = noGuests;
        this.type = type;
        this.length = length;
        this.noEngines = noEngines;
        this.maxSpeed = maxSpeed;
        this.enginePower = enginePower;
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
