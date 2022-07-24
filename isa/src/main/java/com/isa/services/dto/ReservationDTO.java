package com.isa.services.dto;

import com.isa.services.AdditionalInfo;
import org.apache.tomcat.jni.Local;

import java.time.LocalDateTime;
import java.util.Set;

public class ReservationDTO {

    private Long id;
    private LocalDateTime start;
    private LocalDateTime end;
    private Set<AdditionalInfo> additionalInfos;
    private Long serviceId;
    private Integer noPersons;

    public ReservationDTO(){}

    public ReservationDTO(Long id, LocalDateTime start, LocalDateTime end, Set<AdditionalInfo> additionalInfos,
                          Long serviceId, Integer noPersons) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.additionalInfos = additionalInfos;
        this.serviceId = serviceId;
        this.noPersons = noPersons;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public Set<AdditionalInfo> getAdditionalInfos() {
        return additionalInfos;
    }

    public void setAdditionalInfos(Set<AdditionalInfo> additionalInfos) {
        this.additionalInfos = additionalInfos;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public Integer getNoPersons() {
        return noPersons;
    }

    public void setNoPersons(Integer noPersons) {
        this.noPersons = noPersons;
    }
}
