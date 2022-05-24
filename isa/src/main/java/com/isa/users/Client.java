package com.isa.users;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="clients")
public class Client extends User{

    private Integer points;

    public Client(){}
    public Client(Integer points) {
        this.points = points;
    }

    public Client(String name, String surname, String email, Address address, String mobile, String password, Role role, Boolean enabled, Integer points) {
        super(name, surname, email, address, mobile, password, role, enabled);
        this.points = points;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }
}
