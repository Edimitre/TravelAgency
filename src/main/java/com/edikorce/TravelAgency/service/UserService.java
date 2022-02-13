package com.edikorce.TravelAgency.service;


import com.edikorce.TravelAgency.exeption.ContentNotFoundExeption;
import com.edikorce.TravelAgency.model.User;
import com.edikorce.TravelAgency.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user){
        return userRepository.save(user);
    }

    public List<User> getAllUsers(){

        List<User> allUserList= userRepository.findAll();
        return allUserList;
    }

    public List<User> saveAllUsers(List<User> userList){

        return userRepository.saveAll(userList);
    }

    public User getUserById(Long id) throws ContentNotFoundExeption {
        Optional<User> user = userRepository.findById(id);

            if (user.isPresent()){
                return user.get();
            }else{
                throw new ContentNotFoundExeption("user nuk u gjend");
            }
    }

    public void deleteUserById(Long id){

       userRepository.deleteById(id);
    }

}
