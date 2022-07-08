package com.thiagodev.springprojectbasic.services;

import com.thiagodev.springprojectbasic.Models.Categoria;
import com.thiagodev.springprojectbasic.Models.Dto.CategoriaDTO;
import com.thiagodev.springprojectbasic.repository.CategoriaRepository;
import com.thiagodev.springprojectbasic.services.exceptions.DataIntegrityException;
import com.thiagodev.springprojectbasic.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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


    public Categoria findByid(Integer id) {

        Optional<Categoria> obj = categoriaRepository.findById(id);

        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id " +
                id + ",tipo:" + Categoria.class.getName()));

    }

    public Categoria insert(Categoria categoria) {
        categoria.setId(null);
        return categoriaRepository.save(categoria);

    }

    public Categoria update(Categoria categoria) {
        Categoria newCategoria = findByid(categoria.getId()); //feito para atualizar apenas os argumentos enviados (exemplo: atulizar só nome e email)
        updateData(newCategoria,categoria);
        return categoriaRepository.save(newCategoria);
    }

    private void updateData(Categoria newCategoria, Categoria categoria) {

        newCategoria.setNome(categoria.getNome());

    }


    public void delete(Integer id) {
        try {
            categoriaRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é Possivel excluir uma categoria que possui produtos");
        }
    }

    public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return categoriaRepository.findAll(pageRequest);
    }

    public Categoria fromDto(CategoriaDTO objDto){

        return new Categoria (objDto.getId(), objDto.getNome());

    }

}
