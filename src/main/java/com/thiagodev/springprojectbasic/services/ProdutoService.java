package com.thiagodev.springprojectbasic.services;

import com.thiagodev.springprojectbasic.Models.Categoria;
import com.thiagodev.springprojectbasic.Models.Produto;
import com.thiagodev.springprojectbasic.repository.CategoriaRepository;
import com.thiagodev.springprojectbasic.repository.ProdutoRepository;
import com.thiagodev.springprojectbasic.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository produtoRepository;
    @Autowired
    CategoriaRepository categoriaRepository;


    public Produto findByid(Integer id) {


        Optional<Produto> obj = produtoRepository.findById(id);

        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id " +
                id + ",tipo:"  + Produto.class.getName() ));

    }

    public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        List<Categoria> categorias = categoriaRepository.findAllById(ids);
        return produtoRepository.findDistinctByNomeContainingIgnoreCaseAndCategoriaListIn(nome, categorias, pageRequest);
    }

    public List<Produto> findAll() {

        return produtoRepository.findAll();
    }

    public Produto save(Produto produto){
        return produtoRepository.save(produto);
    }

}
