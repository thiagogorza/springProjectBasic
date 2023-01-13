package com.thiagodev.springprojectbasic.Controllers;

import com.thiagodev.springprojectbasic.Models.Cidade;
import com.thiagodev.springprojectbasic.Models.Cliente;
import com.thiagodev.springprojectbasic.Models.Dto.CidadeDTO;
import com.thiagodev.springprojectbasic.Models.Dto.EstadoDTO;
import com.thiagodev.springprojectbasic.Models.Estado;
import com.thiagodev.springprojectbasic.services.CidadeService;
import com.thiagodev.springprojectbasic.services.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/estados")
public class EstadoController {

    @Autowired
    EstadoService estadoService;

    @Autowired
    CidadeService cidadeService;

    @GetMapping
    public ResponseEntity<List<EstadoDTO>> findAll() {
        List<Estado> list = estadoService.findAll();
        List<EstadoDTO> listDto = list.stream().map(obj -> new EstadoDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);

    }
    @GetMapping(value = {"{estadoId}/cidades"})
    public ResponseEntity<List<CidadeDTO>> findByCidade(@PathVariable Integer estadoId) {
        List<Cidade> list = cidadeService.findByEstado(estadoId);
        List<CidadeDTO> listDto = list.stream().map(obj -> new CidadeDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);

    }




}
