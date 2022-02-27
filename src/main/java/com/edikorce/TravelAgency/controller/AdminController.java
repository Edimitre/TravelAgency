package com.edikorce.TravelAgency.controller;

import com.edikorce.TravelAgency.exeption.ContentNotFoundExeption;
import com.edikorce.TravelAgency.model.*;
import com.edikorce.TravelAgency.repository.RoleRepository;
import com.edikorce.TravelAgency.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private CountryService countryService;
    @Autowired
    private ContinentService continentService;
    @Autowired
    private CityService cityService;
    @Autowired
    private PacketService packetService;




    // USERS

    @GetMapping("/home")
    public String home(){

        return "admin_page";
    }

    @GetMapping("/users/all")
    public String watchUsers(Model model){

        List<User> userList = userService.listAll();


        model.addAttribute("userList", userList);
        return "all_users";
    }

    @RequestMapping("/user/edit/{id}")
    public String showEditUserForm(@PathVariable(value = "id")Long id, Model model) throws ContentNotFoundExeption {

        User user = userService.get(id);

        List<Role> rolet= roleRepository.findAll();

        model.addAttribute("rolet", rolet);
        model.addAttribute("user", user);

        return "edit_user_form";

    }

    @RequestMapping("/user/delete/{id}")
    public String deleteUserById(@PathVariable(value = "id") Long id){
        userService.deleteUserById(id);
        return "redirect:/admin/users/all";
    }

    @RequestMapping("/user/save")
    public String saveUser(User user) {
        userService.save(user);
        return "redirect:/admin/users/all";
    }







    // CITY

    @RequestMapping("/city/register")
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

    @PostMapping("/city/save")
    public String saveCity(MultipartFile imageFile, City city) throws IOException {



        cityService.saveCity(imageFile, city);
        return "redirect:/admin/home";
    }


    @GetMapping("/cities/all")
    public String showAllCities(Model model){
        List<City> cityList = cityService.getAllCities();

        model.addAttribute("cityList", cityList);
        return "all_cities";
    }

    @RequestMapping("/city/edit/{id}")
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

    @RequestMapping("/city/delete/{id}")
    public String deleteCity(@PathVariable(value = "id")Long id){


        cityService.deleteCityById(id);
        return "redirect:/admin/cities/all";
    }






    // COUNTRY

    @RequestMapping("/country/save")
    public String saveCountry(Country country){


        countryService.saveCountry(country);
        return "redirect:/admin/home";
    }

    @GetMapping("/country/register")
    public String showCountryAddForm(Model model){

        List<Continent> allContinents= continentService.getAllContinents();


        model.addAttribute("country", new Country());

        model.addAttribute("allContinents", allContinents);
        return "add_country_form";
    }

    @GetMapping("/country/all")
    public String showAllCountries(Model model){
        model.addAttribute("country", new Country());
        List<Country> countryList = countryService.getAllCountries();
        model.addAttribute("countryList", countryList);
        return "all_countries";
    }


    @RequestMapping("/country/edit/{id}")
    public String updateCountry(@PathVariable(value = "id") Long id, Model model) throws ContentNotFoundExeption {
        Country country = countryService.getCountryById(id);

        List<Continent> allContinents = continentService.getAllContinents();
        model.addAttribute("allContinents", allContinents);
        model.addAttribute("country", country);
        return "add_country_form";
    }

    @RequestMapping("/country/delete/{id}")
    public String deleteCountryById(@PathVariable(value = "id") Long id){
        countryService.deleteCountryById(id);
        return "redirect:/admin/country/all";
    }








    // PACKET

    @GetMapping("/packet/register")
    private String showPacketForm(Model model){

        List<City> allCityList = cityService.getAllCities();
        model.addAttribute("allCityList", allCityList);
        model.addAttribute("packet", new Packet());
        model.addAttribute("message", "Shto Packet");

        return"add_packet_form";
    }

    @PostMapping("/packet/save")
    public String savePacket(Packet packet){


        packetService.savePacket(packet);
        return "redirect:/admin/home";
    }

    @RequestMapping("/packet/edit/{id}")
    public String updatePacket(@PathVariable(value = "id") Long id, Model model) throws ContentNotFoundExeption {
        Packet packet = packetService.getPacketById(id);

        List<City> allCityList = cityService.getAllCities();

        model.addAttribute("packet", packet);

        model.addAttribute("allCityList", allCityList);
        model.addAttribute("message", "Edit Packet");
        return "add_packet_form";
    }

    @RequestMapping("/packet/details/{id}")
    public String getPacketDetails(@PathVariable(value = "id") Long id, Model model) throws ContentNotFoundExeption {

        Packet packet = packetService.getPacketById(id);
        model.addAttribute("packet", packet);
        return "packet_details";
    }

}
