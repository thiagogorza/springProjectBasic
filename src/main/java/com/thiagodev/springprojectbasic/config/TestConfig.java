package com.thiagodev.springprojectbasic.config;

import com.thiagodev.springprojectbasic.service.DBService;
import com.thiagodev.springprojectbasic.service.email.EmailService;
import com.thiagodev.springprojectbasic.service.email.MockEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("test")
public class TestConfig {

    @Autowired
    private DBService dbService;


    @Bean
    public boolean instantiateDataBase() throws ParseException {

        dbService.instantiateTestDataBase();

        return true;
    }

    @Bean
    public EmailService emailService(){
        return new MockEmailService();
    }

}
