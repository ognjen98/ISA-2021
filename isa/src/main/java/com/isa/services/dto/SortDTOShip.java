package com.isa.services.dto;

import java.util.List;

public class SortDTOShip {
    List<InhrShipDTO> dto;
    String sortParam;

    public SortDTOShip(){}

    public SortDTOShip(List<InhrShipDTO> dto, String sortParam) {
        this.dto = dto;
        this.sortParam = sortParam;
    }

    public List<InhrShipDTO> getDto() {
        return dto;
    }

    public void setDto(List<InhrShipDTO> dto) {
        this.dto = dto;
    }

    public String getSortParam() {
        return sortParam;
    }

    public void setSortParam(String sortParam) {
        this.sortParam = sortParam;
    }
}
