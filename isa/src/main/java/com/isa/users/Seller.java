package com.isa.users;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="sellers")
public class Seller extends User{

    private Integer points;

    private  Float grade;

    private Float money;

    public Seller(){}
    public Seller(Integer points, Float grade) {
        this.points = points;
        this.grade = grade;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Float getGrade() {
        return grade;
    }

    public void setGrade(Float grade) {
        this.grade = grade;
    }

    public Float getMoney() {
        return money;
    }

    public void setMoney(Float money) {
        this.money = money;
    }
}
