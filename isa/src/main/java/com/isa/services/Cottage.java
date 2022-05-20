package com.isa.services;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="cottages")
public class Cottage extends Service{

    private Integer noRooms;

    private Integer noBedsByRoom;

    public Cottage(){}

    public Cottage(Integer noRooms, Integer noBedsByRoom) {
        this.noRooms = noRooms;
        this.noBedsByRoom = noBedsByRoom;
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
