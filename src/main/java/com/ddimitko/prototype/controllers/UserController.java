package com.ddimitko.prototype.controllers;

import com.ddimitko.prototype.objects.Booking;
import com.ddimitko.prototype.objects.User;
import com.ddimitko.prototype.services.BookingService;
import com.ddimitko.prototype.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    BookingService bookingService;

    /*@GetMapping("/editProfile")
    public String viewEditProfile(@RequestParam Long userId, Model model){

        User user = new User();

        if(userService.findById(userId).isPresent()){
            user = userService.findById(userId).get();
        }
        model.addAttribute("user", user);


        return "editProfile";
    }*/

    @GetMapping("/viewBookings")
    public String viewBookings(@RequestParam Long userId, Model model){

        List<Booking> bookings = bookingService.findAllByUserId(userId);
        model.addAttribute("bookings", bookings);

        return "viewBookings";
    }

    @DeleteMapping("/deleteBooking")
    public String deleteBooking(@RequestParam(value="bookingId") Long bookingId){

        Booking booking = new Booking();
        if(bookingService.findById(bookingId).isPresent()){
            booking = bookingService.findById(bookingId).get();
        }

        bookingService.deleteBooking(booking);

        return "redirect:/home";
    }

    /*@PutMapping("/editProfile")
    public String editProfile(@RequestParam String email,
                            @RequestParam(value="oldPass") String oldPassword,
                            @RequestParam(value="newPass") String newPassword){

        userService.updateUser(email, oldPassword, newPassword);

        return "redirect:/home";

    }*/

}
