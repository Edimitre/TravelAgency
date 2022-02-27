package com.edikorce.TravelAgency.controller;

import com.edikorce.TravelAgency.exeption.ContentNotFoundExeption;
import com.edikorce.TravelAgency.model.Packet;
import com.edikorce.TravelAgency.model.User;
import com.edikorce.TravelAgency.security.AuthenticationSystem;
import com.edikorce.TravelAgency.service.ImageService;
import com.edikorce.TravelAgency.service.PacketService;
import com.edikorce.TravelAgency.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private UserService userService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private PacketService packetService;

    @RequestMapping("/buy/packet/{id}")
    public String addPacketToUser(@PathVariable(value = "id") Long id, Model model) throws ContentNotFoundExeption {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedUserName = authentication.getName();

        User loggedUser = userService.getByName(loggedUserName);

        if (loggedUser != null){
            Packet packet = packetService.getPacketById(id);


            // add packet to user
            loggedUser.getPacketList().add(packet);



            // vendos nr e klieneteve qe kane prenotuar
            int usersInPacket = packet.getUserList().size();

            packet.setNrOfTimesBooked(usersInPacket + 1);

            // save edited user
            userService.save(loggedUser);
            return "redirect:/packet/all";
        }
        // getpacket



        return "redirect:/home";



    }

    @RequestMapping("/image/form")
    public String showImageForm(){

        return "add_image_form";
    }

}
