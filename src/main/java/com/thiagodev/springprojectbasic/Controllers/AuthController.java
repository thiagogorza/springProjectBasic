package com.thiagodev.springprojectbasic.Controllers;

import com.thiagodev.springprojectbasic.Models.Dto.EmailDTO;
import com.thiagodev.springprojectbasic.security.JWTUtil;
import com.thiagodev.springprojectbasic.security.UserSS;
import com.thiagodev.springprojectbasic.services.AuthService;
import com.thiagodev.springprojectbasic.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {
    final
   private JWTUtil jwtUtil;

    final
   private AuthService service;

    public AuthController(JWTUtil jwtUtil, AuthService service) {
        this.jwtUtil = jwtUtil;
        this.service = service;
    }

    @PostMapping(value="/refresh_token")
    public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
        UserSS user = UserService.authenticated();
        String token = jwtUtil.generateToken(user.getUsername());
        response.addHeader("Authorization", "Bearer " + token);
        response.addHeader("access-control-expose-headers", "Authorization");
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value="/forgot")
    public ResponseEntity<Void> forgot(@Valid @RequestBody EmailDTO emailDTO) {
       service.sendNewPassword(emailDTO.getEmail());
        return ResponseEntity.noContent().build();
    }

}
