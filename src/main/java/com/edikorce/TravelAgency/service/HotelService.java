package com.edikorce.TravelAgency.service;


import com.edikorce.TravelAgency.exeption.ContentNotFoundExeption;
import com.edikorce.TravelAgency.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelService {
    @Autowired
    private HotelRepository hotelRepository;

    public Hotel saveHotel(Hotel hotel){
        return hotelRepository.save(hotel);
    }

    public List<Hotel> getAllHotels(){

        List<Hotel> allHotelsList = hotelRepository.findAll();
        return allHotelsList;
    }

    public List<Hotel> saveAllHotels(List<Hotel> hotelList){

        return hotelRepository.saveAll(hotelList);
    }

    public Hotel getHotelById(Long id) throws ContentNotFoundExeption {
        Optional<Hotel> hotel = hotelRepository.findById(id);

            if (hotel.isPresent()){
                return hotel.get();
            }else{
                throw new ContentNotFoundExeption("hoteli nuk u gjend");
            }
    }

    public void deleteHotelById(Long id){

       hotelRepository.deleteById(id);
    }

}
