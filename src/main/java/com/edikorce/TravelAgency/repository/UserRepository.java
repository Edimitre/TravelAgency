package com.edikorce.TravelAgency.repository;

import com.edikorce.TravelAgency.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE name = :name and password = :password")
    User getUserByNameAndPassword(@Param(value = "name") String name, @Param(value="password") String password);

}
