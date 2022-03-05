package com.edikorce.TravelAgency;

import com.edikorce.TravelAgency.model.*;
import com.edikorce.TravelAgency.repository.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
class MyTravelAgencyApplicationTests {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PacketRepository packetRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private ContinentRepository continentRepository;

	@Autowired
	private CountryRepository countryRepository;

	@Autowired
	private CityRepository cityRepository;

	@Test
	public void testCreateContinents(){

		Continent antarktida = new Continent();
		antarktida.setName("ANTARKTIDA");


		Assertions.assertThat(continentRepository.save(antarktida)).isInstanceOf(Continent.class);

	}

	@Test
	public void testCreateRole(){

		Role role = new Role();

		role.setName("ROLE_USER");

		Assertions.assertThat(roleRepository.save(role)).isInstanceOf(Role.class);

	}

	@Test
	public void testCreateCity(){


		City city = new City();
		city.setName("Korce");

		Country country = countryRepository.getById(1L);
		city.setCountry(country);



		Assertions.assertThat(cityRepository.save(city)).isInstanceOf(City.class);


	}

	@Test
	public void deleteCityById(){

		City city = cityRepository.getById(2L);

		cityRepository.delete(city);

		Assertions.assertThat(city).isInstanceOf(City.class);
	}

	@Test
	public void testCreateUser(){

		User user = new User();

		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		String password = "edi";
		String bcryptedPwd = bCryptPasswordEncoder.encode(password);
		user.setPassword(bcryptedPwd);

		user.setName("Edi");

		user.setUsername("edimitre");


		Role role = roleRepository.findByName("ROLE_ADMIN");


		user.getRoles().add(role);



		Assertions.assertThat(userRepository.save(user)).isInstanceOf(User.class);

	}

	@Test
	public void testGetUser(){

		Optional<User> user = userRepository.findById(1l);

		if (user.isPresent()){
			Assertions.assertThat(user).isInstanceOf(User.class);
		}

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

	@Test
	public void deleteUserById(){

		Optional<User> user = userRepository.findById(6L);

		if (user.isPresent()){
			userRepository.delete(user.get());
		}
		Assertions.assertThat(user).isNotEmpty();


	}

}