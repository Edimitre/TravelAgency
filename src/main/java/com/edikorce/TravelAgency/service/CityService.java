package com.edikorce.TravelAgency.service;


import com.edikorce.TravelAgency.exeption.ContentNotFoundExeption;
import com.edikorce.TravelAgency.model.City;
import com.edikorce.TravelAgency.model.Country;
import com.edikorce.TravelAgency.repository.CityRepository;
import com.edikorce.TravelAgency.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityService {
    @Autowired
    private CityRepository cityRepository;

    public City saveCity(City city){
        return cityRepository.save(city);
    }

    public List<City> getAllCities(){

        List<City> allCitiesList = cityRepository.findAll();
        return allCitiesList;
    }

    public List<City> saveAllCities(List<City> cityList){

        return cityRepository.saveAll(cityList);
    }

    public City getCityById(Long id) throws ContentNotFoundExeption {
        Optional<City> city = cityRepository.findById(id);

            if (city.isPresent()){
                return city.get();
            }else{
                throw new ContentNotFoundExeption("Qyteti nuk u gjet");
            }
    }

    public void deleteCityById(Long id){

       cityRepository.deleteById(id);
    }

}
