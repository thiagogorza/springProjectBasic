package com.thiagodev.springprojectbasic.Controllers;

import com.thiagodev.springprojectbasic.Models.Produto;
import com.thiagodev.springprojectbasic.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/produto")
public class ProdutoController {

    @Autowired
    ProdutoService produtoService;

    @GetMapping(value = "{id}")
    public ResponseEntity<?> findById(Long id){

        Produto obj = produtoService.findById(id);

        return ResponseEntity.ok(obj);

    }
}
