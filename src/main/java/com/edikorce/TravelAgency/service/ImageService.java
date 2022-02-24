package com.edikorce.TravelAgency.service;

import com.edikorce.TravelAgency.model.City;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ImageService {

    public static final String DB_PHOTOS = "src/main/resources/static/dbphotos/";


    public City getPathFromImage(MultipartFile imageFile, City city){

        String pa = "/home/springframework/Desktop/photos/city.jpg";

        try {
            byte[] bytes = imageFile.getBytes();
            Path path = Paths.get(DB_PHOTOS + imageFile.getOriginalFilename());

            Files.write(path, bytes);

            System.out.println(path.toString());
            return city;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return city;
    }

    public void saveImageToDb2(){


    }
}
