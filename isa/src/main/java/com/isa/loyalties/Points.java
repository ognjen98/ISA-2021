package com.isa.loyalties;

import javax.persistence.*;

@Entity
@Table(name = "points")
public class Points {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer clientPoints;

    private Integer sellerPoints;

    public Points(){}

    public Points(Long id, Integer clientPoints, Integer sellerPoints) {
        this.id = id;
        this.clientPoints = clientPoints;
        this.sellerPoints = sellerPoints;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getClientPoints() {
        return clientPoints;
    }

    public void setClientPoints(Integer clientPoints) {
        this.clientPoints = clientPoints;
    }

    public Integer getSellerPoints() {
        return sellerPoints;
    }

    public void setSellerPoints(Integer sellerPoints) {
        this.sellerPoints = sellerPoints;
    }
}
