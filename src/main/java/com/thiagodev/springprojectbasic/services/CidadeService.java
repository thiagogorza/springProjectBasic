package com.thiagodev.springprojectbasic.services;

import com.thiagodev.springprojectbasic.Models.Cidade;
import com.thiagodev.springprojectbasic.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CidadeService {


    @Autowired
    CidadeRepository cidadeRepository;

    public List<Cidade> findByEstado(Integer estadoId){


        return cidadeRepository.findByEstadoIdOrderByNomeAsc(estadoId);

    }

}
