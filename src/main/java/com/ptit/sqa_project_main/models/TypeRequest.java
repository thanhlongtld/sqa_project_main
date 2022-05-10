package com.ptit.sqa_project_main.models;

import java.util.List;

public class TypeRequest {
    private Integer id;
    private List<PriceLevel> priceLevelList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<PriceLevel> getPriceLevelList() {
        return priceLevelList;
    }

    public void setPriceLevelList(List<PriceLevel> priceLevelList) {
        this.priceLevelList = priceLevelList;
    }

    public TypeRequest(Integer id, List<PriceLevel> priceLevelList) {
        this.id = id;
        this.priceLevelList = priceLevelList;
    }
}
