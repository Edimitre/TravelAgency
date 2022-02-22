package com.edikorce.TravelAgency.repository;

import com.edikorce.TravelAgency.model.Packet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacketRepository extends JpaRepository<Packet, Long> {
}