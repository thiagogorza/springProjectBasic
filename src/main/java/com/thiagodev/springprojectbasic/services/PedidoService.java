package com.thiagodev.springprojectbasic.services;

import com.thiagodev.springprojectbasic.Models.Categoria;
import com.thiagodev.springprojectbasic.Models.Cliente;
import com.thiagodev.springprojectbasic.Models.Pagamento.PagamentoComBoleto;
import com.thiagodev.springprojectbasic.Models.Pedido.ItemPedido;
import com.thiagodev.springprojectbasic.Models.Pedido.Pedido;
import com.thiagodev.springprojectbasic.Models.enums.EstadoPagamento;
import com.thiagodev.springprojectbasic.repository.ItemPedidoRepository;
import com.thiagodev.springprojectbasic.repository.PagamentoRepository;
import com.thiagodev.springprojectbasic.repository.PedidoRepository;
import com.thiagodev.springprojectbasic.security.UserSS;
import com.thiagodev.springprojectbasic.services.email.EmailService;
import com.thiagodev.springprojectbasic.services.exceptions.AuthorizationException;
import com.thiagodev.springprojectbasic.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    ItemPedidoRepository itemPedidoRepository;

    @Autowired
    BoletoService boletoService;

    @Autowired
    PagamentoRepository pagamentoRepository;

    @Autowired
    ProdutoService produtoService;

    @Autowired
    ClienteService clienteService;


    @Autowired
    EmailService emailService;


    public Pedido findByid(Integer id) {


        Optional<Pedido> obj = pedidoRepository.findById(id);

        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id " +
                id + ",tipo:" + Pedido.class.getName()));

    }

    @Transactional
    public Pedido insert(Pedido pedido) {
        pedido.setId(null);
        pedido.setInstante(new Date());
        pedido.setCliente(clienteService.findById(pedido.getCliente().getId()));
        pedido.getPagamento().setEstado(EstadoPagamento.PENDENTE);
        pedido.getPagamento().setPedido(pedido);
        if (pedido.getPagamento() instanceof PagamentoComBoleto) { // em uma aplicacao real isso será feito por um webservice
            PagamentoComBoleto pagamentoComBoleto = (PagamentoComBoleto) pedido.getPagamento();
            boletoService.preencherPagamentoComBoleto(pagamentoComBoleto, pedido.getInstante());
        }
        pedido = pedidoRepository.save(pedido);
        pagamentoRepository.save(pedido.getPagamento());
        for (ItemPedido ip : pedido.getItens()) {
            ip.setDesconto(0.0);
            ip.setProduto(produtoService.findByid(ip.getProduto().getId()));
            ip.setPreco(ip.getProduto().getPreco()); // seta o preço do produto com o mesmo preço que vem do banco de dados através do id.
            ip.setPedido(pedido);
        }
        itemPedidoRepository.saveAll(pedido.getItens());
        emailService.sendOrderConfirmationHtmlEmail(pedido); // envio de email (pode ser por requisicao ou pelo html, os metodos estao criados)
        return pedido;
    }

    public Page<Pedido> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        UserSS userSS = UserService.authenticated();
        if (userSS == null) {

            throw new AuthorizationException("Acesso negado");
        }
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        Cliente cliente = clienteService.findById(userSS.getId());
        return pedidoRepository.findByCliente(cliente,pageRequest);
    }

}
