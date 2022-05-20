package com.isa.services;

import com.isa.users.Address;
import com.isa.users.Seller;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name="ships")
public class Ship extends Service{

    private String type;

    private Float length;

    private Integer noEngines;

    private Float enginePower;

    private Float maxSpeed;

    private Integer capacity;

    @OneToMany
    private List<FishEq> fishingEquipment;

    private ReservationCancellationTerms rct;

    @OneToMany
    private List<NavigationEquipment> navEquipments;

    public Ship(){}

    public Ship(Long id, String name, String promoDesc, List<Image> images, String rulesOfConduct, List<AdditionalInfo> additionalInfos, List<TimePeriod> period, Seller seller, Address address, List<DiscountReservation> discountReservations, String type, Float length, Integer noEngines, Float enginePower, Float maxSpeed, Integer capacity, List<FishEq> fishingEquipment, ReservationCancellationTerms rct, List<NavigationEquipment> navEquipments) {
        super(id, name, promoDesc, images, rulesOfConduct, additionalInfos, period, seller, address, discountReservations);
        this.type = type;
        this.length = length;
        this.noEngines = noEngines;
        this.enginePower = enginePower;
        this.maxSpeed = maxSpeed;
        this.capacity = capacity;
        this.fishingEquipment = fishingEquipment;
        this.rct = rct;
        this.navEquipments = navEquipments;
    }

    public Ship(String type, Float length, Integer noEngines, Float enginePower, Float maxSpeed, Integer capacity, List<FishEq> fishingEquipment, ReservationCancellationTerms rct, List<NavigationEquipment> navEquipments) {
        this.type = type;
        this.length = length;
        this.noEngines = noEngines;
        this.enginePower = enginePower;
        this.maxSpeed = maxSpeed;
        this.capacity = capacity;
        this.fishingEquipment = fishingEquipment;
        this.rct = rct;
        this.navEquipments = navEquipments;
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

    public List<FishEq> getFishingEquipment() {
        return fishingEquipment;
    }

    public void setFishingEquipment(List<FishEq> fishingEquipment) {
        this.fishingEquipment = fishingEquipment;
    }

    public ReservationCancellationTerms getRct() {
        return rct;
    }

    public void setRct(ReservationCancellationTerms rct) {
        this.rct = rct;
    }

    public List<NavigationEquipment> getNavEquipments() {
        return navEquipments;
    }

    public void setNavEquipments(List<NavigationEquipment> navEquipments) {
        this.navEquipments = navEquipments;
    }
}
