package com.edikorce.TravelAgency.service;

import com.edikorce.TravelAgency.model.Packet;
import com.edikorce.TravelAgency.model.Role;
import com.edikorce.TravelAgency.model.User;
import com.edikorce.TravelAgency.repository.RoleRepository;
import com.edikorce.TravelAgency.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private RoleRepository roleRepo;
	
	@Autowired PasswordEncoder passwordEncoder;


	public void registerDefaultUser(User user) {

		Role roleUser = roleRepo.findByName("ROLE_ADMIN");

		user.addRole(roleUser);

		List<Packet> packets = new ArrayList<>();
		user.setPacketList(packets);

		encodePassword(user);

		userRepo.save(user);
	}

	public List<User> listAll() {
		return userRepo.findAll();
	}

	public User get(Long id) {
		Optional<User> user = userRepo.findById(id);
		return user.orElseGet(User::new);

	}
	
	public List<Role> listRoles() {
		return roleRepo.findAll();
	}
	
	public void save(User user) {

		userRepo.save(user);
	}

	public void deleteUserById(Long id) {
		Optional<User> user = userRepo.findById(id);
		user.ifPresent(value -> userRepo.delete(value));
	}

	public User getLoggedUser(){

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String loggedUserName = authentication.getName();

		return userRepo.findByName(loggedUserName);
	}

	private void encodePassword(User user) {
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);		
	}

	public User getByName(String name){

		return userRepo.findByName(name);
	}

	public boolean userExist(String password){

		return userRepo.findByUsername(password) != null;
	}

	public boolean isUserOnTheList(List<User> userList){

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String loggedUserName = authentication.getName();

		User loggedUser = userRepo.findByName(loggedUserName);
		return userList.contains(loggedUser);
	}

	public void addPacket(Packet packet){

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String loggedUserName = authentication.getName();

		User loggedUser = userRepo.findByName(loggedUserName);

		if (loggedUser != null){


			loggedUser.getPacketList().add(packet);



			// users math
			int usersInPacket = packet.getUserList().size();
			packet.setNrOfTimesBooked(usersInPacket + 1);

//			money math
			loggedUser.setAmountOfMoney(loggedUser.getAmountOfMoney() - packet.getPrice());



			// save edited user
			save(loggedUser);
		}
	}

	public void removePacket(Packet packet){

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String loggedUserName = authentication.getName();

		User loggedUser = userRepo.findByName(loggedUserName);


		// users math
		int usersInPacket = packet.getUserList().size();
		packet.setNrOfTimesBooked(usersInPacket - 1);

		// money math

		loggedUser.setAmountOfMoney(loggedUser.getAmountOfMoney() + packet.getPrice());



		loggedUser.getPacketList().remove(packet);

		save(loggedUser);
	}
}
