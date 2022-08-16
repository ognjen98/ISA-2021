package com.isa.services.dto;

import java.time.LocalDateTime;

public class ReportDTO {
    private String type;
    private Integer year;
    private Integer month;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public ReportDTO(){}

    public ReportDTO(String type, Integer year, Integer month, LocalDateTime startTime, LocalDateTime endTime) {
        this.type = type;
        this.year = year;
        this.month = month;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
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
}
