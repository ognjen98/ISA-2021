package com.isa.services.dto;

import java.util.List;

public class FilterDTO {
    private String entity;
    private List<String> type;
    private List<ServiceDTO> dtos;

    public FilterDTO(){}

    public FilterDTO(String entity, List<String> type) {
        this.entity = entity;
        this.type = type;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public List<String> getType() {
        return type;
    }

    public void setType(List<String> type) {
        this.type = type;
    }

    public List<ServiceDTO> getDtos() {
        return dtos;
    }

    public void setDtos(List<ServiceDTO> dtos) {
        this.dtos = dtos;
    }
}
