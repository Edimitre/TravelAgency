package com.edikorce.TravelAgency.controller;


import com.edikorce.TravelAgency.exeption.ContentNotFoundExeption;
import com.edikorce.TravelAgency.model.City;
import com.edikorce.TravelAgency.model.Continent;
import com.edikorce.TravelAgency.model.Country;
import com.edikorce.TravelAgency.service.CityService;
import com.edikorce.TravelAgency.service.ContinentService;
import com.edikorce.TravelAgency.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/city")
public class CityController {
    @Autowired
    private CityService cityService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private ContinentService continentService;





    @RequestMapping("/addform")
    public String showAddCityForm(Model model){

        List<Country> countryList = countryService.getAllCountries();
        List<Continent> continentList = continentService.getAllContinents();

        model.addAttribute("continentList", continentList);
        model.addAttribute("city", new City());
        model.addAttribute("country", new Country());
        model.addAttribute("continent", new Continent());
        model.addAttribute("countryList", countryList);


        model.addAttribute("pageTitle", "Shto Qytet");
        return "add_city_form";
    }

    @PostMapping("/save")
    public String saveCity(City city) throws IOException {


        cityService.saveCity(city);
        return "redirect:/admin/home";
    }

    @GetMapping("/all")
    public String showAllCities(Model model){
        List<City> cityList = cityService.getAllCities();
        model.addAttribute("cityList", cityList);
        return "all_cities";
    }

    @RequestMapping("/edit/{id}")
    public String showEditCityForm(@PathVariable(value = "id")Long id, Model model) throws ContentNotFoundExeption {


        List<Country> countryList = countryService.getAllCountries();

        City city  = cityService.getCityById(id);

        model.addAttribute("city", city);
        model.addAttribute("country", new Country());
        model.addAttribute("continent", new Continent());
        model.addAttribute("countryList", countryList);


        model.addAttribute("pageTitle", "Edito Qytetin " + city.getName());

        return "add_city_form";

    }




}
