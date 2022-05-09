package com.ptit.sqa_project_main.controllers;

import com.ptit.sqa_project_main.models.Type;
import com.ptit.sqa_project_main.services.ClientService;
import com.ptit.sqa_project_main.services.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ConfigController {

    @Autowired
    private TypeService typeService;

    @GetMapping("/config")
    public String index(Model model) {
        List<Type> types = typeService.getAll();

        model.addAttribute("types", types);

        return "config";
    }
}
