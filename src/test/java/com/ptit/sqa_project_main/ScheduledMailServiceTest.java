package com.ptit.sqa_project_main;


import com.ptit.sqa_project_main.models.ScheduledEmail;
import com.ptit.sqa_project_main.services.ScheduledEmailService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.*;

@SpringBootTest
public class ScheduledMailServiceTest {

    @Autowired
    private ApplicationContext context;

    @Test
    public void testCheckScheduledDay1 (){
        // Khong co mail nao
        ScheduledEmailService scheduledEmailService = context.getBean(ScheduledEmailService.class);

        Assertions.assertEquals(-1,scheduledEmailService.checkScheduleDay(1));
    }

    @Test
    public void testCheckScheduledDay2 (){
        //billing email
        ScheduledEmailService scheduledEmailService = context.getBean(ScheduledEmailService.class);

        Assertions.assertEquals(0,scheduledEmailService.checkScheduleDay(5));
    }

    @Test
    public void testCheckScheduledDay3 (){
        //warning email
        ScheduledEmailService scheduledEmailService = context.getBean(ScheduledEmailService.class);

        Assertions.assertEquals(1,scheduledEmailService.checkScheduleDay(9));
    }

    @Test
    public void testGetAll (){
        // test get all
        ScheduledEmailService scheduledEmailService = context.getBean(ScheduledEmailService.class);

        List<ScheduledEmail> expResult = new ArrayList<>();
        ScheduledEmail scheduledEmail = new ScheduledEmail();
            scheduledEmail.setId(0);
            scheduledEmail.setScheduledDate(5);

            expResult.add(scheduledEmail);
        ScheduledEmail scheduledEmail1 = new ScheduledEmail();
            scheduledEmail1.setId(1);
            scheduledEmail1.setScheduledDate(9);
            expResult.add(scheduledEmail1);

        List<ScheduledEmail> output = scheduledEmailService.getAll();
        System.out.println(expResult.get(1).getId());
        System.out.println(output.get(1).getId());

   //  Assertions.assertEquals(expResult,output);
       Assertions.assertEquals(expResult.get(0).getId(),output.get(0).getId());
        Assertions.assertEquals(expResult.get(0).getScheduledDate(),output.get(0).getScheduledDate());
        Assertions.assertEquals(expResult.get(1).getId(),output.get(1).getId());
        Assertions.assertEquals(expResult.get(1).getScheduledDate(),output.get(1).getScheduledDate());
    }


}
