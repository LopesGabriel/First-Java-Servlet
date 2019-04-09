package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.SingleConnection;
import models.UsuarioModel;

public class DaoUsuario {
	private Connection connection;
	
	public DaoUsuario() {
		connection = SingleConnection.getConnection();
	}
	
	public void salvar(UsuarioModel user) {
		String sql = "INSERT INTO usuario(login, senha, email, cep, rua, bairro, cidade, uf, file, filetype) VALUES(?,?,?,?,?,?,?,?,?,?)";
		
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, user.getLogin());
			statement.setString(2, user.getSenha());
			statement.setString(3, user.getEmail());
			statement.setString(4, user.getCep());
			statement.setString(5, user.getRua());
			statement.setString(6, user.getBairro());
			statement.setString(7, user.getCidade());
			statement.setString(8, user.getUf());
			statement.setString(9, user.getFile());
			statement.setString(10, user.getFileType());
			statement.execute();
			} catch (SQLException e) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
		}
		
	}
	
	public List<UsuarioModel> listar() throws Exception{
		List<UsuarioModel> lista = new ArrayList<UsuarioModel>();
		String sql = "SELECT * FROM usuario";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		
		while(resultSet.next()) {
			UsuarioModel user = new UsuarioModel(resultSet.getString("login"), resultSet.getString("senha"), 
					resultSet.getString("email"));
			user.setId(resultSet.getLong("id"));
			user.setBairro(resultSet.getString("bairro"));
			user.setCep(resultSet.getString("cep"));
			user.setCidade(resultSet.getString("cidade"));
			user.setRua(resultSet.getString("rua"));
			user.setUf(resultSet.getString("uf"));
			user.setFile(resultSet.getString("file"));
			user.setFileType(resultSet.getString("filetype"));
			lista.add(user);
		}
		
		return lista;
	}
	
	public void delete(String id) {
		try {
			String sql = "DELETE FROM usuario WHERE id = "+ id;
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
	
	public UsuarioModel consultar(String id) {
		String sql = "Select * FROM usuario WHERE id= "+ id;
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			
			if(resultSet.next()) {
				UsuarioModel usuario = new UsuarioModel(resultSet.getString("login"), resultSet.getString("senha"), 
						resultSet.getString("email"));
				usuario.setId(resultSet.getLong("id"));
				usuario.setBairro(resultSet.getString("bairro"));
				usuario.setCep(resultSet.getString("cep"));
				usuario.setCidade(resultSet.getString("cidade"));
				usuario.setRua(resultSet.getString("rua"));
				usuario.setUf(resultSet.getString("uf"));
				usuario.setFile(resultSet.getString("file"));
				usuario.setFileType(resultSet.getString("filetype"));
				return usuario;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean validarLogin(String login) {
		String sql = "Select count(1) as qtd FROM usuario WHERE login= '"+ login +"'";
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
	
	public boolean validarLoginUpdate(String login, String id) {
		String sql = "Select count(1) as qtd FROM usuario WHERE login= '"+ login +"' and id <> " + id;
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

	public void atualizar(UsuarioModel user) {
		String sql = "UPDATE usuario SET login = ?, senha = ?, email = ?, cep = ?, bairro = ?, cidade = ?, uf = ?, rua = ?, file = ?, filetype = ? "
				+ "WHERE id = " + user.getId();
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, user.getLogin());
			statement.setString(2, user.getSenha());
			statement.setString(3, user.getEmail());
			statement.setString(4, user.getCep());
			statement.setString(5, user.getBairro());
			statement.setString(6, user.getCidade());
			statement.setString(7, user.getUf());
			statement.setString(8, user.getRua());
			statement.setString(9, user.getFile());
			statement.setString(10, user.getFileType());
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

}
