package br.borges.dao;

import java.util.List;

import br.borges.domain.Produto;

public interface IProdutoDAO {
	
public Integer cadastrar(Produto produto) throws Exception;
	
	public Produto consultar(String codigo)throws Exception;
	
	public Integer alterar(Produto produto)throws Exception;
	
	public List<Produto> buscarTodos()throws Exception;
	
	public Integer excluir (Produto produtoBD) throws Exception;

}
