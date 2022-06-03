package com.ddimitko.prototype.controllers;

import com.ddimitko.prototype.objects.User;
import com.ddimitko.prototype.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SecurityController {

    @Autowired
    UserService userService;

    @GetMapping("/")
    public String landing(Authentication authentication){

        if(authentication != null){
            return "redirect:/home";
        }

        return "index";

    }

    @GetMapping("/login")
    public String showLogin(Authentication authentication){

        if(authentication != null){
            return "redirect:/home";
        }

        return "login";
    }

    @GetMapping("/signup")
    public String showSignUp(Authentication authentication, Model model){

        if(authentication != null){
            return "redirect:/home";
        }
        model.addAttribute("user", new User());

        return "signup";
    }

    @PostMapping("/process_signup")
    public String processSignUp(User user){

        if(userService.findByEmail(user.getEmail()).isEmpty()) {

            userService.createUser(user);

            return "redirect:/home";
        }
        else {
            return "signup";
        }

    }

}
