package com.isa.services;

import com.isa.users.Address;
import com.isa.users.Seller;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="ships")

public class Ship extends Service{

    private String type;

    private Float length;

    private Integer noEngines;

    private Float enginePower;

    private Float maxSpeed;

    private Integer capacity;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<FishEq> fishEqs;

    private ReservationCancellationTerms rct;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<NavigationEquipment> navigationEquipments;

    public Ship(){}

    public Ship(Long id, String name, String promoDesc, String images, String rulesOfConduct, Float price,
                Float grade, Integer noGuests, Set<AdditionalInfo> additionalInfos, List<TimePeriod> period,
                Seller seller,
                Address address, Set<Reservation> discountReservations, String type, Float length, Integer noEngines,
                Float enginePower, Float maxSpeed, Integer capacity, Set<FishEq> fishEqs, ReservationCancellationTerms rct, Set<NavigationEquipment> navigationEquipments) {
        super(id, name, promoDesc, images, rulesOfConduct, price,grade, noGuests, additionalInfos, period, seller,
                address,
                discountReservations);
        this.type = type;
        this.length = length;
        this.noEngines = noEngines;
        this.enginePower = enginePower;
        this.maxSpeed = maxSpeed;
        this.capacity = capacity;
        this.fishEqs = fishEqs;
        this.rct = rct;
        this.navigationEquipments = navigationEquipments;
    }

    public Ship(String type, Float length, Integer noEngines, Float enginePower, Float maxSpeed, Integer capacity, Set<FishEq> fishEqs, ReservationCancellationTerms rct, Set<NavigationEquipment> navigationEquipments) {
        this.type = type;
        this.length = length;
        this.noEngines = noEngines;
        this.enginePower = enginePower;
        this.maxSpeed = maxSpeed;
        this.capacity = capacity;
        this.fishEqs = fishEqs;
        this.rct = rct;
        this.navigationEquipments = navigationEquipments;
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

    public Float getEnginePower() {
        return enginePower;
    }

    public void setEnginePower(Float enginePower) {
        this.enginePower = enginePower;
    }

    public Float getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(Float maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Set<FishEq> getFishEqs() {
        return fishEqs;
    }

    public void setFishEqs(Set<FishEq> fishEqs) {
        this.fishEqs = fishEqs;
    }

    public ReservationCancellationTerms getRct() {
        return rct;
    }

    public void setRct(ReservationCancellationTerms rct) {
        this.rct = rct;
    }

    public Set<NavigationEquipment> getNavigationEquipments() {
        return navigationEquipments;
    }

    public void setNavigationEquipments(Set<NavigationEquipment> navigationEquipments) {
        this.navigationEquipments = navigationEquipments;
    }
}
