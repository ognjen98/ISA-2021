package com.isa.services.dto;

import java.util.List;

public class SortDTOCottage {
    List<InhrCottageDTO> dto;
    String sortParam;

    public SortDTOCottage(){}

    public SortDTOCottage(List<InhrCottageDTO> dto, String sortParam) {
        this.dto = dto;
        this.sortParam = sortParam;
    }

    public List<InhrCottageDTO> getDto() {
        return dto;
    }

    public void setDto(List<InhrCottageDTO> dto) {
        this.dto = dto;
    }

    public String getSortParam() {
        return sortParam;
    }

    public void setSortParam(String sortParam) {
        this.sortParam = sortParam;
    }
}
