package com.edikorce.TravelAgency.controller;


import com.edikorce.TravelAgency.exeption.ContentNotFoundExeption;
import com.edikorce.TravelAgency.model.Continent;
import com.edikorce.TravelAgency.service.ContinentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/continent")
public class ContinentController {
    @Autowired
    private ContinentService continentService;

    @PostMapping("/save")
    public Continent saveContinent(@RequestBody Continent continent){

        return continentService.saveContinent(continent);
    }

    @GetMapping("/get/{id}")
    public Continent getContinentById(@PathVariable Long id) throws ContentNotFoundExeption {


        return continentService.getContinentById(id);
    }

    @PostMapping("/update/{id}")
    public Continent updateContinent(@PathVariable Long id) throws ContentNotFoundExeption {
        Continent continent = continentService.getContinentById(id);

        return continentService.saveContinent(continent);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteContinentById(@PathVariable Long id){

        continentService.deleteContinentById(id);
    }
}
