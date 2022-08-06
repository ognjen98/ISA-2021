package com.isa.users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.isa.services.Reservation;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="clients")
public class Client extends User{

    private Integer points;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "clients_cancelled_reservations",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "reservation_id")
    )
    private Set<Reservation> cancelledReservations;

    public Client(){}
    public Client(Integer points) {
        this.points = points;
    }

    public Client(String name, String surname, String email, Address address, String mobile, String password,
                  Set<Role> roles, Boolean enabled, Integer points) {
        super(name, surname, email, address, mobile, password, roles, enabled);
        this.points = points;

    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Set<Reservation> getCancelledReservations() {
        return cancelledReservations;
    }

    public void setCancelledReservations(Set<Reservation> cancelledReservations) {
        this.cancelledReservations = cancelledReservations;
    }
}
