package br.com.borges;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import br.borges.dao.IProdutoDAO;
import br.borges.dao.ProdutoDAO;
import br.borges.domain.Produto;

public class ProdutoTest {
	
	IProdutoDAO dao = new ProdutoDAO();
	Produto produto = new Produto();
	
	@Test
	public void produtoTest() throws Exception{
		produto.setCodigo("01");
		produto.setNome("Notebook");
		produto.setPreco(1500.00);
		produto.setEstoque(10);
		
		Integer qtd = dao.cadastrar(produto);
		assertTrue(qtd == 1);
		
		Produto produtoBD = dao.consultar(produto.getCodigo());
		
		assertNotNull(produtoBD);
		assertNotNull(produtoBD.getId());
		assertEquals(produto.getCodigo(), produtoBD.getCodigo());
		assertEquals(produto.getNome(), produtoBD.getNome());
		assertEquals(produto.getPreco(), produtoBD.getPreco());
		assertEquals(produto.getEstoque(), produtoBD.getEstoque());
		
		produto.setNome("Desktop Dell");
		produto.setPreco(2600.50);
		produto.setEstoque(7);
		
		Integer countUpdate = dao.alterar(produtoBD);
		assertTrue(countUpdate > 0);
		
		List<Produto>list = dao.buscarTodos();
		assertNotNull(list);
		assertEquals(1, list.size());
		
		Integer deleteAll = dao.excluir(produtoBD);
		assertNotNull(deleteAll);
		
	}

}
