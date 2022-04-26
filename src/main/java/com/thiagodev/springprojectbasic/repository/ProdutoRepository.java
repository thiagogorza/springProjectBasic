package com.thiagodev.springprojectbasic.repository;

import com.thiagodev.springprojectbasic.Models.Categoria;
import com.thiagodev.springprojectbasic.Models.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
//    @Transactional(readOnly=true)
//    @Query("SELECT DISTINCT obj FROM Produto obj " + "INNER JOIN obj.categoriaList cat WHERE LOWER(obj.nome)" + "LIKE LOWER(CONCAT('%',:nome,'%')) AND cat IN :categoriaList")
//    Page<Produto> findDistinctByNomeContainingIgnoreCaseAndCategoriasIn(@Param("nome") String nome, @Param("categoriaList") List<Categoria> categoriaList, Pageable pageRequest);

    @Transactional(readOnly=true) // como é só uma consulta, nao precisa acrescentar na transacao
    Page<Produto> findDistinctByNomeContainingIgnoreCaseAndCategoriaListIn(String nome, List<Categoria> categoriaList, Pageable pageRequest);
}