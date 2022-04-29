package com.ddimitko.prototype.controllers;

import com.ddimitko.prototype.objects.DaySchedule;
import com.ddimitko.prototype.objects.Shop;
import com.ddimitko.prototype.objects.WeekSchedule;
import com.ddimitko.prototype.services.ScheduleService;
import com.ddimitko.prototype.services.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ShopController {

    @Autowired
    ShopService shopService;

    @Autowired
    ScheduleService scheduleService;

    @PostMapping("/addSchedule")
    public String addSchedule(@RequestParam Long shopId, DaySchedule daySchedule, WeekSchedule weekSchedule){

        Shop shop = shopService.findById(shopId).get();

        scheduleService.createDaySchedule(daySchedule);

        scheduleService.createWeekSchedule(shop, daySchedule, weekSchedule);

        return "redirect:/shops";

    }

}
