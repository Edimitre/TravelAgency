package com.edikorce.TravelAgency;

import com.edikorce.TravelAgency.model.Role;
import com.edikorce.TravelAgency.model.User;
import com.edikorce.TravelAgency.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
class MyTravelAgencyApplicationTests {

	@Autowired
	private UserRepository service;

	@Test
	public void testCreateUser(){

		User user = new User();

		String password = "edi";
		BCryptPasswordEncoder pswdencoder = new BCryptPasswordEncoder();
		String encodedpwd = pswdencoder.encode(password);

		user.setPassword(encodedpwd);

		user.setName("edi");



		Role role = new Role();
		role.setName("ROLE_ADMIN");
		user.addRole(role);



		Assertions.assertThat(service.save(user)).isInstanceOf(User.class);

	}

}