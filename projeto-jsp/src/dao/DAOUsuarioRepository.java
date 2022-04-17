package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.SingleConnectionBanco;
import model.ModelLogin;

public class DAOUsuarioRepository {
	
	private Connection connection;
	
	public DAOUsuarioRepository() {
		connection = SingleConnectionBanco.getConnection();
	}
	
	public ModelLogin gravarUsuario(ModelLogin obj) throws SQLException {
		
		String sql = "";
		
		if(obj.isNovo()) {
			sql = "INSERT INTO model_login(login, senha, nome, email) VALUES (?, ?, ?, ?);";
			PreparedStatement preparedSql = connection.prepareStatement(sql);
			preparedSql.setString(1, obj.getLogin());
			preparedSql.setString(2, obj.getSenha());
			preparedSql.setString(3, obj.getNome());
			preparedSql.setString(4, obj.getEmail());
			preparedSql.execute();
						
		}else {
			sql = "UPDATE model_login SET login=?, senha=?, nome=?, email=? WHERE id = ?";
			PreparedStatement preparedSql = connection.prepareStatement(sql);
			preparedSql.setString(1, obj.getLogin());
			preparedSql.setString(2, obj.getSenha());
			preparedSql.setString(3, obj.getNome());
			preparedSql.setString(4, obj.getEmail());
			preparedSql.setLong(5, obj.getId());
			preparedSql.execute();			
		}
		
		connection.commit();		
		
		return this.consultaUsuario(obj.getLogin());		
	}
	
	
	
	public ModelLogin consultaUsuario(String login) throws SQLException {
		
		ModelLogin modelLogin = new ModelLogin();
		
		String sql = "SELECT login, senha, id, nome, email FROM model_login where upper(login) = upper(?);";
		PreparedStatement preparedSQL = connection.prepareStatement(sql);
		preparedSQL.setString(1, login);
		
		ResultSet rs = preparedSQL.executeQuery();
		while (rs.next()) {
			modelLogin.setId(rs.getLong("id"));
			modelLogin.setLogin(rs.getString("login"));
			modelLogin.setSenha(rs.getString("senha"));
			modelLogin.setNome(rs.getString("nome"));
			modelLogin.setEmail(rs.getString("email"));
		}		
		
		return modelLogin;		
	}
	
		
	public boolean validarLogin(String login) throws Exception {
		
		String sql = "select count(1) > 0 as existe from model_login where upper(login) = upper(?);";
		
		PreparedStatement preparedSQL = connection.prepareStatement(sql);
		preparedSQL.setString(1, login);
		
		ResultSet rs = preparedSQL.executeQuery();
		rs.next();
		
		return rs.getBoolean("existe");
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
