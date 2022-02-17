package com.edikorce.TravelAgency.repository;

import com.edikorce.TravelAgency.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

    @Query("SELECT c FROM Country c WHERE name = :name")
    Optional<Country> getCountryByName(@Param(value = "name") String name);
}
