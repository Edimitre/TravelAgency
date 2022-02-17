package com.edikorce.TravelAgency.controller;


import com.edikorce.TravelAgency.exeption.ContentNotFoundExeption;
import com.edikorce.TravelAgency.model.Continent;
import com.edikorce.TravelAgency.model.Country;
import com.edikorce.TravelAgency.service.ContinentService;
import com.edikorce.TravelAgency.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/country")
public class CountryController {
    @Autowired
    private CountryService countryService;
    @Autowired
    private ContinentService continentService;

    @RequestMapping("/save")
    public String saveCountry(@RequestParam(value = "countryName") String countryName, @RequestParam(value = "continentName") String continentName){

        Continent continent = continentService.getContinentByName(continentName);

        Country country = new Country();
        country.setName(countryName);
        country.setContinent(continent);
        countryService.saveCountry(country);
        return "redirect:/admin/home";
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
