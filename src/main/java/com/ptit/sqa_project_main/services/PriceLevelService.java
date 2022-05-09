package com.ptit.sqa_project_main.services;

import com.ptit.sqa_project_main.models.Level;
import com.ptit.sqa_project_main.models.PriceLevel;
import com.ptit.sqa_project_main.repositories.LevelRepository;
import com.ptit.sqa_project_main.repositories.PriceLevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PriceLevelService {
    @Autowired
    private PriceLevelRepository repository;

    @Autowired
    private LevelRepository levelRepository;

    public List<PriceLevel> getPriceLevelsByTypeId(Integer typeId) {
        List<PriceLevel> priceLevels = repository.getPriceLevelsByTypeId(typeId);
        System.out.println(priceLevels.size());
        for(PriceLevel priceLevel: priceLevels) {
            System.out.println(priceLevel.getLevel().getName());
        }
        return priceLevels;
    };
}
