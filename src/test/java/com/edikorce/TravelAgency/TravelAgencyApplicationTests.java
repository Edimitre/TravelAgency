package com.edikorce.TravelAgency;

import com.edikorce.TravelAgency.model.Packet;
import com.edikorce.TravelAgency.model.Role;
import com.edikorce.TravelAgency.model.User;
import com.edikorce.TravelAgency.repository.PacketRepository;
import com.edikorce.TravelAgency.repository.RoleRepository;
import com.edikorce.TravelAgency.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
class MyTravelAgencyApplicationTests {

	@Autowired
	private UserRepository service;

	@Autowired
	private PacketRepository packetRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Test
	public void testCreateUser(){

		User user = new User();

		String password = "edi";
		user.setPassword(password);

		user.setName("edi");



		Role role = roleRepository.findByName("ROLE_ADMIN");


		user.getRoles().add(role);



		Assertions.assertThat(service.save(user)).isInstanceOf(User.class);

	}

	@Test
	public void testGetUser(){

		User user = service.findById(1l).get();

		Assertions.assertThat(user).isInstanceOf(User.class);

	}

	@Test
	public void testOfferPackets(){

		List<Packet> packetList = packetRepository.getOfferPackets();

		Assertions.assertThat(packetList.isEmpty()).isFalse();



	}

	@Test
	public void testKeywordPackets(){

		List<Packet> packetList = packetRepository.getPacketsByKeyword("paris");

		Assertions.assertThat(packetList.isEmpty()).isFalse();



	}

}