package com.thiagodev.springprojectbasic.repository;
import com.thiagodev.springprojectbasic.Models.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface EstadoRepository extends JpaRepository<Estado, Integer> {

   @Transactional(readOnly = true)
   List<Estado> findAllByOrderByNome();



}