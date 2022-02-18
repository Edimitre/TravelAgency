package com.edikorce.TravelAgency.controller;


import com.edikorce.TravelAgency.exeption.ContentNotFoundExeption;
import com.edikorce.TravelAgency.model.City;
import com.edikorce.TravelAgency.model.Country;
import com.edikorce.TravelAgency.model.Packet;
import com.edikorce.TravelAgency.model.User;
import com.edikorce.TravelAgency.service.CityService;
import com.edikorce.TravelAgency.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {


    @Autowired
    private UserService userService;
    @Autowired
    private CityService cityService;

    @GetMapping("/home")
    public String home(){




        return "admin_page";
    }





    @GetMapping("/users/all")
    public String showAllUsers(Model model){

        List<User> usersList = userService.getAllUsers();

        model.addAttribute("userList", usersList);
        return "all_users";
    }

    @RequestMapping("/user/edit/{id}")
    public String showEditUserForm(@PathVariable(value = "id")Long id, Model model) throws ContentNotFoundExeption {

        User user = userService.getUserById(id);

        model.addAttribute("user", user);

        return "edit_user_form";

    }

    @PostMapping("/user/edited/save")
    public String saveEditedUser(User user){

        user.setAnonymous(false);

        user = userService.saveUser(user);
        return "redirect:/admin/users/all";
    }


}
