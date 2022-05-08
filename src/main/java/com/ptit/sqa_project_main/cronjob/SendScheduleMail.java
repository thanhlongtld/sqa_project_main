package com.ptit.sqa_project_main.cronjob;

import com.ptit.sqa_project_main.services.ScheduledEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@Component
public class SendScheduleMail {

    @Autowired
    private ScheduledEmailService scheduledEmailService;

    @Scheduled(cron = "0 0 7 ? * * ")
//    @Scheduled(fixedRate = 5000)
    public void sendScheduleMail(){
        int currentDate = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        System.out.println(currentDate);
        int id = scheduledEmailService.checkScheduleDay(currentDate);
        switch (id){
            case -1:
                break;
            case 0:
                scheduledEmailService.sendBillingEmail();
                break;
            case 1:
                scheduledEmailService.sendWarningEmail();
                break;
        }
    }



}
