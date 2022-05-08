package com.ptit.sqa_project_main.cronjob;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SendScheduleMail {

    @Scheduled(fixedRate = 5000)
    public void sendScheduleMail(){
        System.out.println("send mail task");
    }

}
