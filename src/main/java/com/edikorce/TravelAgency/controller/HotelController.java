package com.edikorce.TravelAgency.controller;


import com.edikorce.TravelAgency.exeption.ContentNotFoundExeption;
import com.edikorce.TravelAgency.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hotel")
public class HotelController {
    @Autowired
    private HotelService hotelService;

    @PostMapping("/save")
    public Hotel saveHotel(@RequestBody Hotel hotel){

        return hotelService.saveHotel(hotel);
    }

    @GetMapping("/get/{id}")
    public Hotel getHotelById(@PathVariable Long id) throws ContentNotFoundExeption {


        return hotelService.getHotelById(id);
    }

    @PostMapping("/update/{id}")
    public Hotel updateHotel(@PathVariable Long id) throws ContentNotFoundExeption {
        Hotel hotel = hotelService.getHotelById(id);


        return hotelService.saveHotel(hotel);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteHotelById(@PathVariable Long id){

        hotelService.deleteHotelById(id);
    }
}
