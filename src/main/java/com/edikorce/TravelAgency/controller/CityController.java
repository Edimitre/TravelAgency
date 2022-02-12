package com.edikorce.TravelAgency.controller;


import com.edikorce.TravelAgency.exeption.ContentNotFoundExeption;
import com.edikorce.TravelAgency.model.City;
import com.edikorce.TravelAgency.model.Continent;
import com.edikorce.TravelAgency.service.CityService;
import com.edikorce.TravelAgency.service.ContinentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/city")
public class CityController {
    @Autowired
    private CityService cityService;

    @PostMapping("/save")
    public City saveCity(@RequestBody City city){

        return cityService.saveCity(city);
    }

    @GetMapping("/get/{id}")
    public City getCityById(@PathVariable Long id) throws ContentNotFoundExeption {


        return cityService.getCityById(id);
    }

    @PostMapping("/update/{id}")
    public City updateCity(@PathVariable Long id) throws ContentNotFoundExeption {
        City city = cityService.getCityById(id);

        return cityService.saveCity(city);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCityById(@PathVariable Long id){

        cityService.deleteCityById(id);
    }
}
