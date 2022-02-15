package com.edikorce.TravelAgency.controller;


import com.edikorce.TravelAgency.model.User;
import com.edikorce.TravelAgency.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class HomeController {

    // default user
    User user = new User(0L, "","" ,true,false,new ArrayList<>());


    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String home(Model model){



        model.addAttribute("user", user);
        return "index";
    }

    @GetMapping("/user/register")
    public String registerUser(Model model){

        model.addAttribute("user", new User());
        return "register_user_form";
    }

    @PostMapping("/user/save")
    public String saveUser(User user1){

        user1.setAnonymous(false);
        user1.setAdmin(false);
        user = userService.saveUser(user1);
        return "redirect:/";
    }

    @RequestMapping("/login/form")
    public String showLoginForm(){



        return "login_user_form";
    }

    @RequestMapping(value = "/logged/in")
    public String loggedIn(@RequestParam(value = "userName") String userName, @RequestParam(value = "password") String password){

        if (user != null){
            user = userService.getUserByUsernameAndPassword(userName, password);
        }
        return "redirect:/";
    }

    @RequestMapping("/logout")
    public String logout(){

        user = new User(0L, "","" ,true, false, new ArrayList<>());

        return "redirect:/";
    }

}
