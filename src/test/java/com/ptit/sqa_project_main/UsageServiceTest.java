package com.ptit.sqa_project_main;


import com.ptit.sqa_project_main.models.Usage;
import com.ptit.sqa_project_main.services.UsageService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.Date;

@SpringBootTest
public class UsageServiceTest {
    @Autowired
    private ApplicationContext context;

    @Test
    public void testGetByClientIdAndMonthYear1(){
        // thanh cong
        UsageService usageService = context.getBean(UsageService.class);
        Usage actualUsage = usageService.getByClientIdAndMonthYear(1,4,2022);

        Assertions.assertEquals(1,actualUsage.getId());
        Assertions.assertEquals("2022-04-30 17:09:18.0",actualUsage.getCreatedAt().toString().trim());
        Assertions.assertEquals(4,actualUsage.getMonth());
        Assertions.assertEquals(30, actualUsage.getTotalCBM());
        Assertions.assertEquals(30,actualUsage.getRecentUsedCBM());
        Assertions.assertEquals(2022,actualUsage.getYear());
        Assertions.assertEquals(1,actualUsage.getClient().getId());
    }

    @Test
    public void testGetByClientIdAndMonthYear2(){
        //client khong thoa man
        UsageService usageService = context.getBean(UsageService.class);
        Usage actualUsage = usageService.getByClientIdAndMonthYear(3,4,2022);

        Assertions.assertNull(actualUsage);
    }

    @Test
    public void testGetByClientIdAndMonthYear3(){
        //thang khong thoa man
        UsageService usageService = context.getBean(UsageService.class);
        Usage actualUsage = usageService.getByClientIdAndMonthYear(1,13,2022);

        Assertions.assertNull(actualUsage);
    }

    @Test
    public void testGetByClientIdAndMonthYear4(){
        //nam khong thoa man
        UsageService usageService = context.getBean(UsageService.class);
        Usage actualUsage = usageService.getByClientIdAndMonthYear(1,4,1900);

        Assertions.assertNull(actualUsage);
    }
}
