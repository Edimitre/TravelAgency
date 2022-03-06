package com.edikorce.TravelAgency.service;

import com.edikorce.TravelAgency.exeption.ContentNotFoundExeption;
import com.edikorce.TravelAgency.model.City;
import com.edikorce.TravelAgency.model.Order;
import com.edikorce.TravelAgency.model.Packet;
import com.edikorce.TravelAgency.repository.CityRepository;
import com.edikorce.TravelAgency.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserService userService;


    public Order saveOrder(Order order){

        return orderRepository.save(order);
    }


    @Transactional
    public List<Order> getAllOrders(){

        List<Order> allOrders = orderRepository.findAll();
        return allOrders;
    }


    public Order getOrderById(Long id) throws ContentNotFoundExeption {

        Optional<Order> order = orderRepository.findById(id);

        if (order.isPresent()){
            return order.get();
        }else{
            throw new ContentNotFoundExeption("Porosia nuk u gjet");
        }
    }


    public void deleteOrder(Order order){

        orderRepository.delete(order);
    }


    public void approveOrder(Order order){
        // per te derguar email
        String email = order.getUser().getEmail();

        userService.addPacket(order.getPacket());
    }

}