package com.isa.services.dto;

import java.util.List;

public class FilterDTO {
    private String entity;
    private String type;
    private List<InhrShipDTO> dtos;

    public FilterDTO(){}

    public FilterDTO(String entity, String type) {
        this.entity = entity;
        this.type = type;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<InhrShipDTO> getDtos() {
        return dtos;
    }

    public void setDtos(List<InhrShipDTO> dtos) {
        this.dtos = dtos;
    }
}
