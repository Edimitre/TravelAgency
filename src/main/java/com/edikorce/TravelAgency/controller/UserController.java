package com.edikorce.TravelAgency.controller;


import com.edikorce.TravelAgency.exeption.ContentNotFoundExeption;
import com.edikorce.TravelAgency.model.User;
import com.edikorce.TravelAgency.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;







    @GetMapping("/get/{id}")
    public User getUserById(@PathVariable Long id) throws ContentNotFoundExeption {


        return userService.getUserById(id);
    }

    @PostMapping("/update/{id}")
    public User updateUser(@PathVariable Long id) throws ContentNotFoundExeption {
        User user = userService.getUserById(id);


        return userService.saveUser(user);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUserById(@PathVariable Long id){

        userService.deleteUserById(id);
    }
}
