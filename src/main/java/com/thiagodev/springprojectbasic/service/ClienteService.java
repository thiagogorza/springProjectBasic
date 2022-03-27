package com.thiagodev.springprojectbasic.service;

import com.thiagodev.springprojectbasic.Models.Cliente;
import com.thiagodev.springprojectbasic.repository.ClienteRepository;
import com.thiagodev.springprojectbasic.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository ClienteRepository;


    public Cliente findByid(Long id) {


        Optional<Cliente> obj = ClienteRepository.findById(id);

        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id " +
                id + ",tipo:"  + Cliente.class.getName() ));

    }

}
