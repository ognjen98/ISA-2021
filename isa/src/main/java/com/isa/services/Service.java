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


    private String image;

    private String rulesOfConduct;

    @Version
    private Integer version;

    private Float price;

    private  Float grade;

    private Integer noGuests;

    private Boolean deleted;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinTable(
            name = "services_additional_infos",
            joinColumns = @JoinColumn(name = "service_id"),
            inverseJoinColumns = @JoinColumn(name = "additional_info_id")
    )
    private Set<AdditionalInfo> additionalInfos;

    @ManyToMany(fetch = FetchType.EAGER,  cascade = {CascadeType.REMOVE})
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

    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.REMOVE})
    private Set<Reservation> discountReservations;

    public Service(){}

    public Service(Long id, String name, String promoDesc, String image, String rulesOfConduct,
                   Float price, Float grade, Integer noGuests, Set<AdditionalInfo> additionalInfos,
                   List<TimePeriod> period,
                   Seller seller,
                   Address address,
                   Set<Reservation> discountReservations) {
        this.id = id;
        this.name = name;
        this.promoDesc = promoDesc;
        this.image = image;
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

    public String getImage() {
        return image;
    }

    public void setImages(String image) {
        this.image = image;
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

    public Set<Reservation> getReservations() {
        return discountReservations;
    }

    public void setReservations(Set<Reservation> discountReservations) {
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

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
