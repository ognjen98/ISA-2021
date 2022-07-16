package com.isa.services;

import com.isa.users.Address;
import com.isa.users.Client;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="reservations")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    @Version
    private Long version;

    private Integer maxCapacity;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "reservations_additional_infos",
            joinColumns = @JoinColumn(name = "reservation_id"),
            inverseJoinColumns = @JoinColumn(name = "additional_info_id")
    )
    private Set<AdditionalInfo> additionalInfos;

    private Float price;

    @OneToOne(fetch = FetchType.EAGER)
    private Address address;

    @ManyToOne(fetch = FetchType.EAGER)
    private Service service;

    @ManyToOne(fetch = FetchType.EAGER)
    private Client client;

    public Reservation(){}

    public Reservation(Long id, LocalDateTime startTime, LocalDateTime endTime, Integer maxCapacity,
                       Set<AdditionalInfo> additionalInfos,
                       Float price, Address address, Service service, Client client) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.maxCapacity = maxCapacity;
        this.additionalInfos = additionalInfos;
        this.price = price;
        this.address = address;
        this.service = service;
        this.client = client;
    }
    public Reservation(LocalDateTime startTime, LocalDateTime endTime, Integer maxCapacity,
                       Set<AdditionalInfo> additionalInfos,
                       Float price, Address address, Service service, Client client) {

        this.startTime = startTime;
        this.endTime = endTime;
        this.maxCapacity = maxCapacity;
        this.additionalInfos = additionalInfos;
        this.price = price;
        this.address = address;
        this.service = service;
        this.client = client;
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
}
