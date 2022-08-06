package com.isa.services;

import com.isa.users.Address;
import com.isa.users.Client;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name="disc_reservations")
public class DiscountReservation extends Reservation{

    private Float discPrice;

    public DiscountReservation(){}



    public DiscountReservation(Float discPrice) {
        this.discPrice = discPrice;
    }

    public DiscountReservation(Long id, LocalDateTime startTime, LocalDateTime endTime, Integer maxCapacity, Set<AdditionalInfo> additionalInfos, Float price, Address address, Service service, Client client, Boolean cancelled, Boolean reserved, Float discPrice) {
        super(id, startTime, endTime, maxCapacity, additionalInfos, price, address, service, client, cancelled, reserved);
        this.discPrice = discPrice;
    }

    public Float getDiscPrice() {
        return discPrice;
    }

    public void setDiscPrice(Float discPrice) {
        this.discPrice = discPrice;
    }
}
