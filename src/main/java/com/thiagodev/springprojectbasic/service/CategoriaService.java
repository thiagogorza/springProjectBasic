package com.thiagodev.springprojectbasic.service;

import com.thiagodev.springprojectbasic.Models.Categoria;
import com.thiagodev.springprojectbasic.Models.Dto.CategoriaDto;
import com.thiagodev.springprojectbasic.repository.CategoriaRepository;
import com.thiagodev.springprojectbasic.service.exception.DataIntegrityException;
import com.thiagodev.springprojectbasic.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    CategoriaRepository categoriaRepository;

    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }


    public Categoria findByid(Long id) {

        Optional<Categoria> obj = categoriaRepository.findById(id);

        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id " +
                id + ",tipo:" + Categoria.class.getName()));

    }

    public Categoria insert(Categoria categoria) {
        categoria.setId(null);
        return categoriaRepository.save(categoria);

    }

    public Categoria update(Categoria categoria) {
        findByid(categoria.getId()); // caso a categoria não exista, ele chama esse metódo que lança uma exceção
        return categoriaRepository.save(categoria);
    }


    public void delete(Long id) {
        try {
            categoriaRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é Possivel excluir uma categoria que possui produtos");
        }
    }

    public Page<Categoria> findPage(Pageable pageable) {
//        Integer page, Integer linesPerPage, String direction, String ordeBy
//       PageRequest pageRequest =  PageRequest.of(page,linesPerPage, Sort.Direction.valueOf(direction),ordeBy);

       return categoriaRepository.findAll(pageable);
    }

    public Categoria fromDto(CategoriaDto objDto){

        return new Categoria (objDto.getId(), objDto.getNome());

    }

}