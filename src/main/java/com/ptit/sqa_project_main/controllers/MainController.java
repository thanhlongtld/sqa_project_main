package com.ptit.sqa_project_main.controllers;


import com.ptit.sqa_project_main.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class MainController {
    @GetMapping("")
    public String showHomePage(HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user!=null){
            return "index";
        }
        return "redirect:/login";
    }
}
