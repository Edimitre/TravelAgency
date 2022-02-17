package com.edikorce.TravelAgency.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {


    @GetMapping("/home")
    public String home(){




        return "admin_page";
    }

    @GetMapping("/allPackets")
    public String showAllPackets(Model model){



        return "packets_page";

    }


}
