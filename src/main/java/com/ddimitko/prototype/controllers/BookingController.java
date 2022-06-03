package com.ddimitko.prototype.controllers;

import com.ddimitko.prototype.objects.Booking;
import com.ddimitko.prototype.objects.Shop;
import com.ddimitko.prototype.services.BookingService;
import com.ddimitko.prototype.services.ShopService;
import com.ddimitko.prototype.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookingController {

    @Autowired
    UserService userService;

    @Autowired
    ShopService shopService;

    @Autowired
    BookingService bookingService;

    @PostMapping("/book")
    public String book(@RequestParam Long shopId, @RequestParam Long userId, @RequestParam Long serviceId, Booking booking) throws Exception {

        Shop shop = shopService.findById(shopId).get();
        /*shop.getSchedule();*/

        bookingService.createBooking(shopId, userId, serviceId, booking);

        return "redirect:/shops";
    }

}
