package com.thiagodev.springprojectbasic.services;

import com.thiagodev.springprojectbasic.Models.Cliente;
import com.thiagodev.springprojectbasic.repository.ClienteRepository;
import com.thiagodev.springprojectbasic.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ClienteRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Cliente cliente = repository.findByEmail(email);
        if(cliente == null){
            throw new UsernameNotFoundException(email);
        }

        return new UserSS(cliente.getId(),cliente.getEmail(), cliente.getSenha(), cliente.getPerfis());
    }
}
