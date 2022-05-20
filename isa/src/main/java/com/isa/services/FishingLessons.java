package com.isa.services;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name="lessons")
public class FishingLessons extends Service{

    private String instructorBio;

    private Integer maxPersons;

    @OneToMany
    private List<FishEq> fishingEquipment;

    private ReservationCancellationTerms terms;

    public FishingLessons(){}

    public FishingLessons(String instructorBio, Integer maxPersons, List<FishEq> fishingEquipment, ReservationCancellationTerms terms) {
        this.instructorBio = instructorBio;
        this.maxPersons = maxPersons;
        this.fishingEquipment = fishingEquipment;
        this.terms = terms;
    }

    public String getInstructorBio() {
        return instructorBio;
    }

    public void setInstructorBio(String instructorBio) {
        this.instructorBio = instructorBio;
    }

    public Integer getMaxPersons() {
        return maxPersons;
    }

    public void setMaxPersons(Integer maxPersons) {
        this.maxPersons = maxPersons;
    }

    public List<FishEq> getFishingEquipment() {
        return fishingEquipment;
    }

    public void setFishingEquipment(List<FishEq> fishingEquipment) {
        this.fishingEquipment = fishingEquipment;
    }

    public ReservationCancellationTerms getTerms() {
        return terms;
    }

    public void setTerms(ReservationCancellationTerms terms) {
        this.terms = terms;
    }
}

