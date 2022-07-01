package com.isa.services.dto;

import java.util.List;

public class SortDTO {
    List<ServiceDTO> dto;
    String sortParam;

    public SortDTO(){}

    public SortDTO(List<ServiceDTO> dto, String sortParam) {
        this.dto = dto;
        this.sortParam = sortParam;
    }

    public List<ServiceDTO> getDto() {
        return dto;
    }

    public void setDto(List<ServiceDTO> dto) {
        this.dto = dto;
    }

    public String getSortParam() {
        return sortParam;
    }

    public void setSortParam(String sortParam) {
        this.sortParam = sortParam;
    }
}
