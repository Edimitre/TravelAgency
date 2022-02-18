package com.edikorce.TravelAgency.controller;


import com.edikorce.TravelAgency.model.City;
import com.edikorce.TravelAgency.model.Packet;
import com.edikorce.TravelAgency.service.CityService;
import com.edikorce.TravelAgency.service.PacketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/packet")
public class PacketController {


    @Autowired
    private PacketService packetService;

    @Autowired
    private CityService cityService;

    @GetMapping("/form")
    private String showPacketForm(Model model){

        List<City> allCityList = cityService.getAllCities();
        model.addAttribute("allCityList", allCityList);
        model.addAttribute("packet", new Packet());
        return"add_packet_form";
    }

    @PostMapping("/save")
    public String savePacket(Packet packet){


        packetService.savePacket(packet);
        return "redirect:/admin/home";
    }

    @GetMapping("/all")
    public String showAllPackets(Model model){

        List<Packet> packetList = packetService.getAllPackets();
        model.addAttribute("packetList", packetList);
        return "all_packets";

    }



}
