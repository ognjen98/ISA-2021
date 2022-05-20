package com.isa.services;

import javax.persistence.*;

@Entity
@Table(name = "fish_equipment")
public class FishEq {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private FishingEquipment fishingEquipment;

    public FishEq(){}

    public FishEq(Long id, FishingEquipment fishingEquipment) {
        this.id = id;
        this.fishingEquipment = fishingEquipment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FishingEquipment getFishingEquipment() {
        return fishingEquipment;
    }

    public void setFishingEquipment(FishingEquipment fishingEquipment) {
        this.fishingEquipment = fishingEquipment;
    }
}
