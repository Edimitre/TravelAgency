package com.edikorce.TravelAgency.service;

import com.edikorce.TravelAgency.exeption.ContentNotFoundExeption;
import com.edikorce.TravelAgency.model.Continent;
import com.edikorce.TravelAgency.repository.ContinentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContinentService {
    @Autowired
    private ContinentRepository continentRepository;

    public Continent saveContinent(Continent continent){
        return continentRepository.save(continent);
    }

    public Continent getContinentByName(String continentName){

        return continentRepository.getContinentByName(continentName);
    }

    public List<Continent> getAllContinents(){

        List<Continent> allContinentsList = continentRepository.findAll();
        return allContinentsList;
    }

    public List<Continent> saveAllContinents(List<Continent> continentList){

        return continentRepository.saveAll(continentList);
    }

    public Continent getContinentById(Long id) throws ContentNotFoundExeption {
        Optional<Continent> continent = continentRepository.findById(id);

        if (continent.isPresent()){
            return continent.get();
        }else{
            throw new ContentNotFoundExeption("Kontinenti nuku gjet");
        }
    }

    public void deleteContinentById(Long id){

        continentRepository.deleteById(id);
    }

}