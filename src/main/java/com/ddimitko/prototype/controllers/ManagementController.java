package com.ddimitko.prototype.controllers;

import com.ddimitko.prototype.objects.User;
import com.ddimitko.prototype.services.BookingService;
import com.ddimitko.prototype.services.UserService;
import com.ddimitko.prototype.userdetails.user.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.Optional;

@Controller
public class ManagementController {

    @Autowired
    UserService userService;

    @Autowired
    BookingService bookingService;

    @GetMapping("/management")
    public String managementPage(Authentication authentication, Model model) {

        String userId = userService.getId(authentication);

        if(userService.findByStaffId(userId).get().getShop() != null){

            Long shopId = userService.findByStaffId(userId).get().getShop().getId();

            model.addAttribute("staff", userService.findAllByShopId(shopId));
            model.addAttribute("bookings", bookingService.findAllByShopId(shopId));
        }


        return "managementDashboard";
    }

}
