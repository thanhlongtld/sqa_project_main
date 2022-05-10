package com.ptit.sqa_project_main.controllers;

import com.ptit.sqa_project_main.models.PriceLevel;
import com.ptit.sqa_project_main.models.Type;
import com.ptit.sqa_project_main.services.PriceLevelService;
import com.ptit.sqa_project_main.services.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Objects;

@Controller
public class ConfigController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private PriceLevelService priceLevelService;

    @GetMapping("/config")
    public String index(Model model,@RequestParam Integer id, @RequestParam String method) {
        Integer typeId = id;
        if(Objects.equals(method, "DELETE")) {
            typeService.deleteById(typeId);
            typeId = 1;
        }
        List<Type> types = typeService.getAll();

        model.addAttribute("types", types);
        Type currentTypes = null;
        for(Type type: types){
            if(Objects.equals(type.getId(), typeId)){
                currentTypes = type;
            }
        }
        model.addAttribute("currentType", currentTypes);
        List<PriceLevel> priceLevels = priceLevelService.getPriceLevelsByTypeId(currentTypes.getId());

        model.addAttribute("priceLevels", priceLevels);

        return "config";
    }

    @PostMapping("/config")
    public String updateType(Model model, @RequestBody String typeRequest) {
        String[] data = typeRequest.split("&");
        Integer typeId = Integer.parseInt(data[0].split("=")[1]);
            String typeName = data[2].split("=")[1];
            try {
                byte[] utf8Bytes = typeName.getBytes("UTF-8");
                typeName = new String(utf8Bytes, "UTF-8");
                System.out.println(typeName);
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
            Type type = new Type();
            type.setId(typeId);
            type.setName(typeName);
            typeService.save(type);
            for(int i = 3; i < data.length; i++) {
                String sp = data[i].split("priceLevel")[1];
                Integer id = Integer.parseInt(sp.split("=")[0]);
                Integer price = Integer.parseInt(sp.split("=")[1]);
                PriceLevel priceLevel = new PriceLevel();
                priceLevel.setPrice(price);
                priceLevel.setId(id);
                priceLevelService.updatePriceLevel(priceLevel);
            }

        List<Type> types = typeService.getAll();

        model.addAttribute("types", types);
        Type currentTypes = null;
        for(Type _type: types){
            if(_type.getId() == typeId){
                currentTypes = _type;
            }
        }
        model.addAttribute("currentType", currentTypes);
        List<PriceLevel> priceLevels = priceLevelService.getPriceLevelsByTypeId(currentTypes.getId());

        model.addAttribute("priceLevels", priceLevels);

        return "config";
    }
}
