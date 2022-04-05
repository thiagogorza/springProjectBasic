package com.thiagodev.springprojectbasic.service;

import com.thiagodev.springprojectbasic.Models.Categoria;
import com.thiagodev.springprojectbasic.repository.CategoriaRepository;
import com.thiagodev.springprojectbasic.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    CategoriaRepository categoriaRepository;


    public Categoria findByid(Long id) {

        Optional<Categoria> obj = categoriaRepository.findById(id);

        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id " +
                id + ",tipo:"  + Categoria.class.getName() ));

    }

    public Categoria insert(Categoria categoria) {
        categoria.setId(null);
        return categoriaRepository.save(categoria);

    }

    public Categoria update(Categoria categoria) {
        findByid(categoria.getId()); // caso a categoria não exista, ele chama esse metódo que lança uma exceção
        return categoriaRepository.save(categoria);
    }
}
