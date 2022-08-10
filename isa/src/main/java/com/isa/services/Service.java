package com.isa.services;

import com.isa.users.Address;
import com.isa.users.Seller;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="services")
@Inheritance(strategy = InheritanceType.JOINED)
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String promoDesc;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Image> images;

    private String rulesOfConduct;

    private Float price;

    private  Float grade;

    private Integer noGuests;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinTable(
            name = "services_additional_infos",
            joinColumns = @JoinColumn(name = "service_id"),
            inverseJoinColumns = @JoinColumn(name = "additional_info_id")
    )
    private Set<AdditionalInfo> additionalInfos;

    @ManyToMany(fetch = FetchType.EAGER,  cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
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

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Set<DiscountReservation> discountReservations;

    public Service(){}

    public Service(Long id, String name, String promoDesc, Set<Image> images, String rulesOfConduct,
                   Float price, Float grade, Integer noGuests, Set<AdditionalInfo> additionalInfos,
                   List<TimePeriod> period,
                   Seller seller,
                   Address address,
                   Set<DiscountReservation> discountReservations) {
        this.id = id;
        this.name = name;
        this.promoDesc = promoDesc;
        this.images = images;
        this.rulesOfConduct = rulesOfConduct;
        this.price = price;
        this.grade =grade;
        this.noGuests = noGuests;
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

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }

    public String getRulesOfConduct() {
        return rulesOfConduct;
    }

    public void setRulesOfConduct(String rulesOfConduct) {
        this.rulesOfConduct = rulesOfConduct;
    }

    public Set<AdditionalInfo> getAdditionalInfos() {
        return additionalInfos;
    }

    public void setAdditionalInfos(Set<AdditionalInfo> additionalInfos) {
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

    public Set<DiscountReservation> getDiscountReservations() {
        return discountReservations;
    }

    public void setDiscountReservations(Set<DiscountReservation> discountReservations) {
        this.discountReservations = discountReservations;
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
}
