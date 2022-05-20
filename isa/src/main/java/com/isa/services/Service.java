package com.isa.services;

import com.isa.users.Address;
import com.isa.users.Seller;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="services")
@Inheritance(strategy = InheritanceType.JOINED)
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String promoDesc;

    @OneToMany
    private List<Image> images;

    private String rulesOfConduct;

    @OneToMany
    private List<AdditionalInfo> additionalInfos;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "services_periods",
            joinColumns = @JoinColumn(name = "service_id"),
            inverseJoinColumns = @JoinColumn(name = "period_id")
    )
    private List<TimePeriod> period;

    @ManyToOne(fetch = FetchType.EAGER)
    private Seller seller;

    @OneToOne(fetch = FetchType.EAGER)
    private Address address;

    @OneToMany
    private List<DiscountReservation> discountReservations;

    public Service(){}

    public Service(Long id, String name, String promoDesc, List<Image> images, String rulesOfConduct, List<AdditionalInfo> additionalInfos, List<TimePeriod> period, Seller seller, Address address, List<DiscountReservation> discountReservations) {
        this.id = id;
        this.name = name;
        this.promoDesc = promoDesc;
        this.images = images;
        this.rulesOfConduct = rulesOfConduct;
        this.additionalInfos = additionalInfos;
        this.period = period;
        this.seller = seller;
        this.address = address;
        this.discountReservations = discountReservations;
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

    public String getPromoDesc() {
        return promoDesc;
    }

    public void setPromoDesc(String promoDesc) {
        this.promoDesc = promoDesc;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public String getRulesOfConduct() {
        return rulesOfConduct;
    }

    public void setRulesOfConduct(String rulesOfConduct) {
        this.rulesOfConduct = rulesOfConduct;
    }

    public List<AdditionalInfo> getAdditionalInfos() {
        return additionalInfos;
    }

    public void setAdditionalInfos(List<AdditionalInfo> additionalInfos) {
        this.additionalInfos = additionalInfos;
    }

    public List<TimePeriod> getPeriod() {
        return period;
    }

    public void setPeriod(List<TimePeriod> period) {
        this.period = period;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<DiscountReservation> getDiscountReservations() {
        return discountReservations;
    }

    public void setDiscountReservations(List<DiscountReservation> discountReservations) {
        this.discountReservations = discountReservations;
    }
}
