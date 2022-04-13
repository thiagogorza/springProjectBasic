package com.thiagodev.springprojectbasic.Controllers;

import com.thiagodev.springprojectbasic.Models.Produto;
import com.thiagodev.springprojectbasic.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    @Autowired
    public ProdutoController(ProdutoService produtoService){
        this.produtoService = produtoService;
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<?> findById(Long id){

        Produto obj = produtoService.findByid(id);

        return ResponseEntity.ok(obj);

    }

    @GetMapping
    public ResponseEntity<?> findAll(){
        List<Produto> produtoList = produtoService.findAll();
        return ResponseEntity.ok(produtoList) ;
    }
}
