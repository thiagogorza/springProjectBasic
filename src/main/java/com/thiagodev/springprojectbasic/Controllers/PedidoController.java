package com.thiagodev.springprojectbasic.Controllers;
import com.thiagodev.springprojectbasic.Models.Categoria;
import com.thiagodev.springprojectbasic.Models.Pedido.Pedido;
import com.thiagodev.springprojectbasic.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
