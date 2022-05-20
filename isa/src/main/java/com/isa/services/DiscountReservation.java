package com.isa.services;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="disc_reservations")
public class DiscountReservation extends Reservation{

    private Float discPrice;

    public DiscountReservation(){}

    public DiscountReservation(Float discPrice) {
        this.discPrice = discPrice;
    }

    public Float getDiscPrice() {
        return discPrice;
    }

    public void setDiscPrice(Float discPrice) {
        this.discPrice = discPrice;
    }
}
