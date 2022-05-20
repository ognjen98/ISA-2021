package com.isa.services;

import javax.persistence.*;

@Entity
@Table(name = "nav_equipments")
public class NavigationEquipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private NavEquipment navEquipment;

    public NavigationEquipment(){}

    public NavigationEquipment(Long id, NavEquipment navEquipment) {
        this.id = id;
        this.navEquipment = navEquipment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public NavEquipment getNavEquipment() {
        return navEquipment;
    }

    public void setNavEquipment(NavEquipment navEquipment) {
        this.navEquipment = navEquipment;
    }
}
