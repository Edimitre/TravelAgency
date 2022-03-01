package com.edikorce.TravelAgency.repository;

import com.edikorce.TravelAgency.model.Packet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PacketRepository extends JpaRepository<Packet, Long> {


    @Query("SELECT p FROM Packet p where p.isOffer = true")
    List<Packet> getOfferPackets();

    @Query("SELECT p FROM Packet p WHERE p.city.name LIKE %?1% OR p.city.country.continent.name LIKE %?1% OR p.city.country.name LIKE %?1%")
    List<Packet> getPacketsByKeyword(String keyword);

//
//    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Packet p WHERE p.name = :companyName")
//    boolean existsByName(@Param("companyName") String companyName);
//    @Query("SELECT p FROM Packet p WHERE CONCAT(p.name, p.city.name, p.isOffer, p.nrOfTimesBooked) LIKE %?1%")
//    List<Packet> getFilteredPackets(String keyword);
}