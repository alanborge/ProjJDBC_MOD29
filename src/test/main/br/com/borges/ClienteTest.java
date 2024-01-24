
package br.com.borges;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import br.borges.dao.ClienteDAO;
import br.borges.dao.IClienteDAO;
import br.borges.domain.Cliente;
import static org.junit.Assert.assertEquals;

public class ClienteTest {

	IClienteDAO dao = new ClienteDAO();
	Cliente cliente = new Cliente();

	@Test
	public void clienteTest() throws Exception {
		//Validando o Cadastro do Cliente
		cliente.setCodigo("01");
		cliente.setNome("Maria Da silva");
		cliente.setEmail("bbb@teles.com");
		cliente.setTelefone("(62) 99330-5853");

		Integer qtd = dao.cadastrar(cliente);
		assertTrue(qtd == 1);
		
		//Validando a consulta do cliente vs o Banco
		Cliente clienteBD = dao.consultar(cliente.getCodigo());

		assertNotNull(clienteBD);
		assertNotNull(clienteBD.getId());
		assertEquals(cliente.getCodigo(), clienteBD.getCodigo());
		assertEquals(cliente.getNome(), clienteBD.getNome());
		assertEquals(cliente.getEmail(), clienteBD.getEmail());
		assertEquals(cliente.getTelefone(), clienteBD.getTelefone());
		
		//Alterando os Dados do Cliente
		cliente.setNome("Bento São Jão");
		cliente.setEmail("admin@example.com");
		cliente.setTelefone("(62) 3124-4060");
		
		Integer countUpdate = dao.alterar(clienteBD);
		assertTrue(countUpdate > 0);
		
		//Trazendo todos os dados do Cliente
		List<Cliente> list = dao.buscarTodos();
		assertNotNull(list);
		assertEquals(1, list.size());
		    
		Integer deleteAll = dao.excluir(clienteBD);
		assertNotNull(deleteAll);
	}

}
