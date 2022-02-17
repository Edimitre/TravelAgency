package com.edikorce.TravelAgency.controller;


import com.edikorce.TravelAgency.model.User;
import com.edikorce.TravelAgency.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;


@Controller
public class HomeController {

    // default User
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
        user1.setAdmin(true);
        user = userService.saveUser(user1);
        return "redirect:/";
    }


    @RequestMapping(value = "/logged/in")
    public String loggedIn(@RequestParam(value = "userName") String userName,
                           @RequestParam(value = "password") String password,
                           RedirectAttributes redirAttrs){

        user = userService.getUserByUsernameAndPassword(userName, password);

        if (user != null){
            return "redirect:/";
        }else{
            redirAttrs.addFlashAttribute("message", "Te Dhenat Jane Vendosur Gabim");

            user = userService.getEmptyUser();
            return "redirect:/";
        }

    }

    @RequestMapping("/logout")
    public String logout(){

        user = userService.getEmptyUser();

        return "redirect:/";
    }

    // todo vazhdo me funksionet e editimit te userit



}
