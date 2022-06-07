package com.ddimitko.prototype.controllers;

import com.ddimitko.prototype.objects.User;
import com.ddimitko.prototype.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/editProfile")
    public String viewEditProfile(@RequestParam Long userId, Model model){

        User user = new User();

        if(userService.findById(userId).isPresent()){
            user = userService.findById(userId).get();
        }

        model.addAttribute("user", user);

        return "editProfile";

    }

    @PostMapping("/editProfile")
    public void editProfile(@RequestParam String email,
                            @RequestParam(value="oldPass") String oldPassword,
                            @RequestParam(value="newPass") String newPassword){

        userService.updateUser(email, oldPassword, newPassword);

    }

}
