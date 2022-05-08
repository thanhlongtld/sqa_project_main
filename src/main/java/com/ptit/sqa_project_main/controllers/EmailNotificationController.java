package com.ptit.sqa_project_main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EmailNotificationController {

    @Autowired
    public JavaMailSender emailSender;


    @GetMapping("/email-notification")
    public String index() {
        return "email-notification";
    }

    @PostMapping("/email-notification/send")
    public String sendManualMail(){

        // Creat message
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo("dung122k@gmail.com");
        message.setSubject("Test Simple Email");
        message.setText("Hello, Im testing Simple Email");

        // Send Message!
        this.emailSender.send(message);

        return "redirect:/email-notification";
    }
}

