package com.isa.services.dto;

public class InhrCottageDTO extends ServiceDTO{
    private Integer noGuests;
    private Integer noRooms;
    private Integer noBedsByRoom;

    public InhrCottageDTO(){}

    public InhrCottageDTO(Long id, String name, Float grade, Float price, String streetName, String number, String city, String state, Integer noGuests, Integer noRooms, Integer noBedsByRoom) {
        super(id, name, grade, price, streetName, number, city, state);
        this.noGuests = noGuests;
        this.noRooms = noRooms;
        this.noBedsByRoom = noBedsByRoom;
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
