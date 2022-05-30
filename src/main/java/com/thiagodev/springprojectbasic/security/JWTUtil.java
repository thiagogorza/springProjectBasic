package com.thiagodev.springprojectbasic.security;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Date;


@Component
public class JWTUtil {

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private String expiration;


    public String generateToken(String username) {

        return Jwts.builder()
                .setSubject(username)
                .setExpiration(Date.from(ZonedDateTime.now().plusMinutes(5).toInstant()))
                .signWith(SignatureAlgorithm.HS512, secret.getBytes())
                .compact();
    }

}
