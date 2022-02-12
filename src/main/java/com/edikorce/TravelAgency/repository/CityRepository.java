package com.edikorce.TravelAgency.repository;

import com.edikorce.TravelAgency.model.City;
import com.edikorce.TravelAgency.model.Continent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
}
