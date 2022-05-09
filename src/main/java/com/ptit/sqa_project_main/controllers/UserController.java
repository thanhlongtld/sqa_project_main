package com.ptit.sqa_project_main.controllers;

import com.ptit.sqa_project_main.models.Client;
import com.ptit.sqa_project_main.models.User;
import com.ptit.sqa_project_main.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String index(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if(user!=null){
            return "redirect:/";
        }
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/login";
    }

    @PostMapping("/login/auth")
    public String userAuth(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession session){
            User user = userService.Auth(username,password);
            if(user!=null){
                session.setAttribute("user",user);
                return "redirect:/";
            }
            session.setAttribute("message","Sai tai khoan hoac mat khau");
            return "redirect:/login";
    }
}
