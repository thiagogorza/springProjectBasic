package com.thiagodev.springprojectbasic.services;

import com.thiagodev.springprojectbasic.security.UserSS;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public static UserSS authenticated() {
        try {
            return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); // retorna o usuario logado no sistema
        }
        catch (Exception e){
            return null;
        }
    }

}
