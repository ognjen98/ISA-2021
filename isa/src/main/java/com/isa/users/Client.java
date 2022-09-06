package com.isa.users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.isa.services.Reservation;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="clients")
public class Client extends User{

    private Integer points;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinTable(
            name = "clients_cancelled_reservations",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "reservation_id")
    )
    private Set<Reservation> cancelledReservations;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinTable(
            name = "clients_all_reservations",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "reservation_id")
    )
    private Set<Reservation> allReservations;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinTable(
            name = "clients_deleted_reservations",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "reservation_id")
    )
    private Set<Reservation> deletedReservations;

    private Integer penalties;

    public Client(){}
    public Client(Integer points) {
        this.points = points;
    }

    public Client(String name, String surname, String email, Address address, String mobile, String password,
                  List<Role> roles, Boolean enabled, Boolean deleted, Integer points, Integer penalties,
                  Integer approved, Boolean firstTimeLogin) {
        super(name, surname, email, address, mobile, password, roles, enabled,deleted, approved, firstTimeLogin);
        this.points = points;
        this.penalties = penalties;

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

    public Set<Reservation> getAllReservations() {
        return allReservations;
    }

    public void setAllReservations(Set<Reservation> allReservations) {
        this.allReservations = allReservations;
    }

    public Set<Reservation> getDeletedReservations() {
        return deletedReservations;
    }

    public void setDeletedReservations(Set<Reservation> deletedReservations) {
        this.deletedReservations = deletedReservations;
    }

    public Integer getPenalties() {
        return penalties;
    }

    public void setPenalties(Integer penalties) {
        this.penalties = penalties;
    }
}
