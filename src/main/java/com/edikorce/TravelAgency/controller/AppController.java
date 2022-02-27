package com.edikorce.TravelAgency.controller;

import com.edikorce.TravelAgency.exeption.ContentNotFoundExeption;
import com.edikorce.TravelAgency.model.Packet;
import com.edikorce.TravelAgency.model.Role;
import com.edikorce.TravelAgency.model.User;
import com.edikorce.TravelAgency.security.AuthenticationSystem;
import com.edikorce.TravelAgency.service.PacketService;
import com.edikorce.TravelAgency.service.UserService;
import com.sun.xml.bind.v2.runtime.reflect.Lister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AppController {

	@Autowired
	private UserService service;

	@Autowired
	private PacketService packetService;


	@ModelAttribute(name = "user")
	public  User newUser(){
		return new User();
	}


	@RequestMapping("/login")
	public String login(){

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication == null || authentication instanceof AnonymousAuthenticationToken){

			return "login";

		}

		return "/home";

	}

	@RequestMapping("/logout")
	public String logOut(Model model){

		model.addAttribute("user", new User());
		return "login";
	}

	@RequestMapping("/home")
	public String viewHomePage(Model model) {

		List<Packet> packetList = packetService.getAllPackets();
		model.addAttribute("packetList", packetList);
		return "index";
	}

	@RequestMapping("/process_register")
	public String processRegister(User user) {


		service.registerDefaultUser(user);
		return "redirect:/login";
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

	@GetMapping("/packet/all")
	public String showAllPackets(Model model){

		model.addAttribute("user", new User());
		model.addAttribute("packetList", packetService.getAllPackets());
		return "all_packets";
	}

	@GetMapping("/packet/offer")
	private String showOfferPackets(Model model){

		model.addAttribute("packetList", packetService.getOfferPackets());
		return "all_packets";
	}

	@RequestMapping("/packet/profile/{id}")
	public String packetProfile(@PathVariable(value = "id") Long id, Model model){
		try {
			Packet packet = packetService.getPacketById(id);
			model.addAttribute("packet", packet);
			return "packet_profile";
		} catch (ContentNotFoundExeption e) {
			e.printStackTrace();
		}
		return "packet_profile";
	}

	@GetMapping("/packet/mine")
	public String myPackets(Model model){


		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String loggedUserName = authentication.getName();

		User loggedUser = service.getByName(loggedUserName);

		if (loggedUser !=null){


			model.addAttribute("packetList", loggedUser.getPacketList());
		}

		return "all_packets";
	}

	@RequestMapping("/packet/search")
	public String showSearchedPackets(@RequestParam(value = "keyword") String keyword, Model model){

		System.out.println(packetService.getPacketsByKeyword(keyword));
		model.addAttribute("packetList", packetService.getPacketsByKeyword(keyword));
		return "all_packets";
	}

}
