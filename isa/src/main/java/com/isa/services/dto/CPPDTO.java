package com.isa.services.dto;

public class CPPDTO {

    private Integer points;
    private String category;
    private Integer penalties;

    public CPPDTO(){}

    public CPPDTO(Integer points, String category, Integer penalties) {
        this.points = points;
        this.category = category;
        this.penalties = penalties;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getPenalties() {
        return penalties;
    }

    public void setPenalties(Integer penalties) {
        this.penalties = penalties;
    }
}
