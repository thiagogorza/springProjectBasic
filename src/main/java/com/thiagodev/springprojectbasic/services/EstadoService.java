package com.thiagodev.springprojectbasic.services;

import com.thiagodev.springprojectbasic.Models.Estado;
import com.thiagodev.springprojectbasic.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class EstadoService {

        @Autowired
        EstadoRepository estadoRepository;

        public List<Estado> findAll(){
                return estadoRepository.findAllByOrderByNome();

        }




}
