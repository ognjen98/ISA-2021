package com.isa.services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.isa.users.Address;
import com.isa.users.Client;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    @Version
    private Long version;

    private Integer maxCapacity;

    private Boolean cancelled;

    private Boolean reserved;

    private Boolean deleted;

    private Float discPrice;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.REMOVE})
    @JoinTable(
            name = "reservations_additional_infos",
            joinColumns = @JoinColumn(name = "reservation_id"),
            inverseJoinColumns = @JoinColumn(name = "additional_info_id")
    )
    private Set<AdditionalInfo> additionalInfos;

    private Float price;

    @OneToOne(fetch = FetchType.EAGER)
    private Address address;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    private Service service;

    @ManyToOne(fetch = FetchType.EAGER)
    private Client client;

    public Reservation(){}

    public Reservation(Long id, LocalDateTime startTime, LocalDateTime endTime, Integer maxCapacity,
                       Set<AdditionalInfo> additionalInfos,
                       Float price, Float discPrice, Address address, Service service, Client client, Boolean cancelled,
                       Boolean reserved) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.maxCapacity = maxCapacity;
        this.additionalInfos = additionalInfos;
        this.price = price;
        this.address = address;
        this.service = service;
        this.client = client;
        this.cancelled = cancelled;
        this.reserved = reserved;
        this.discPrice = discPrice;
    }
    public Reservation(LocalDateTime startTime, LocalDateTime endTime, Integer maxCapacity,
                       Set<AdditionalInfo> additionalInfos,
                       Float price, Address address, Service service, Client client, Boolean cancelled,
                       Boolean reserved, Boolean deleted) {

        this.startTime = startTime;
        this.endTime = endTime;
        this.maxCapacity = maxCapacity;
        this.additionalInfos = additionalInfos;
        this.price = price;
        this.address = address;
        this.service = service;
        this.client = client;
        this.cancelled = cancelled;
        this.reserved = reserved;
        this.deleted = deleted;
    }

    public Reservation(Reservation reservation){
        this.cancelled = reservation.cancelled;
        this.reserved = reservation.reserved;
    }

    public Reservation(Float price, Float discPrice, Service service){
        this.price = price;
        this.discPrice = discPrice;
        this.service = service;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Integer getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(Integer maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public Set<AdditionalInfo> getAdditionalInfos() {
        return additionalInfos;
    }

    public void setAdditionalInfos(Set<AdditionalInfo> additionalInfos) {
        this.additionalInfos = Reservation.this.additionalInfos;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Boolean getCancelled() {
        return cancelled;
    }

    public void setCancelled(Boolean cancelled) {
        this.cancelled = cancelled;
    }

    public Boolean getReserved() {
        return reserved;
    }

    public void setReserved(Boolean reserved) {
        this.reserved = reserved;
    }

    public Float getDiscPrice() {
        return discPrice;
    }

    public void setDiscPrice(Float discPrice) {
        this.discPrice = discPrice;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
