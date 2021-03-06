package com.edikorce.TravelAgency.controller;

import com.edikorce.TravelAgency.exeption.ContentNotFoundExeption;
import com.edikorce.TravelAgency.model.Order;
import com.edikorce.TravelAgency.model.Packet;
import com.edikorce.TravelAgency.model.User;
import com.edikorce.TravelAgency.service.ImageService;
import com.edikorce.TravelAgency.service.OrderService;
import com.edikorce.TravelAgency.service.PacketService;
import com.edikorce.TravelAgency.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.transaction.Transactional;

@Controller
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private UserService userService;


    @Autowired
    private PacketService packetService;

    @Autowired
    private OrderService orderService;

    @RequestMapping("/place/order/{id}")
    @Transactional
    public String placeOrder(@PathVariable(value = "id") Long id, Model model) throws ContentNotFoundExeption {


        Packet packet = packetService.getPacketById(id);
        User user = userService.getLoggedUser();

        Order order = new Order();
        order.setUser(user);
        order.setPacket(packet);

        orderService.saveOrder(order);

        model.addAttribute("message", "Kerkesa juaj u vendos. \nDo te njoftoheni me email kur kerkesa te pranohet");

        return "order_message";
    }







    @RequestMapping("/buy/packet/{id}")
    @Transactional
    public String addPacketToUser(@PathVariable(value = "id") Long id) throws ContentNotFoundExeption {


        Packet packet = packetService.getPacketById(id);

        userService.addPacket(packet);


        return "redirect:/packet/mine";

    }





    @RequestMapping("/cancel/packet/{id}")
    @Transactional
    public String cancelUserPacket(@PathVariable(value = "id") Long id) throws ContentNotFoundExeption {

        // get packet
        Packet packet = packetService.getPacketById(id);


        userService.removePacket(packet);

        return "redirect:/packet/mine";
    }

}
