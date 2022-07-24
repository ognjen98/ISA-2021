package com.isa.services.dto;

public class CottageDTO {

    private Long id;
    private String name;
    private Float price;
    private Float grade;
    private Integer noGuests;
    private Integer noRooms;
    private Integer noBedsByRoom;

    public CottageDTO(){}

    public CottageDTO(Long id, String name, Float price, Float grade, Integer noGuests, Integer noRooms, Integer noBedsByRoom) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.grade = grade;
        this.noGuests = noGuests;
        this.noRooms = noRooms;
        this.noBedsByRoom = noBedsByRoom;
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

    public Integer getNoRooms() {
        return noRooms;
    }

    public void setNoRooms(Integer noRooms) {
        this.noRooms = noRooms;
    }

    public Integer getNoBedsByRoom() {
        return noBedsByRoom;
    }

    public void setNoBedsByRoom(Integer noBedsByRoom) {
        this.noBedsByRoom = noBedsByRoom;
    }


}
