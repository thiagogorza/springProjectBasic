package com.thiagodev.springprojectbasic.config;

import com.thiagodev.springprojectbasic.services.DBService;
import com.thiagodev.springprojectbasic.services.email.EmailService;
import com.thiagodev.springprojectbasic.services.email.SmtpEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("dev")
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

    @Bean
    public EmailService emailService(){
        return new SmtpEmailService();
    }

}
