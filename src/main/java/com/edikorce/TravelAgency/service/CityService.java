package com.edikorce.TravelAgency.service;

import com.edikorce.TravelAgency.exeption.ContentNotFoundExeption;
import com.edikorce.TravelAgency.model.City;
import com.edikorce.TravelAgency.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class CityService {
    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private ImageService imageService;

    public City saveCity(MultipartFile imageFile, City city){
        try {
            String encodedImage = Base64.getEncoder().encodeToString(imageFile.getBytes());
            city.setImage(encodedImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cityRepository.save(city);
    }

    public List<City> getAllCities(){

        List<City> allCitiesList = cityRepository.findAll();
        return allCitiesList;
    }

    public City getCityByName(String cityName){

        return cityRepository.getCityByName(cityName);
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