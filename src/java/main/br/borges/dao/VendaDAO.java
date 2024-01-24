package br.borges.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.borges.dao.generic.jdbc.ConnectionFactory;
import br.borges.domain.Cliente;
import br.borges.domain.Venda;

public class VendaDAO implements IVendaDAO {

	@Override
	public Integer cadastrar(Venda venda) throws Exception {
		Connection connection = null;
		PreparedStatement stm = null;
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "INSERT INTO TB_VENDA (VENDA_ID, CODIGO, CLIENTE_ID, PRODUTO_ID, QUANTIDADE, TOTAL)VALUES (nextval('SQ_VENDA'),?,?,?,?,?)";
			stm = connection.prepareStatement(sql);
			stm.setString(1, venda.getCodigo());
			stm.setLong(2, venda.getCliente_id());
			stm.setLong(3, venda.getProduto_id());
			stm.setInt(4, venda.getQuantidade());
			stm.setInt(5, venda.getTotal());
			return stm.executeUpdate();
		} catch (Exception e) {
			throw e;
		}finally {
			if (stm != null && !stm.isClosed()) {
				stm.close();
			}if (connection != null && !connection.isClosed()) {
				connection.close();
			}
		}
	}

	@Override
	public Venda consultar(String codigo) throws Exception {
		Connection connection = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		Venda venda = null;
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "SELECT * FROM TB_VENDA WHERE CODIGO = ?";
			stm = connection.prepareStatement(sql);
			stm.setString(1, codigo);
			rs = stm.executeQuery();
			if (rs.next()) {
				venda = new Venda();
				venda.setId(rs.getLong("venda_id"));
				venda.setCodigo(rs.getString("codigo"));
				venda.setCliente_id(rs.getLong("cliente_id"));
				venda.setProduto_id(rs.getLong("produto_id"));
				venda.setQuantidade(rs.getInt("quantidade"));
				venda.setTotal(rs.getInt("total"));
			}
			return venda;	
		} catch (Exception e) {
			throw e;
		}finally {			
			if (stm != null && !stm.isClosed()) {
				stm.close();
			}if (connection != null && !connection.isClosed()) {
				connection.close();
			}        
		}	
	}

	@Override
	public Integer alterar(Venda venda) throws Exception {
		Connection connection = null;
		PreparedStatement stm = null;
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "UPDATE TB_VENDA SET QUANTIDADE = ?, TOTAL = ? WHERE CODIGO =?";
			stm = connection.prepareStatement(sql);
			stm.setInt(1, venda.getQuantidade());
			stm.setInt(2, venda.getTotal());
			stm.setString(3, venda.getCodigo());
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
	public List<Venda> buscarTodos() throws Exception {
		Connection connection = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		List<Venda>list = new ArrayList<>();
		Venda venda = null;
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "SELECT * FROM TB_VENDA";
			stm = connection.prepareStatement(sql);
			rs = stm.executeQuery();
			
			while (rs.next()) {
				venda = new Venda();
				venda.setId(rs.getLong("VENDA_ID"));
				venda.setCodigo(rs.getString("CODIGO"));
				venda.setCliente_id(rs.getLong("CLIENTE_ID"));
				venda.setProduto_id(rs.getLong("PRODUTO_ID"));
				venda.setQuantidade(rs.getInt("QUANTIDADE"));
				venda.setTotal(rs.getInt("TOTAL"));
				list.add(venda);
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

	@Override
	public Integer excluir(Venda vendaBD) throws Exception {
		Connection connection = null;
		PreparedStatement stm = null;
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "DELETE FROM TB_VENDA WHERE CODIGO = ?";
			stm = connection.prepareStatement(sql);
			stm.setString(1, vendaBD.getCodigo());
			return stm.executeUpdate();
		} catch (Exception e) {
			throw e;
		}finally {
			if (stm != null && !stm.isClosed()) {
				stm.close();
			}if (connection != null && !connection.isClosed()) {
				connection.close();
			}
		}
		
	}

}
