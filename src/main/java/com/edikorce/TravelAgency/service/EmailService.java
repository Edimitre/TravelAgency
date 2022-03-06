package com.edikorce.TravelAgency.service;

import com.edikorce.TravelAgency.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {


    @Autowired
    private JavaMailSender javaMailSender;


    public void sendEmail(User user, String message){


        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom("mitreedi@gmail.com");

        mail.setTo(user.getEmail());
        mail.setSubject("TravelAgency");
        mail.setText(message);
        javaMailSender.send(mail);

    }
}
