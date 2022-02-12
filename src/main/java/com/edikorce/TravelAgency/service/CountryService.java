package com.edikorce.TravelAgency.service;


import com.edikorce.TravelAgency.exeption.ContentNotFoundExeption;
import com.edikorce.TravelAgency.model.Continent;
import com.edikorce.TravelAgency.model.Country;
import com.edikorce.TravelAgency.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryService {
    @Autowired
    private CountryRepository countryRepository;

    public Country saveCountry(Country country){
        return countryRepository.save(country);
    }

    public List<Country> getAllCountries(){

        List<Country> allCountriesList = countryRepository.findAll();
        return allCountriesList;
    }

    public List<Country> saveAllCountries(List<Country> countryList){

        return countryRepository.saveAll(countryList);
    }

    public Country getCountryById(Long id) throws ContentNotFoundExeption {
        Optional<Country> country = countryRepository.findById(id);

            if (country.isPresent()){
                return country.get();
            }else{
                throw new ContentNotFoundExeption("Shteti nuk u gjet");
            }
    }

    public void deleteCountryById(Long id){

       countryRepository.deleteById(id);
    }

}
