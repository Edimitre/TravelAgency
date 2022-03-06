package com.edikorce.TravelAgency.repository;

import com.edikorce.TravelAgency.model.Order;
import com.edikorce.TravelAgency.model.Packet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {




}