package com.ddimitko.prototype.controllers;

import com.ddimitko.prototype.objects.Booking;
import com.ddimitko.prototype.objects.Services;
import com.ddimitko.prototype.objects.Shop;
import com.ddimitko.prototype.repositories.ServicesRepository;
import com.ddimitko.prototype.services.BookingService;
import com.ddimitko.prototype.services.ShopService;
import com.ddimitko.prototype.services.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

@Controller
public class BookingController {

    @Autowired
    UserService userService;

    @Autowired
    ShopService shopService;

    @Autowired
    BookingService bookingService;

    // TODO fix
    @Autowired
    ServicesRepository servicesRepository;

    @GetMapping("/book")
    public String showAvailableBookings(@RequestParam Long shopId, @RequestParam Long serviceId, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date, Model model){

        Shop shop = new Shop();
        if (shopService.findById(shopId).isPresent()) {
           shop = shopService.findById(shopId).get();
        }

        Services service = new Services();
        if (servicesRepository.findById(serviceId).isPresent()) {
            service = servicesRepository.findById(serviceId).get();
        }

        List<LocalTime> slots = bookingService.addSlots(shop, service, date, shop.getOpenTime(), shop.getCloseTime());

        model.addAttribute("shopInfo", shop);
        model.addAttribute("date", date);
        model.addAttribute("slots", slots);

        return "available";
    }

    @PostMapping("/book")
    public String book(@RequestParam Long shopId, @RequestParam Long userId, @RequestParam Long serviceId, Booking booking) throws Exception {

        /*shop.getSchedule();*/

        bookingService.createBooking(shopId, userId, serviceId, booking);

        return "redirect:/shops";
    }

}
