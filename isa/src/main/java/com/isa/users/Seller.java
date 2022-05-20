package com.isa.users;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="sellers")
public class Seller extends User{

    private Integer points;

    public Seller(){}
    public Seller(Integer points) {
        this.points = points;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }
}
