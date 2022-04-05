package com.thiagodev.springprojectbasic.Controllers;

import com.thiagodev.springprojectbasic.Models.Categoria;
import com.thiagodev.springprojectbasic.repository.CategoriaRepository;
import com.thiagodev.springprojectbasic.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    @Autowired
    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping(value = {"{id}"})
    public ResponseEntity<?> find(@PathVariable Long id) {
        Categoria obj = categoriaService.findByid(id);
        return ResponseEntity.ok(obj);

    }

    @PostMapping
    public @ResponseBody  Categoria insert(Categoria categoria) {
       categoriaService.insert(categoria);
        return categoria;
    }


}
