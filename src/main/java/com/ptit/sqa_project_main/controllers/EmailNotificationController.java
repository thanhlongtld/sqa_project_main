package com.ptit.sqa_project_main.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmailNotificationController {
    @GetMapping("/email-notification")
    public String index() {
        return "email-notification";
    }
}
