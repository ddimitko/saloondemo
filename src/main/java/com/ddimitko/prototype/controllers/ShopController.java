package com.ddimitko.prototype.controllers;

import com.ddimitko.prototype.objects.Shop;
import com.ddimitko.prototype.services.CityService;
import com.ddimitko.prototype.services.ShopService;
import com.ddimitko.prototype.services.ShopTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ShopController {

    @Autowired
    ShopService shopService;

    @Autowired
    CityService cityService;

    @Autowired
    ShopTypeService typeService;

    @GetMapping("/addShop")
    public String showAddShop(Model model){

        model.addAttribute("shop", new Shop());
        model.addAttribute("city", cityService.findAll());
        model.addAttribute("type", typeService.findAll());

        return "addShop";
    }

    @PostMapping("/addSchedule")
    public String addSchedule(@RequestParam Long shopId){

        Shop shop = shopService.findById(shopId).get();

        return "redirect:/shops";

    }

    @PostMapping("/process_addShop")
    public String processAddShop(Shop shop){

        shopService.addShop(shop);

        return "redirect:/shops";
    }

}
