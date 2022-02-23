package com.edikorce.TravelAgency.repository;

import com.edikorce.TravelAgency.model.Packet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PacketRepository extends JpaRepository<Packet, Long> {


    @Query("SELECT p FROM Packet p where p.isOffer = true")
    List<Packet> getOfferPackets();

//    @Query("SELECT p FROM Packet p WHERE CONCAT(p.name, p.city.name, p.isOffer, p.nrOfTimesBooked) LIKE %?1%")
//    List<Packet> getFilteredPackets(String keyword);
}