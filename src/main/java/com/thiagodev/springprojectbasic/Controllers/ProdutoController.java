package com.thiagodev.springprojectbasic.Controllers;

import com.thiagodev.springprojectbasic.Controllers.utils.URL;
import com.thiagodev.springprojectbasic.Models.Categoria;
import com.thiagodev.springprojectbasic.Models.Dto.CategoriaDTO;
import com.thiagodev.springprojectbasic.Models.Dto.ProdutoDTO;
import com.thiagodev.springprojectbasic.Models.Produto;
import com.thiagodev.springprojectbasic.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
@RequestMapping(value = "/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    @Autowired
    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Produto> findById(@PathVariable Integer id) {

        Produto obj = produtoService.findByid(id);

        return ResponseEntity.ok().body(obj);

    }

//    @GetMapping
//    public ResponseEntity<?> findAll() {
//
//        List<Produto> obj = produtoService.findAll();
//
//        return ResponseEntity.ok(obj);
//
//    }

    @GetMapping
    public ResponseEntity<Page<ProdutoDTO>> findPage(
            @RequestParam(value="nome", defaultValue="") String nome,
            @RequestParam(value="categoriaList", defaultValue="") String categoriaList,
            @RequestParam(value="page", defaultValue="0") Integer page,
            @RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
            @RequestParam(value="orderBy", defaultValue="nome") String orderBy,
            @RequestParam(value="direction", defaultValue="ASC") String direction) {
        String nomeDecoded = URL.decodeParam(nome);
        List<Integer> ids = URL.decodeIntList(categoriaList);
        Page<Produto> list = produtoService.search(nomeDecoded, ids, page, linesPerPage, orderBy, direction);
        Page<ProdutoDTO> listDto = list.map(ProdutoDTO::new);
        return ResponseEntity.ok().body(listDto);
    }
    }
