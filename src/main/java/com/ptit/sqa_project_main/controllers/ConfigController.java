package com.ptit.sqa_project_main.controllers;

import com.ptit.sqa_project_main.models.PriceLevel;
import com.ptit.sqa_project_main.models.Type;
import com.ptit.sqa_project_main.repositories.PriceLevelRepository;
import com.ptit.sqa_project_main.services.ClientService;
import com.ptit.sqa_project_main.services.PriceLevelService;
import com.ptit.sqa_project_main.services.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class ConfigController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private PriceLevelService priceLevelService;

    @GetMapping("/config")
    public String index(Model model) {
        List<Type> types = typeService.getAll();

        model.addAttribute("types", types);
        model.addAttribute("currentType", types.get(0));
        List<PriceLevel> priceLevels = priceLevelService.getPriceLevelsByTypeId(types.get(0).getId());

        model.addAttribute("priceLevels", priceLevels);

        return "config";
    }

    @RequestMapping("config/{id}")
    public String getById(Model model, @PathVariable(value="id") Integer id) {
        List<Type> types = typeService.getAll();

        model.addAttribute("types", types);
        Type currentTypes = null;
        for(Type type: types){
            if(type.getId() == id){
                currentTypes = type;
            }
        }
        model.addAttribute("currentType", currentTypes);
        List<PriceLevel> priceLevels = priceLevelService.getPriceLevelsByTypeId(currentTypes.getId());

        model.addAttribute("priceLevels", priceLevels);

        return "config";
    }
}
