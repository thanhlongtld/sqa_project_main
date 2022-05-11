package com.ptit.sqa_project_main;


import com.ptit.sqa_project_main.models.PriceLevel;
import com.ptit.sqa_project_main.repositories.PriceLevelRepository;
import com.ptit.sqa_project_main.services.PriceLevelService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
public class PriceLevelServiceTest {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private PriceLevelRepository repository;

    @Test
    public void testGetPriceLevelByTypeId(){
        PriceLevelService priceLevelService = context.getBean(PriceLevelService.class);

        List<PriceLevel> priceLevels = priceLevelService.getPriceLevelsByTypeId(2);

        Assertions.assertEquals(3600,priceLevels.get(0).getPrice());
        Assertions.assertEquals(4500,priceLevels.get(1).getPrice());
        Assertions.assertEquals(5600,priceLevels.get(2).getPrice());
        Assertions.assertEquals(6700,priceLevels.get(3).getPrice());
    }

    @Rollback
    @Test
    public void testUpdatePriceLevel(){
        PriceLevelService priceLevelService = context.getBean(PriceLevelService.class);

        PriceLevel priceLevel = new PriceLevel();
        priceLevel.setId(10);
        priceLevel.setPrice(100000);

        priceLevelService.updatePriceLevel(priceLevel);

        Assertions.assertEquals(100000,this.repository.getPriceLevelById(10).getPrice());

    }
}
