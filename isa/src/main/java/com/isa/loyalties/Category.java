package com.isa.loyalties;

import javax.persistence.*;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Float discount;

    private Integer points;

    private String type;

    public Category(){}

    public Category(Long id, String name, Float discount, Integer points, String type) {
        this.id = id;
        this.name = name;
        this.discount = discount;
        this.points = points;
        this.type = type;
    }

    public Category(String name, Float discount, Integer points, String type) {
        this.name = name;
        this.discount = discount;
        this.points = points;
        this.type = type;
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

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public String getType() {

        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
