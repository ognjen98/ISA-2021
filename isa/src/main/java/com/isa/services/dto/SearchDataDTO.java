package com.isa.services.dto;

import org.apache.tomcat.jni.Local;

import java.time.LocalDateTime;

public class SearchDataDTO {

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String noGuests;
    private String entity;
    private String grade;
    private String location;

    public SearchDataDTO(){}

    public SearchDataDTO(LocalDateTime startTime, LocalDateTime endTime, String noGuests, String entity, String grade,
                         String location) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.noGuests = noGuests;
        this.entity = entity;
        this.grade = grade;
        this.location = location;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getNoGuests() {
        return noGuests;
    }

    public void setNoGuests(String noGuests) {
        this.noGuests = noGuests;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
