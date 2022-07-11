package com.thiagodev.springprojectbasic.Controllers;
import com.thiagodev.springprojectbasic.Models.Categoria;
import com.thiagodev.springprojectbasic.Models.Dto.CategoriaDTO;
import com.thiagodev.springprojectbasic.Models.Pedido.Pedido;
import com.thiagodev.springprojectbasic.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@Controller
@RequestMapping(value = "/pedidos")
public class PedidoController {


   private final PedidoService pedidoService;

   @Autowired
   public PedidoController(PedidoService pedidoService){
       this.pedidoService = pedidoService;
   }

    @GetMapping(value = {"{id}"})
    public ResponseEntity<?> find(@PathVariable Integer id) {
        Pedido obj = pedidoService.findByid(id);
        return ResponseEntity.ok(obj);

    }

    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody Pedido pedido) {
        pedido = pedidoService.insert(pedido);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(pedido.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    @GetMapping
    public ResponseEntity<Page<Pedido>> findPage(
            @RequestParam(value="page", defaultValue="0") Integer page,
            @RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
            @RequestParam(value="orderBy", defaultValue="instante") String orderBy,
            @RequestParam(value="direction", defaultValue="DESC") String direction)
    {
        Page<Pedido> listPedido = pedidoService.findPage(page, linesPerPage, orderBy, direction);
        return ResponseEntity.ok().body(listPedido);
    }
}
