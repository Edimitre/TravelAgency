package com.edikorce.TravelAgency.repository;

import com.edikorce.TravelAgency.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    @Query("SELECT c FROM City c WHERE name = :name")
    City getCityByName(@Param(value = "name") String name);
}