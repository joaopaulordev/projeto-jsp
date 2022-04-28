package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connection.SingleConnectionBanco;
import model.ModelTelefone;

public class DAOTelefoneRepository {
	
	private Connection connection;
	private DAOUsuarioRepository daoUsuario = new DAOUsuarioRepository();
	
	public DAOTelefoneRepository() {
		connection = SingleConnectionBanco.getConnection();
	}
	
	
	public List<ModelTelefone> listarTelefonesByUsuarioPai(Long idUserPai) throws Exception {
		
		List<ModelTelefone> listaTel = new ArrayList<ModelTelefone>();
		
		String sql = "SELECT * FROM telefone WHERE usuario_pai_id = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setLong(1, idUserPai);
		
		ResultSet rs = preparedStatement.executeQuery();
		
		while (rs.next()) {
			ModelTelefone fone = new ModelTelefone();
			fone.setId(rs.getLong("id"));
			fone.setNumero(rs.getString("numero"));
			fone.setUsuario_pai(daoUsuario.consultaUsuarioById(""+rs.getLong("usuario_pai_id")));
			fone.setUsuario_cad(daoUsuario.consultaUsuarioById(""+rs.getLong("usuario_cad_id")));
			listaTel.add(fone);
		}
		
		return listaTel;		
	}
	
	
	
	public void gravarTelefone(ModelTelefone objTelefone) throws Exception {
		
		String sql = "INSERT INTO telefone(numero, usuario_pai_id, usuario_cad_id) VALUES(?, ?, ?);";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		
		preparedStatement.setString(1, objTelefone.getNumero());
		preparedStatement.setLong(2, objTelefone.getUsuario_pai().getId());
		preparedStatement.setLong(3, objTelefone.getUsuario_cad().getId());
		preparedStatement.execute();
		
		connection.commit();
	}
	
	
	
	
	public void deletarTelefone(Long id) throws Exception {
		
		String sql = "DELETE FROM telefone WHERE id = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		
		preparedStatement.setLong(1, id);
		
		preparedStatement.executeUpdate();
		
		connection.commit();
	}
	
	
	public boolean existeTelefone(Long idUser, String numero) throws Exception {
		
		String sql = "SELECT count(1) > 0 as existe FROM telefone WHERE usuario_pai_id = ? AND numero = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		
		preparedStatement.setLong(1, idUser);
		preparedStatement.setString(2, numero);
		
		ResultSet rs = preparedStatement.executeQuery();
		
		rs.next();
		
		return rs.getBoolean("existe");
		
	}

}
