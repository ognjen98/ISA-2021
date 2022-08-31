package com.isa.revisions.dto;

public class RevisionDTO {

    private  Long id;
    private Integer grade;
    private String text;
    private String type;
    private Long serviceId;

    public RevisionDTO(){}

    public RevisionDTO(Integer grade, String text, String type) {
        this.grade = grade;
        this.text = text;
        this.type = type;
    }

    public RevisionDTO(Integer grade, String text) {
        this.grade = grade;
        this.text = text;
    }

    public RevisionDTO(Long id, Integer grade, String text, String type) {
        this.id = id;
        this.grade = grade;
        this.text = text;
        this.type = type;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }
}
