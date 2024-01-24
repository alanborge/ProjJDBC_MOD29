package br.borges.dao;

import java.util.List;

import br.borges.domain.Cliente;
import br.borges.domain.Venda;

public interface IVendaDAO {
	
public Integer cadastrar(Venda venda) throws Exception;
	
	public Venda consultar(String codigo)throws Exception;
	
	public Integer alterar(Venda venda)throws Exception;
	
	public List<Venda> buscarTodos()throws Exception;
	
	public Integer excluir (Venda vendaBD) throws Exception;

}
