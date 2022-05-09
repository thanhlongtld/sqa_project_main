package com.ptit.sqa_project_main.cronjob;

import com.ptit.sqa_project_main.services.ScheduledEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

@Component
public class SendScheduleMail {

    @Autowired
    private ScheduledEmailService scheduledEmailService;

    @Scheduled(cron = "0 0 7 ? * * ")
//    @Scheduled(fixedRate = 5000)
    public void sendScheduleMail() throws MessagingException {
        int currentDate = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        LocalDate now = LocalDate.now(); // 2015-11-24
        LocalDate earlier = now.minusMonths(1); // 2015-10-24

        earlier.getMonth(); // java.time.Month = OCTOBER
        earlier.getMonth().getValue(); // 10
        earlier.getYear(); // 2015
        System.out.println(currentDate);
        int id = scheduledEmailService.checkScheduleDay(currentDate);
        switch (id){
            case -1:
                break;
            case 0:
                scheduledEmailService.sendBillingEmail(earlier.getMonth().getValue(),earlier.getYear());
                break;
            case 1:
                scheduledEmailService.sendWarningEmail();
                break;
        }
    }



}
