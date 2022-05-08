package com.ptit.sqa_project_main.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClientController {
    @GetMapping("/user")
    public String index() {
        return "client-management";
    }
}