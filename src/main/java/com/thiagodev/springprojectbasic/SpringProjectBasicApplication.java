package com.thiagodev.springprojectbasic;

import com.thiagodev.springprojectbasic.Models.*;
import com.thiagodev.springprojectbasic.Models.Pagamento.Pagamento;
import com.thiagodev.springprojectbasic.Models.Pagamento.PagamentoComBoleto;
import com.thiagodev.springprojectbasic.Models.Pagamento.PagamentoComCartao;
import com.thiagodev.springprojectbasic.Models.Pedido.ItemPedido;
import com.thiagodev.springprojectbasic.Models.Pedido.Pedido;
import com.thiagodev.springprojectbasic.Models.enums.EstadoPagamento;
import com.thiagodev.springprojectbasic.Models.enums.TipoCliente;
import com.thiagodev.springprojectbasic.repository.*;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.Arrays;

@SpringBootApplication
public class SpringProjectBasicApplication implements ApplicationRunner {


    public static void main(String[] args) {
        SpringApplication.run(SpringProjectBasicApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

    }
}
