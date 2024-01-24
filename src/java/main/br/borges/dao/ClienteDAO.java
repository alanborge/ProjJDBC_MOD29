package br.borges.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.borges.dao.generic.jdbc.ConnectionFactory;
import br.borges.domain.Cliente;

public class ClienteDAO implements IClienteDAO {

	@Override
	public Integer cadastrar(Cliente cliente) throws Exception {
		Connection connection = null;
		PreparedStatement stm = null;
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "INSERT INTO TB_CLIENTE (CLIENTE_ID, CODIGO, NOME, EMAIL, TELEFONE) VALUES (nextval ('SQ_CLIENTE'),?,?,?,?)";
			stm = connection.prepareStatement(sql);
			stm.setString(1, cliente.getCodigo());
			stm.setString(2, cliente.getNome());
			stm.setString(3, cliente.getEmail());
			stm.setString(4, cliente.getTelefone());
			return stm.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			if (stm != null && !stm.isClosed()) {
				stm.close();
			}if (connection != null && !connection.isClosed()) {
				connection.close();
			}
		}
	}

	@Override
	public Cliente consultar(String codigo) throws Exception {
		Connection connection = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		Cliente cliente = null;
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "SELECT * FROM TB_CLIENTE WHERE CODIGO = ?";
			stm = connection.prepareStatement(sql);
			stm.setString(1, codigo);
			rs = stm.executeQuery();
			if (rs.next()) {
				cliente = new Cliente();
				cliente.setId(rs.getLong("cliente_id"));
				cliente.setCodigo(rs.getString("codigo"));
				cliente.setNome(rs.getString("nome"));
				cliente.setEmail(rs.getString("email"));
				cliente.setTelefone(rs.getString("telefone"));
			}
			return cliente;
		} catch (Exception e) {
			throw e;
		} finally {
			if (stm != null && !stm.isClosed()) {
				stm.close();
			}if (connection != null && !connection.isClosed()) {
				connection.close();
			}
		}
	}

	@Override
	public Integer excluir(Cliente cliente) throws Exception {
		Connection connection = null;
		PreparedStatement stm = null;
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "DELETE FROM TB_CLIENTE WHERE CODIGO = ?";
			stm = connection.prepareStatement(sql);
			stm.setString(1, cliente.getCodigo());
			return stm.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			if (stm != null && !stm.isClosed()) {
				stm.close();
			}if (connection != null && !connection.isClosed()) {
				connection.close();
			}
		}
	}

	@Override
	public Integer alterar(Cliente cliente) throws Exception {
		Connection connection = null;
		PreparedStatement stm = null;
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "UPDATE TB_CLIENTE SET  NOME = ?, EMAIL = ?, TELEFONE = ? WHERE CODIGO = ?";
			stm = connection.prepareStatement(sql);
			stm.setString(1, cliente.getNome());
			stm.setString(2, cliente.getEmail());
			stm.setString(3, cliente.getTelefone());
			stm.setString(4, cliente.getCodigo());
			return stm.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			if (stm != null && !stm.isClosed()) {
				stm.close();
			}if (connection != null && !connection.isClosed()) {
				connection.close();
			}
		}
	}

	@Override
	public List<Cliente> buscarTodos() throws Exception {
		Connection connection = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		List<Cliente>list = new ArrayList<>();
		Cliente cliente = null;
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "SELECT * FROM TB_CLIENTE";
			stm = connection.prepareStatement(sql);
			rs = stm.executeQuery();
			
			while (rs.next()) {
				cliente = new Cliente();
				cliente.setId(rs.getLong("CLIENTE_ID"));
				cliente.setCodigo(rs.getString("CODIGO"));
				cliente.setNome(rs.getString("NOME"));
				cliente.setEmail(rs.getString("EMAIL"));
				cliente.setTelefone(rs.getString("TELEFONE"));
				list.add(cliente);	
			}
		} catch (Exception e) {
			throw e;
		}finally {
			if (stm != null && !stm.isClosed()) {
				stm.close();
			}if (connection != null && !connection.isClosed()) {
				connection.close();
			}
		}
		return list;
	}

}
