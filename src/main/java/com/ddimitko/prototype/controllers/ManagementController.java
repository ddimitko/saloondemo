package com.ddimitko.prototype.controllers;

import com.ddimitko.prototype.objects.Booking;
import com.ddimitko.prototype.services.BookingService;
import com.ddimitko.prototype.services.ShopService;
import com.ddimitko.prototype.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ManagementController {

    @Autowired
    UserService userService;

    @Autowired
    ShopService shopService;

    @Autowired
    BookingService bookingService;

    @GetMapping("/management")
    public String managementPage(Authentication authentication, Model model) {

        String userId = userService.getId(authentication);

        if(userService.findByStaffId(userId).get().getShop() != null){

            Long shopId = userService.findByStaffId(userId).get().getShop().getId();

            model.addAttribute("staff", userService.findAllByShopId(shopId));
            model.addAttribute("bookings", bookingService.findAllByShopId(shopId));
            model.addAttribute("addStaff", userService.findAll());

        }



        return "managementDashboard";
    }

    @GetMapping("/management/editBooking")
    public String managementEditBooking(@RequestParam Long bookId, Model model){

        Booking booking = new Booking();

        if(bookingService.findById(bookId).isPresent()){
            booking = bookingService.findById(bookId).get();
        }

        model.addAttribute("bookId", bookId);

        model.addAttribute("slots", bookingService.addSlotsOnBooking(booking));

        return "managementEditBooking";

    }

    @PutMapping("/management/addStaff")
    @ResponseStatus(value = HttpStatus.OK)
    public void managementAddStaff(@RequestParam Long shopId, @RequestParam String staffId){

        shopService.addStaff(shopId, staffId);

    }

}
