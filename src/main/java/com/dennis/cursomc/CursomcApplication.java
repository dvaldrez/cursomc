package com.dennis.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.dennis.cursomc.domain.Categoria;
import com.dennis.cursomc.domain.Cidade;
import com.dennis.cursomc.domain.Cliente;
import com.dennis.cursomc.domain.Endereco;
import com.dennis.cursomc.domain.Estado;
import com.dennis.cursomc.domain.Pagamento;
import com.dennis.cursomc.domain.PagamentoComBoleto;
import com.dennis.cursomc.domain.PagamentoComCartao;
import com.dennis.cursomc.domain.Pedido;
import com.dennis.cursomc.domain.Produto;
import com.dennis.cursomc.domain.enums.EstadoPagamento;
import com.dennis.cursomc.domain.enums.TipoCliente;
import com.dennis.cursomc.repositories.CategoriaRepository;
import com.dennis.cursomc.repositories.CidadeRepository;
import com.dennis.cursomc.repositories.ClienteRepository;
import com.dennis.cursomc.repositories.EnderecoRepository;
import com.dennis.cursomc.repositories.EstadoRepository;
import com.dennis.cursomc.repositories.PagamentoRepository;
import com.dennis.cursomc.repositories.PedidoRepository;
import com.dennis.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private  CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	
		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritorio");
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		
		Produto prod1 = new Produto(null, "Computador", 2000.00);
		Produto prod2 = new Produto(null, "Impressora", 800.00);
		Produto prod3 = new Produto(null, "Mouse", 80.00);
			
		cat1.getProdutos().addAll(Arrays.asList(prod1, prod2,prod3));
		cat2.getProdutos().addAll(Arrays.asList( prod2));
		
		prod1.getCategoria().addAll(Arrays.asList(cat1));
		prod2.getCategoria().addAll(Arrays.asList(cat1,cat2));
		prod3.getCategoria().addAll(Arrays.asList(cat2));
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		produtoRepository.saveAll(Arrays.asList(prod1,prod2,prod3));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "Maria@gmail.com", "24656842800", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("123123","456456"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "ap 303", "jardim", "30002125", cli1, c1);
		Endereco e2 = new Endereco(null, "Av Matos", "300", "ap 303", "jardim", "30002125", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);
		Pagamento pgto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO,ped1, 6);
		ped1.setPagamento(pgto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
		pagamentoRepository.saveAll(Arrays.asList(pgto1, pagto2));
		
	}

}
