package com.isa.services;

import javax.persistence.*;

@Entity
@Table(name="additional_infos")
public class AdditionalInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String info;

    private Float price;

    public AdditionalInfo() {
    }

    public AdditionalInfo(Long id, String info, Float price) {
        this.id = id;
        this.info = info;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
