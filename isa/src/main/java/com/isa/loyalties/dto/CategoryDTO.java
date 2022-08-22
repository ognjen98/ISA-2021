package com.isa.loyalties.dto;

public class CategoryDTO {
    private Long id;
    private String type;
    private String name;
    private Integer points;
    private Float discount;

    public CategoryDTO(){}

    public CategoryDTO(Long id, String type, String name, Integer points, Float discount) {
        this.type = type;
        this.name = name;
        this.points = points;
        this.discount = discount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }
}
