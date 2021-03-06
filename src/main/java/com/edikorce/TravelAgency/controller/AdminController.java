package com.edikorce.TravelAgency.controller;

import com.edikorce.TravelAgency.exeption.ContentNotFoundExeption;
import com.edikorce.TravelAgency.model.*;
import com.edikorce.TravelAgency.repository.RoleRepository;
import com.edikorce.TravelAgency.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
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
    @Autowired
    private OrderService orderService;
    @Autowired
    private EmailService emailService;





    // USERS

    @GetMapping("/home")
    public String home(){

        return "admin/admin_page";
    }

    @GetMapping("/users/all")
    public String watchUsers(Model model){

        List<User> userList = userService.listAll();
        model.addAttribute("userList", userList);
        return "listview/all_users";
    }

    @RequestMapping("/user/edit/{id}")
    public String showEditUserForm(@PathVariable(value = "id")Long id, Model model) throws ContentNotFoundExeption {

        User user = userService.get(id);

        List<Role> rolet= roleRepository.findAll();

        model.addAttribute("rolet", rolet);
        model.addAttribute("user", user);

        return "forms/edit_user_form";

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
        return "forms/add_city_form";
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
        return "listview/all_cities";
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

        return "forms/add_city_form";

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
        return "forms/add_country_form";
    }

    @GetMapping("/country/all")
    public String showAllCountries(Model model){
        model.addAttribute("country", new Country());
        List<Country> countryList = countryService.getAllCountries();
        model.addAttribute("countryList", countryList);
        return "listview/all_countries";
    }


    @RequestMapping("/country/edit/{id}")
    public String updateCountry(@PathVariable(value = "id") Long id, Model model) throws ContentNotFoundExeption {
        Country country = countryService.getCountryById(id);

        List<Continent> allContinents = continentService.getAllContinents();
        model.addAttribute("allContinents", allContinents);
        model.addAttribute("country", country);
        return "forms/add_country_form";
    }

    @RequestMapping("/country/delete/{id}")
    public String deleteCountryById(@PathVariable(value = "id") Long id){
        countryService.deleteCountryById(id);
        return "redirect:/admin/country/all";
    }








    // PACKET

    @RequestMapping("/packet/register")
    public String showPacketForm(Model model){

        model.addAttribute("cityList", cityService.getAllCities());
        model.addAttribute("packet", new Packet());
        model.addAttribute("message", "Shto Packet");

        return"forms/add_packet_form";
    }

    @PostMapping("/packet/save")
    public String savePacket(Packet packet){


        packetService.savePacket(packet);
        return "redirect:/admin/home";
    }

    @RequestMapping("/packet/edit/{id}")
    public String updatePacket(@PathVariable(value = "id") Long id, Model model) throws ContentNotFoundExeption {
        Packet packet = packetService.getPacketById(id);

        List<City> cityList = cityService.getAllCities();

        model.addAttribute("packet", packet);

        model.addAttribute("cityList", cityList);
        model.addAttribute("message", "Edit Packet");
        return "forms/add_packet_form";
    }

    @RequestMapping("/packet/details/{id}")
    @Transactional
    public String getPacketDetails(@PathVariable(value = "id") Long id, Model model) throws ContentNotFoundExeption {

        Packet packet = packetService.getPacketById(id);

        List<User> userList = packet.getUserList();
        model.addAttribute("packet", packet);
        model.addAttribute("user", new User());
        model.addAttribute("userList", userList);
        return "admin/packet_details";
    }




    @RequestMapping("/orders/all")
    public String allOrders(Model model){

        model.addAttribute("orders", orderService.getAllOrders());
        return "listview/all_orders";
    }


    @RequestMapping("/order/approve/{id}")
    public String approveOrder(@PathVariable(value = "id") Long id) throws ContentNotFoundExeption {

        Order order = orderService.getOrderById(id);
        orderService.approveOrder(order);
        emailService.sendEmail(order.getUser(), "Paketa u aprovua");
        orderService.deleteOrder(order);
        return "redirect:/admin/orders/all";
    }

}
