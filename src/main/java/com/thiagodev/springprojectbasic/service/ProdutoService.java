package com.thiagodev.springprojectbasic.service;

import com.thiagodev.springprojectbasic.Models.Categoria;
import com.thiagodev.springprojectbasic.Models.Produto;
import com.thiagodev.springprojectbasic.repository.ProdutoRepository;
import com.thiagodev.springprojectbasic.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository produtoRepository;


    public Produto findByid(Long id) {


        Optional<Produto> obj = produtoRepository.findById(id);

        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id " +
                id + ",tipo:"  + Produto.class.getName() ));

    }

    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }

    public Page<Produto> search(String nome,List<Long> ids,Pageable pageable){
        List<Categoria> categorias = new ArrayList<>();
        return produtoRepository.search(pageable,categorias,nome);
    }


}
