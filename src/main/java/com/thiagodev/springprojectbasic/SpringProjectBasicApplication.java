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
    final
    private CategoriaRepository categoriaRepository;


    final
    private ProdutoRepository produtoRepository;

    final
    private CidadeRepository cidadeRepository;

    final
    private EstadoRepository estadoRepository;

    final
    private ClienteRepository clienteRepository;

    final
    private EnderecoRepository enderecoRepository;

    final
    private PagamentoRepository pagamentoRepository;

    final
    private PedidoRepository pedidoRepository;

    final
    private ItemPedidoRepository itemPedidoRepository;




    public SpringProjectBasicApplication(CategoriaRepository categoriaRepository, ProdutoRepository produtoRepository, CidadeRepository cidadeRepository, EstadoRepository estadoRepository, ClienteRepository clienteRepository, EnderecoRepository enderecoRepository, PagamentoRepository pagamentoRepository, PedidoRepository pedidoRepository, ItemPedidoRepository itemPedidoRepository) {
        this.categoriaRepository = categoriaRepository;
        this.produtoRepository = produtoRepository;
        this.cidadeRepository = cidadeRepository;
        this.estadoRepository = estadoRepository;
        this.clienteRepository = clienteRepository;
        this.enderecoRepository = enderecoRepository;
        this.pagamentoRepository = pagamentoRepository;
        this.pedidoRepository = pedidoRepository;
        this.itemPedidoRepository = itemPedidoRepository;
    }


    public static void main(String[] args) {
        SpringApplication.run(SpringProjectBasicApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Categoria cat1 = new Categoria(null,"Informática");
        Categoria cat2 = new Categoria(null,"Escritório");
        Categoria cat3 = new Categoria(null,"Cama,mesa e banho");
        Categoria cat4 = new Categoria(null,"Eletrônicos");
        Categoria cat5 = new Categoria(null,"Jardinagem");
        Categoria cat6 = new Categoria(null,"Decoração");
        Categoria cat7 = new Categoria(null,"Perfumaria");
        Produto p1 = new Produto(null,"Computador",2000.00);
        Produto p2 = new Produto(null,"Impressora",800.00);
        Produto p3 = new Produto(null,"Mouse",80.00);

        p1.getCategoriaList().addAll(Arrays.asList(cat1));
        p2.getCategoriaList().addAll(Arrays.asList(cat1,cat2));
        p3.getCategoriaList().addAll(Arrays.asList(cat1));

        cat1.getProdutoList().addAll(Arrays.asList(p1,p2,p3));
        cat2.getProdutoList().addAll(Arrays.asList(p2));


        produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
        categoriaRepository.saveAll(Arrays.asList(cat1,cat2,cat3,cat4,cat5,cat6,cat7));

        Estado est1 = new Estado(null,"Minas Gerais");
        Estado est2 = new Estado(null,"São Paulo");
        Cidade c1 = new Cidade(null,"Uberlandia",est1);
        Cidade c2 = new Cidade(null,"Sao Paulo",est2);
        Cidade c3 = new Cidade(null,"Campinas",est2);

        est1.getCidadeList().addAll(Arrays.asList(c1));
        est2.getCidadeList().addAll(Arrays.asList(c2,c3));

        estadoRepository.saveAll(Arrays.asList(est1,est2));
        cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));

        Cliente cli1 = new Cliente(null,"Maria Silva","maria@gmail.com","12214316557",
                TipoCliente.PESSOAFISICA);
        cli1.getTelefones().addAll(Arrays.asList("2733225049","2733244504"));

        Endereco e1 = new Endereco(null,"Rua Tapajós","3","casa",
                "vila belmiro","22334-120", cli1,c1);
        Endereco e2 = new Endereco(null,"Rua Jacupuru","5","loja",
                "Lambari","25534-333", cli1,c2);


        clienteRepository.saveAll(Arrays.asList(cli1));

        enderecoRepository.saveAll(Arrays.asList(e1,e2));

//        cli1.getEnderecos().addAll(Arrays.asList(e1,e2));

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm"); // classe auxiliar para gerar uma data formatada

        Pedido ped1 = new Pedido(null,sdf.parse("30/09/2017 10:32"),cli1,e1);
        Pedido ped2 = new Pedido(null,sdf.parse("10/10/2017 19:35"),cli1,e2);

        Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO,ped1,6);
        ped1.setPagamento(pagto1);
        Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE,ped2,sdf.parse("20/10/2017 00:00"),null);
        ped2.setPagamento(pagto2);

        pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
        pagamentoRepository.saveAll((Arrays.asList(pagto1,pagto2)));


        ItemPedido ip1 = new ItemPedido(ped1,p1,0.00,1,2000.00);
        ItemPedido ip2 = new ItemPedido(ped1,p3,0.00,2,80.00);
        ItemPedido ip3 = new ItemPedido(ped2,p2,100.00,1,800.00);

        ped1.getItens().addAll(Arrays.asList(ip1,ip2));
        ped2.getItens().addAll(Arrays.asList(ip3));

        p1.getItens().addAll(Arrays.asList(ip1));
        p2.getItens().addAll(Arrays.asList(ip3));
        p3.getItens().addAll(Arrays.asList(ip2));


        itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2,ip3));




    }
}
