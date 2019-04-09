package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connection.SingleConnection;
import models.UsuarioModel;

public class DaoLogin {
	
	private Connection connection;
	private UsuarioModel user;
	
	public DaoLogin() {
		connection = SingleConnection.getConnection();
	}
	
	public boolean validarLogin(String login, String senha) throws Exception{
		
		String sql = "SELECT * FROM usuario WHERE login = '"+ login + "' and senha = '" + senha + "'";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		if(resultSet.next()) {
			 user = new UsuarioModel(resultSet.getString("login"), resultSet.getString("senha"), 
					resultSet.getString("email"));
			user.setId(resultSet.getLong("id"));
			user.setBairro(resultSet.getString("bairro"));
			user.setCep(resultSet.getString("cep"));
			user.setCidade(resultSet.getString("cidade"));
			user.setRua(resultSet.getString("rua"));
			user.setUf(resultSet.getString("uf"));
			user.setFile(resultSet.getString("file"));
			user.setFileType(resultSet.getString("filetype"));
			return true;  // Possui usuário
		}else {
			return false; // Não possui usuário
		}
	}
	
	public UsuarioModel consultar() {
		return user;
	}

}
