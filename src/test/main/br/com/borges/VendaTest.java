package br.com.borges;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import br.borges.dao.ClienteDAO;
import br.borges.dao.IProdutoDAO;
import br.borges.dao.IVendaDAO;
import br.borges.dao.ProdutoDAO;
import br.borges.dao.VendaDAO;
import br.borges.domain.Cliente;
import br.borges.domain.Produto;
import br.borges.domain.Venda;

public class VendaTest {
	
	IVendaDAO vendaDao = new VendaDAO();
	ClienteDAO clienteDAO = new ClienteDAO();
	IProdutoDAO produtoDao = new ProdutoDAO();
	Venda venda = new Venda();
	
	@Test
	public void vendaTest()throws Exception{
		//Validando o Cadastro da Venda
		Cliente cliente = new Cliente();
		cliente.setCodigo("C01");
		cliente.setNome("Ciclano");
		cliente.setTelefone("(62) 1234-5678");
		
		Integer clienteCadastrado = clienteDAO.cadastrar(cliente);
		assertTrue(clienteCadastrado == 1);
		
		Produto produto = new Produto();
		produto.setCodigo("01");
		produto.setNome("Lenovo");
		produto.setPreco(3500.00);
		produto.setEstoque(10);
		
		Integer produtoCadastrado = produtoDao.cadastrar(produto);
		assertTrue(produtoCadastrado == 1);
		
		venda.setCodigo("01");
		List<Cliente>clientes = clienteDAO.buscarTodos();
		assertFalse(clientes.isEmpty());
		venda.setCliente_id(clientes.get(0).getId());
		
		List<Produto>produtos = produtoDao.buscarTodos();
		assertFalse(produtos.isEmpty());
		venda.setProduto_id(produtos.get(0).getId());
		
		venda.setQuantidade(1);
		venda.setTotal(500);
		
		Integer cadastroVenda = vendaDao.cadastrar(venda);
		assertTrue(cadastroVenda == 1);
        //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX	
		//Validando a consulta da Venda no Banco
		
		Venda vendaBD = vendaDao.consultar(venda.getCodigo());
		
		assertNotNull(vendaBD);
		assertNotNull(vendaBD.getId());
		assertEquals(venda.getCodigo(), vendaBD.getCodigo());
		assertEquals(venda.getCliente_id(), vendaBD.getCliente_id());
		assertEquals(venda.getProduto_id(), vendaBD.getProduto_id());
		assertEquals(venda.getQuantidade(), vendaBD.getQuantidade());
		assertEquals(venda.getTotal(), vendaBD.getTotal());
		
		//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
		//Validando atualização do cadastro de Venda
		venda.setQuantidade(30);
		venda.setTotal(5000);
		
		Integer countUpdate = vendaDao.alterar(vendaBD);
		assertTrue(countUpdate > 0);
		
		//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
		//trazendo todas as Vendas
		List<Venda>list = vendaDao.buscarTodos();
		assertNotNull(list);
		assertEquals(1, list.size());
		
		//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
		Integer apagarTodasVendas = vendaDao.excluir(vendaBD);
		assertNotNull(apagarTodasVendas);
	}

}
