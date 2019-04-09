package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.SingleConnection;
import models.ProdutoModel;

public class DaoProduto {

	private Connection connection;

	public DaoProduto() {
		connection = SingleConnection.getConnection();
	}

	// Methods

	public void salvar(ProdutoModel produto) {
		String sql = "INSERT INTO produtos(nome, quantidade, valor) VALUES(?,?,?)";

		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, produto.getNome());
			statement.setInt(2, produto.getQuantidade());
			statement.setDouble(3, produto.getValor());
			statement.execute();
		} catch (SQLException e) {
			try {
				connection.rollback(); // Desfaz alterações
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

	}
	
	public ProdutoModel consultar(String id) {
		String sql = "Select * FROM produtos WHERE produtoid = "+ id;
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			
			if(resultSet.next()) {
				ProdutoModel produto = new ProdutoModel();
				produto.setId(resultSet.getLong("produtoid"));
				produto.setNome(resultSet.getString("nome"));
				produto.setQuantidade(resultSet.getInt("quantidade"));
				produto.setValor(resultSet.getDouble("valor"));
				return produto;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<ProdutoModel> listar(){
		List<ProdutoModel> lista = new ArrayList<ProdutoModel>();
		String sql = "SELECT * FROM produtos";
		
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				ProdutoModel produto = new ProdutoModel();
				produto.setId(resultSet.getLong("produtoid"));
				produto.setNome(resultSet.getString("nome"));
				produto.setQuantidade(resultSet.getInt("quantidade"));
				produto.setValor(resultSet.getDouble("valor"));
				lista.add(produto);
			}
		}catch(SQLException e) {
			try {
				connection.rollback(); // Desfaz alterações
			}catch(SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
		return lista;
	}
	
	public void delete(String id) {
		try {
			String sql = "DELETE FROM produtos WHERE produtoid = "+ id;
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.execute();
			connection.commit();
		}catch(Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			}catch(SQLException e2) {
				e2.printStackTrace();
			}
		}
		
	}
	
	public void atualizar(ProdutoModel produto) {
		String sql = "UPDATE produtos SET nome = ?, quantidade = ?, valor = ? WHERE produtoid = " + produto.getId();
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, produto.getNome());
			statement.setInt(2, produto.getQuantidade());
			statement.setDouble(3, produto.getValor());
			statement.executeUpdate();
			connection.commit();
		}catch(Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			}catch(SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public boolean validarProdutoNome(String nome) {
		String sql = "Select count(1) as qtd FROM produtos WHERE nome= '"+ nome +"'";
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			
			if(resultSet.next()) {
				return resultSet.getInt("qtd") <= 0; // Return true 
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean validarProdutoNomeUpdate(String nome, String id) {
		String sql = "Select count(1) as qtd FROM produtos WHERE nome= '"+ nome +"' and produtoid <> " + id;
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			
			if(resultSet.next()) {
				return resultSet.getInt("qtd") <= 0; // Return true 
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
