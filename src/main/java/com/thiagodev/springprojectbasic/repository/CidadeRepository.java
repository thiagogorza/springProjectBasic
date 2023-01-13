package com.thiagodev.springprojectbasic.repository;
import com.thiagodev.springprojectbasic.Models.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CidadeRepository extends JpaRepository<Cidade, Integer> {


    @Transactional(readOnly = true)
    List<Cidade> findByEstadoIdOrderByNomeAsc(Integer estadoId);

}