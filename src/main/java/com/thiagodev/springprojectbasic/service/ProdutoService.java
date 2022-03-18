package com.thiagodev.springprojectbasic.service;

import com.thiagodev.springprojectbasic.Models.Produto;
import com.thiagodev.springprojectbasic.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository produtoRepository;


    public Produto findById(Long id){

        Optional<Produto> obj = produtoRepository.findById(id);

        return obj.orElse(null);

    }
}
