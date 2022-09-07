package com.ddimitko.prototype.controllers;

import com.ddimitko.prototype.objects.User;
import com.ddimitko.prototype.services.ShopService;
import com.ddimitko.prototype.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.transaction.Transactional;

@Controller
public class SecurityController {

    @Autowired
    UserService userService;

    @Autowired
    ShopService shopService;

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
        else {
            model.addAttribute("staff", new User());
            model.addAttribute("shopList", shopService.findAll());

            return "signup";
        }
    }

    @RequestMapping(value = "/management/login", method = { RequestMethod.GET, RequestMethod.POST })
    public String showManagementLogin(){

        return "managementLogin";
    }

    @PostMapping("/process_signup")
    public String processSignUp(User user){

        userService.createUser(user);

        return "redirect:/management";

    }

}
