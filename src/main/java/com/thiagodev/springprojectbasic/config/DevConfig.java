package com.thiagodev.springprojectbasic.config;

import com.thiagodev.springprojectbasic.service.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("heroku")
public class DevConfig {

    @Autowired
    private DBService dbService;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String strategy;


    @Bean
    public boolean instantiateDataBase() throws ParseException {

        if(!strategy.equals("create")){
            return false;
        }

        dbService.instantiateTestDataBase();

        return true;
    }


}
