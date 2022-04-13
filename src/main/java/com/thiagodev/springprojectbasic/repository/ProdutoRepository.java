package com.thiagodev.springprojectbasic.repository;

import com.thiagodev.springprojectbasic.Models.Categoria;
import com.thiagodev.springprojectbasic.Models.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    @Query("SELECT DISTINCT obj FROM Produto obj INNER JOIN obj.categoriaList cat WHERE obj.nome LIKE %:nome% AND cat IN :categoriaList")
    Page<Produto> search(Pageable pageable,@Param("categoriaList")  List<Categoria> categorias, @Param("nome") String nome);
}