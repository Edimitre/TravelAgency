package com.edikorce.TravelAgency.controller;

import com.edikorce.TravelAgency.model.Packet;
import com.edikorce.TravelAgency.model.Role;
import com.edikorce.TravelAgency.model.User;
import com.edikorce.TravelAgency.service.PacketService;
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
public class AppController {

	@Autowired
	private UserService service;

	@Autowired
	private PacketService packetService;
	
	@GetMapping("/")
	public String viewHomePage(Model model) {

		List<Packet> packetList = packetService.getOfferPackets();

		model.addAttribute("packetList", packetList);

		return "index";
	}
	
	@RequestMapping("/register")
	public String showRegistrationForm(Model model) {

		model.addAttribute("user", new User());
		return "signup_form";
	}
	
	@RequestMapping("/process_register")
	public String processRegister(Model model,User user) {

		model.addAttribute("user", new User());
		service.registerDefaultUser(user);

		return "register_success";
	}
	
	@GetMapping("/users")
	public String listUsers(Model model) {

		model.addAttribute("user", new User());
		List<User> listUsers = service.listAll();
		model.addAttribute("listUsers", listUsers);
		
		return "users";
	}
	
	@GetMapping("/users/edit/{id}")
	public String editUser(@PathVariable("id") Long id, Model model) {
		User user = service.get(id);
		List<Role> listRoles = service.listRoles();
		model.addAttribute("user", user);
		model.addAttribute("listRoles", listRoles);
		return "user_form";
	}
	
	@PostMapping("/users/save")
	public String saveUser(User user) {
		service.save(user);
		
		return "redirect:/users";
	}

	@GetMapping("/packets/all")
	public String showAllPackets(Model model){

		// todo show list of all packages

		List<Packet> packetList = packetService.getAllPackets();
		model.addAttribute("user", new User());
		model.addAttribute("packetList", packetList);
		return "all_packets";
	}

	@GetMapping("/packets/offer")
	private String showOfferPackets(Model model){


		List<Packet> packetList = packetService.getOfferPackets();
		model.addAttribute("packetList", packetList);
		return "all_packets";
	}



}
