package br.borges.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;



import br.borges.dao.generic.jdbc.ConnectionFactory;
import br.borges.domain.Produto;

public class ProdutoDAO implements IProdutoDAO {

	@Override
	public Integer cadastrar(Produto produto) throws Exception {
		Connection connection = null;
		PreparedStatement stm = null;
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "INSERT INTO TB_PRODUTO (PRODUTO_ID, CODIGO, NOME, PRECO, ESTOQUE) VALUES (nextval('SQ_PRODUTO'),?,?,?,?)";
			stm = connection.prepareStatement(sql);
			stm.setString(1, produto.getCodigo());
			stm.setString(2, produto.getNome());
			stm.setDouble(3, produto.getPreco());
			stm.setInt(4, produto.getEstoque());
			return stm.executeUpdate();	
		} catch (Exception e) {
			throw e;
		}finally {
			if(stm != null && !stm.isClosed()) {
				stm.close();
			}if (connection != null && !connection.isClosed()) {
				connection.close();
			}
		}
	}

	@Override
	public Produto consultar(String codigo) throws Exception {
		Connection connection = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		Produto produto = null;
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "SELECT * FROM TB_PRODUTO WHERE CODIGO = ?";
			stm = connection.prepareStatement(sql);
			stm.setString(1, codigo);
			rs = stm.executeQuery();
			if (rs.next()) {
				produto = new Produto();
				produto.setId(rs.getLong("produto_id"));
				produto.setCodigo(rs.getString("codigo"));
				produto.setNome(rs.getString("nome"));
				produto.setPreco(rs.getDouble("preco"));
				produto.setEstoque(rs.getInt("estoque"));
			}
			return produto;	
		} catch (Exception e) {
			throw e;
		} finally {
			if(stm != null && !stm.isClosed()) {
				stm.close();
			}if (connection != null && !connection.isClosed()) {
				connection.close();
			}
		}
		
	}

	@Override
	public Integer alterar(Produto produto) throws Exception {
		Connection connection = null;
		PreparedStatement stm = null;
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "UPDATE TB_PRODUTO SET NOME = ?, PRECO = ?, ESTOQUE = ? WHERE CODIGO = ?";
			stm = connection.prepareStatement(sql);
			stm.setString(1, produto.getNome());
			stm.setDouble(2, produto.getPreco());
			stm.setInt(3, produto.getEstoque());
			stm.setString(4, produto.getCodigo());
			return stm.executeUpdate();
		} catch (Exception e) {
			throw e;
		}finally {
			if(stm != null && !stm.isClosed()) {
				stm.close();
			}if (connection != null && !connection.isClosed()) {
				connection.close();
			}
		}
		
	}

	@Override
	public List<Produto> buscarTodos() throws Exception {
		Connection connection = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		List<Produto>list = new ArrayList<>();
		Produto produto = null;
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "SELECT * FROM TB_PRODUTO";
			stm = connection.prepareStatement(sql);
			rs = stm.executeQuery();
			
			while (rs.next()) {
				produto = new Produto();
				produto.setId(rs.getLong("PRODUTO_ID"));
				produto.setCodigo(rs.getString("CODIGO"));
				produto.setNome(rs.getString("NOME"));
				produto.setPreco(rs.getDouble("PRECO"));
				produto.setEstoque(rs.getInt("ESTOQUE"));
				list.add(produto);
			}
		} catch (Exception e) {
			throw e;
		}finally {
			if(stm != null && !stm.isClosed()) {
				stm.close();
			}if (connection != null && !connection.isClosed()) {
				connection.close();
			}
		}
		return list;
	}

	@Override
	public Integer excluir(Produto produtoBD) throws Exception {
		Connection connection = null;
		PreparedStatement stm = null;
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "DELETE FROM TB_PRODUTO WHERE CODIGO = ?";
			stm = connection.prepareStatement(sql);
			stm.setString(1, produtoBD.getCodigo());
			return stm.executeUpdate();	
		} catch (Exception e) {
			throw e;
		}finally {
			if(stm != null && !stm.isClosed()) {
				stm.close();
			}if (connection != null && !connection.isClosed()) {
				connection.close();
			}
		}
		
	}

}
