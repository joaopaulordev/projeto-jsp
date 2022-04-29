package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connection.SingleConnectionBanco;

public class DAOVersionadorBanco {
	
	private Connection connection;
	
	public DAOVersionadorBanco() {
		connection = SingleConnectionBanco.getConnection();
	}
	
	public void gravaArquivoSQLRodado(String arquivo_sql) throws Exception {
		String sql = "INSERT INTO versionadorbanco(arquivo_sql) VALUES (?);";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, arquivo_sql);
		
		preparedStatement.execute();
	}
	
	public boolean verificaArquivoSqlRodado(String arquivo_sql) throws Exception {
		
		String sql = "select count(1) > 0 as rodado from versionadorbanco where arquivo_sql = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		
		preparedStatement.setString(1, arquivo_sql);
		
		ResultSet rs = preparedStatement.executeQuery();
		
		rs.next();
		
		return rs.getBoolean("rodado");
		
	}

}
