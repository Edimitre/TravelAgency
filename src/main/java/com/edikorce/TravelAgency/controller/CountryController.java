package com.edikorce.TravelAgency.controller;


import com.edikorce.TravelAgency.exeption.ContentNotFoundExeption;
import com.edikorce.TravelAgency.model.Country;
import com.edikorce.TravelAgency.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/country")
public class CountryController {
    @Autowired
    private CountryService countryService;

    @PostMapping("/save")
    public Country saveCountry(@RequestBody Country country){

        return countryService.saveCountry(country);
    }

    @GetMapping("/get/{id}")
    public Country getCountryById(@PathVariable Long id) throws ContentNotFoundExeption {


        return countryService.getCountryById(id);
    }

    @PostMapping("/update/{id}")
    public Country updateCountry(@PathVariable Long id) throws ContentNotFoundExeption {
        Country country = countryService.getCountryById(id);

        return countryService.saveCountry(country);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCountryById(@PathVariable Long id){

        countryService.deleteCountryById(id);
    }
}
