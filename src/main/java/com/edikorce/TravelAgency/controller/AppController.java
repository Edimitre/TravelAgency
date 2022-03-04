package com.edikorce.TravelAgency.controller;

import com.edikorce.TravelAgency.exeption.ContentNotFoundExeption;
import com.edikorce.TravelAgency.model.Packet;
import com.edikorce.TravelAgency.model.Role;
import com.edikorce.TravelAgency.model.User;
import com.edikorce.TravelAgency.service.PacketService;
import com.edikorce.TravelAgency.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@Controller
public class AppController {


	@Autowired
	private  UserService userService;

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

			return "/forms/login";

		}

		return "/home";

	}

	@RequestMapping("/logout")
	public String logOut(Model model){

		model.addAttribute("user", new User());
		return "/forms/login";
	}

	@RequestMapping("/")
	public String rootPage(Model model) {

		List<Packet> packetList = packetService.getAllPackets();
		model.addAttribute("user", new User());
		model.addAttribute("packetList", packetList);
		return "index";
	}

	@RequestMapping("/home")
	public String viewHomePage(Model model) {

		List<Packet> packetList = packetService.getAllPackets();
		model.addAttribute("user", new User());
		model.addAttribute("packetList", packetList);
		return "index";
	}

	@RequestMapping("/process_register")
	public String processRegister(User user, Model model) {

		// user qe po regjistrohet kontrollehet eshte apo jo ne database
		boolean userExist = userService.userExist(user.getUsername());
		if (userExist){
			model.addAttribute("message", "Kjo llogari ekziston \nJu Lutem vendosni te dhenat perseri");

		}else{
			// nqs user nuk eshte ne database ruaje ne te kundert mos e ruaj por shfaq mesazh

			userService.registerDefaultUser(user);
			model.addAttribute("message", "Regjistrimi u krye me Sukses\nJu Lutem Logohuni");

		}


		return "/forms/login";
	}

	@GetMapping("/users")
	public String listUsers(Model model) {

		model.addAttribute("user", new User());
		List<User> listUsers = userService.listAll();
		model.addAttribute("listUsers", listUsers);
		// todo hmtl per all users
		return "/listview/all_users";
	}
	
	@GetMapping("/users/edit/{id}")
	public String editUser(@PathVariable("id") Long id, Model model) {
		User user = userService.get(id);
		List<Role> listRoles = userService.listRoles();
		model.addAttribute("user", user);
		model.addAttribute("listRoles", listRoles);
		return "/forms/user_form";
	}
	
	@PostMapping("/users/save")
	public String saveUser(User user) {
		userService.save(user);
		return "redirect:/users";
	}

	@GetMapping("/packet/all")
	public String showAllPackets(Model model){



		model.addAttribute("user", new User());
		model.addAttribute("packetList", packetService.getAllPackets());
		return "/listview/all_packets";
	}

	@GetMapping("/packet/offer")
	public String showOfferPackets(Model model){

		model.addAttribute("packetList", packetService.getOfferPackets());
		return "/listview/all_packets";
	}

	@RequestMapping("/packet/profile/{id}")
	@Transactional
	public String packetProfile(@PathVariable(value = "id") Long id, Model model){
		try {

			Packet packet = packetService.getPacketById(id);
			model.addAttribute("packet", packet);


			boolean isUserOnTheList = userService.isUserOnTheList(packet.getUserList());

			System.out.println(isUserOnTheList);
			model.addAttribute("isUserOnTheList", isUserOnTheList);

			return "/profiles/packet_profile";
		} catch (ContentNotFoundExeption e) {
			e.printStackTrace();
		}
		return "/profiles/packet_profile";
	}

	@GetMapping("/packet/mine")
	public String myPackets(Model model){


		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String loggedUserName = authentication.getName();

		User loggedUser = userService.getByName(loggedUserName);

		if (loggedUser !=null){



			model.addAttribute("packetList", loggedUser.getPacketList());
		}

		return "/listview/my_packets";
	}

	@RequestMapping("/packet/search")
	public String showSearchedPackets(@RequestParam(value = "keyword") String keyword, Model model){

		System.out.println(packetService.getPacketsByKeyword(keyword));
		model.addAttribute("packetList", packetService.getPacketsByKeyword(keyword));
		return "/listview/all_packets";
	}


}
