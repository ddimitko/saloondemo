package com.ddimitko.prototype.controllers;

import com.ddimitko.prototype.objects.Booking;
import com.ddimitko.prototype.objects.Shop;
import com.ddimitko.prototype.services.CityService;
import com.ddimitko.prototype.services.ShopService;
import com.ddimitko.prototype.services.ShopTypeService;
import com.ddimitko.prototype.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalTime;

@Controller
public class ShopController {

    @Autowired
    ShopService shopService;

    @Autowired
    CityService cityService;

    @Autowired
    ShopTypeService typeService;

    @Autowired
    UserService userService;

    @GetMapping("/shop")
    private String viewShop(@RequestParam Long id, Model model){

        Shop shop = new Shop();

        if(shopService.findById(id).isPresent()){
            shop = shopService.findById(id).get();
        }

        model.addAttribute("shopInfo", shop);
        model.addAttribute("booking", new Booking());
        model.addAttribute("services", shop.getServices());
        model.addAttribute("staff", shop.getStaff());

        return "shopProfile";
    }

    @GetMapping("/addShop")
    public String showAddShop(Model model){

        model.addAttribute("shop", new Shop());
        model.addAttribute("city", cityService.findAll());
        model.addAttribute("type", typeService.findAll());
        model.addAttribute("staff", userService.findAll());

        return "addShop";
    }

    @PostMapping("/process_addShop")
    public String processAddShop(Shop shop){

        shopService.addShop(shop);

        return "redirect:/shops";
    }

}
