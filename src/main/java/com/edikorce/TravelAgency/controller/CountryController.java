package com.edikorce.TravelAgency.controller;


import com.edikorce.TravelAgency.exeption.ContentNotFoundExeption;
import com.edikorce.TravelAgency.model.Continent;
import com.edikorce.TravelAgency.model.Country;
import com.edikorce.TravelAgency.service.ContinentService;
import com.edikorce.TravelAgency.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/country")
public class CountryController {
    @Autowired
    private CountryService countryService;
    @Autowired
    private ContinentService continentService;

    @RequestMapping("/save")
    public String saveCountry(Country country){


        countryService.saveCountry(country);
        return "redirect:/admin/home";
    }

    @GetMapping("/add/form")
    public String showCountryAddForm(Model model){

        List<Continent> allContinents= continentService.getAllContinents();


        model.addAttribute("country", new Country());

        model.addAttribute("allContinents", allContinents);
        return "add_country_form";
    }

    @GetMapping("/all")
    public String showAllCountries(Model model){
        model.addAttribute("country", new Country());
        List<Country> countryList = countryService.getAllCountries();
        model.addAttribute("countryList", countryList);
        return "all_countries";
    }

    @GetMapping("/get/{id}")
    public Country getCountryById(@PathVariable Long id) throws ContentNotFoundExeption {


        return countryService.getCountryById(id);
    }

    @RequestMapping("/edit/{id}")
    public Country updateCountry(@PathVariable Long id) throws ContentNotFoundExeption {
        Country country = countryService.getCountryById(id);

        return countryService.saveCountry(country);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCountryById(@PathVariable Long id){

        countryService.deleteCountryById(id);
    }

}
