package com.edikorce.TravelAgency.repository;

import com.edikorce.TravelAgency.model.Continent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ContinentRepository extends JpaRepository<Continent, Long> {

    @Query("SELECT c FROM Continent c WHERE name = :name")
    Continent getContinentByName(@Param(value = "name") String name);
}