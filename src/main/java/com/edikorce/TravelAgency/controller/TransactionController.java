package com.edikorce.TravelAgency.controller;

import com.edikorce.TravelAgency.exeption.ContentNotFoundExeption;
import com.edikorce.TravelAgency.model.Packet;
import com.edikorce.TravelAgency.model.User;
import com.edikorce.TravelAgency.service.PacketService;
import com.edikorce.TravelAgency.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private UserService userService;

    @Autowired
    private PacketService packetService;

    @RequestMapping("/buy/packet/{id}")
    public String addPacketToUser(@PathVariable(value = "id") Long id) throws ContentNotFoundExeption {


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String LoggedUserName = authentication.getName();

        // get user
        User loggedUser = userService.getByName(LoggedUserName);
        // getpacket

        Packet packet = packetService.getPacketById(id);


        // add packet to user // user to packet
        loggedUser.getPacketsList().add(packet);
        // kompleto pakten
        // vendos nr e klieneteve qe kane prenotuar
        int usersInPacket = packet.getUserList().size();
        packet.setNrOfTimesBooked(usersInPacket + 1);
        packet.getUserList().add(loggedUser);
        // save edited user
        userService.save(loggedUser);
        // redirect my packets in the my profile section (must be done in html)
        // todo return my profile
        return "all_packets";



    }
}
