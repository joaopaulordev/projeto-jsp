package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.SingleConnectionBanco;
import model.ModelLogin;

public class DAOUsuarioRepository {
	
	private Connection connection;
	
	public DAOUsuarioRepository() {
		connection = SingleConnectionBanco.getConnection();
	}
	
	public ModelLogin gravarUsuario(ModelLogin obj, Long usuarioLogado) throws SQLException {
		
		String sql = "";
		
		if(obj.isNovo()) {
			sql = "INSERT INTO model_login(login, senha, nome, email, usuario_id, perfil, sexo, cep, logradouro, bairro, localidade, uf, numero) VALUES (?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?);";
			PreparedStatement preparedSql = connection.prepareStatement(sql);
			preparedSql.setString(1, obj.getLogin());
			preparedSql.setString(2, obj.getSenha());
			preparedSql.setString(3, obj.getNome());
			preparedSql.setString(4, obj.getEmail());
			preparedSql.setLong(5, usuarioLogado);
			preparedSql.setString(6, obj.getPerfil());
			preparedSql.setString(7, obj.getSexo());			
			preparedSql.setString(8, obj.getCep());
			preparedSql.setString(9, obj.getLogradouro());
			preparedSql.setString(10, obj.getBairro());
			preparedSql.setString(11, obj.getLocalidade());
			preparedSql.setString(12, obj.getUf());
			preparedSql.setString(13, obj.getNumero());
			
			preparedSql.execute();
			connection.commit();	
			
			if (obj.getFotoUser() != null && !obj.getFotoUser().isEmpty()) {
				
				sql = "UPDATE model_login SET fotouser = ?, extensaofotouser = ? WHERE login = ?";
				preparedSql = connection.prepareStatement(sql);
				preparedSql.setString(1, obj.getFotoUser());
				preparedSql.setString(2, obj.getFotoUserExtensao());
				preparedSql.setString(3, obj.getLogin());
				preparedSql.executeUpdate();
				connection.commit();
			}
			
		}else {
			sql = "UPDATE model_login SET login=?, senha=?, nome=?, email=?, perfil=?, sexo=?, cep=?, logradouro=?, bairro=?, localidade=?, uf=?, numero=? WHERE id = ?";
			PreparedStatement preparedSql = connection.prepareStatement(sql);
			preparedSql.setString(1, obj.getLogin());
			preparedSql.setString(2, obj.getSenha());
			preparedSql.setString(3, obj.getNome());
			preparedSql.setString(4, obj.getEmail());
			preparedSql.setString(5, obj.getPerfil());
			preparedSql.setString(6, obj.getSexo());
			
			preparedSql.setString(7, obj.getCep());
			preparedSql.setString(8, obj.getLogradouro());
			preparedSql.setString(9, obj.getBairro());
			preparedSql.setString(10, obj.getLocalidade());
			preparedSql.setString(11, obj.getUf());
			preparedSql.setString(12, obj.getNumero());
		
			preparedSql.setLong(13, obj.getId());
			
			preparedSql.executeUpdate();
			connection.commit();
			
			if (obj.getFotoUser() != null && !obj.getFotoUser().isEmpty()) {
				
				sql = "UPDATE model_login SET fotouser = ?, extensaofotouser = ? WHERE login = ?";
				preparedSql = connection.prepareStatement(sql);
				preparedSql.setString(1, obj.getFotoUser());
				preparedSql.setString(2, obj.getFotoUserExtensao());
				preparedSql.setString(3, obj.getLogin());
				preparedSql.executeUpdate();
				connection.commit();
			}
		}	
		
		return this.consultaUsuarioByLogin(obj.getLogin());		
	}
	
	
	
	public ModelLogin consultaUsuarioByLogin(String login) throws SQLException {
		
		ModelLogin modelLogin = new ModelLogin();
		
		String sql = "SELECT * FROM model_login where upper(login) = upper(?);";
		PreparedStatement preparedSQL = connection.prepareStatement(sql);
		preparedSQL.setString(1, login);
		
		ResultSet rs = preparedSQL.executeQuery();
		while (rs.next()) {
			modelLogin.setId(rs.getLong("id"));
			modelLogin.setLogin(rs.getString("login"));
			modelLogin.setSenha(rs.getString("senha"));
			modelLogin.setNome(rs.getString("nome"));
			modelLogin.setEmail(rs.getString("email"));
			modelLogin.setUseradmin(rs.getBoolean("useradmin"));
			modelLogin.setPerfil(rs.getString("perfil"));
			modelLogin.setSexo(rs.getString("sexo"));
			modelLogin.setFotoUser(rs.getString("fotouser"));
			
			modelLogin.setCep(rs.getString("cep"));
			modelLogin.setLogradouro(rs.getString("logradouro"));
			modelLogin.setBairro(rs.getString("bairro"));
			modelLogin.setLocalidade(rs.getString("localidade"));
			modelLogin.setUf(rs.getString("uf"));
			modelLogin.setNumero(rs.getString("numero"));
		}		
		
		return modelLogin;		
	}
	
	public ModelLogin consultaUsuarioById(String id) throws SQLException {
		
		ModelLogin modelLogin = new ModelLogin();
		
		String sql = "SELECT * FROM model_login where id = ?;";
		PreparedStatement preparedSQL = connection.prepareStatement(sql);
		preparedSQL.setLong(1, Long.parseLong(id));
		
		ResultSet rs = preparedSQL.executeQuery();
		while (rs.next()) {
			modelLogin.setId(rs.getLong("id"));
			modelLogin.setLogin(rs.getString("login"));
			modelLogin.setSenha(rs.getString("senha"));
			modelLogin.setNome(rs.getString("nome"));
			modelLogin.setEmail(rs.getString("email"));
			modelLogin.setPerfil(rs.getString("perfil"));
			modelLogin.setSexo(rs.getString("sexo"));
			modelLogin.setFotoUser(rs.getString("fotouser"));
			modelLogin.setFotoUserExtensao(rs.getString("extensaofotouser"));
			
			modelLogin.setCep(rs.getString("cep"));
			modelLogin.setLogradouro(rs.getString("logradouro"));
			modelLogin.setBairro(rs.getString("bairro"));
			modelLogin.setLocalidade(rs.getString("localidade"));
			modelLogin.setUf(rs.getString("uf"));
			modelLogin.setNumero(rs.getString("numero"));
		}		
		
		return modelLogin;		
	}
	
	
	public List<ModelLogin> buscarUsuarioByNome(String nome, Long usuarioLogado) throws Exception{
		
		List<ModelLogin> listaRetorno = new ArrayList<ModelLogin>();
		
		String sql = "SELECT * FROM model_login WHERE upper(nome) LIKE upper(?) AND userAdmin is false AND usuario_id = ? limit 5";
		PreparedStatement preparedSQL = connection.prepareStatement(sql);
		preparedSQL.setString(1, "%"+ nome +"%");
		preparedSQL.setLong(2, usuarioLogado);
		
		ResultSet rs = preparedSQL.executeQuery();
		while(rs.next()) {
			ModelLogin obj = new ModelLogin();
			obj.setLogin(rs.getString("login"));
			obj.setId(rs.getLong("id"));
			obj.setNome(rs.getString("nome"));
			obj.setEmail(rs.getString("email"));
			obj.setSexo(rs.getString("sexo"));
			
			listaRetorno.add(obj);
		}
		
		return listaRetorno;
	}
	
	public List<ModelLogin> buscarUsuarioByNomePaginadoOffSet(String nome, Long usuarioLogado, String offSet) throws Exception{
		
		List<ModelLogin> listaRetorno = new ArrayList<ModelLogin>();
		
		String sql = "SELECT * FROM model_login WHERE upper(nome) LIKE upper(?) AND userAdmin is false AND usuario_id = ? offset "+ offSet +" limit 5";
		PreparedStatement preparedSQL = connection.prepareStatement(sql);
		preparedSQL.setString(1, "%"+ nome +"%");
		preparedSQL.setLong(2, usuarioLogado);
		
		ResultSet rs = preparedSQL.executeQuery();
		while(rs.next()) {
			ModelLogin obj = new ModelLogin();
			obj.setLogin(rs.getString("login"));
			obj.setId(rs.getLong("id"));
			obj.setNome(rs.getString("nome"));
			obj.setEmail(rs.getString("email"));
			obj.setSexo(rs.getString("sexo"));
			
			listaRetorno.add(obj);
		}
		
		return listaRetorno;
	}
	
	public int buscarUsuarioByNomeTotalPaginas(String nome, Long usuarioLogado) throws Exception{
		
		String sql = "SELECT count(1) as total FROM model_login WHERE upper(nome) LIKE upper(?) AND userAdmin is false AND usuario_id = ?";
		PreparedStatement preparedSQL = connection.prepareStatement(sql);
		preparedSQL.setString(1, "%"+ nome +"%");
		preparedSQL.setLong(2, usuarioLogado);
		
		ResultSet rs = preparedSQL.executeQuery();
		rs.next();
		
		Double cadastros = rs.getDouble("total");
		
		Double porPagina = 5.0;
		
		Double pagina = cadastros / porPagina;
		
		Double resto = pagina % 2;
		
		if (resto > 0) {
			pagina++;
		}
		
		return pagina.intValue();
	}

		
	
	public List<ModelLogin> buscarUsuarios(Long usuarioLogado) throws Exception{
		
		List<ModelLogin> listaRetorno = new ArrayList<ModelLogin>();
		
		String sql = "SELECT * FROM model_login where userAdmin is false AND usuario_id = ? order by nome limit 5";
		PreparedStatement preparedSQL = connection.prepareStatement(sql);
		preparedSQL.setLong(1, usuarioLogado);
		
		ResultSet rs = preparedSQL.executeQuery();
		while(rs.next()) {
			ModelLogin obj = new ModelLogin();
			obj.setLogin(rs.getString("login"));
			obj.setId(rs.getLong("id"));
			obj.setNome(rs.getString("nome"));
			obj.setEmail(rs.getString("email"));
			obj.setPerfil(rs.getString("perfil"));
			obj.setSexo(rs.getString("sexo"));
						
			listaRetorno.add(obj);
		}
		
		return listaRetorno;
	}
	
	
	
	public int totalPagina(Long usuarioLogado) throws Exception{
		
		String sql = "select count(1) as total from model_login where userAdmin is false AND usuario_id = ?";
		PreparedStatement preparedSQL = connection.prepareStatement(sql);
		preparedSQL.setLong(1, usuarioLogado);
		
		ResultSet rs = preparedSQL.executeQuery();
		rs.next();
		
		Double cadastros = rs.getDouble("total");
		
		Double porPagina = 5.0;
		
		Double pagina = cadastros / porPagina;
		
		Double resto = pagina % 2;
		
		if (resto > 0) {
			pagina++;
		}
		
		return pagina.intValue();
	}
	
	
	public List<ModelLogin> buscarUsuariosPaginado(Long usuarioLogado, int offset) throws Exception{
		
		List<ModelLogin> listaRetorno = new ArrayList<ModelLogin>();
		
		String sql = "SELECT * FROM model_login where userAdmin is false AND usuario_id = ? order by nome offset "+ offset +" limit 5";
		PreparedStatement preparedSQL = connection.prepareStatement(sql);
		preparedSQL.setLong(1, usuarioLogado);
		
		ResultSet rs = preparedSQL.executeQuery();
		while(rs.next()) {
			ModelLogin obj = new ModelLogin();
			obj.setLogin(rs.getString("login"));
			obj.setId(rs.getLong("id"));
			obj.setNome(rs.getString("nome"));
			obj.setEmail(rs.getString("email"));
			obj.setPerfil(rs.getString("perfil"));
			obj.setSexo(rs.getString("sexo"));
						
			listaRetorno.add(obj);
		}
		
		return listaRetorno;
	}
	
		
	public boolean validarLogin(String login) throws Exception {
		String sql = "select count(1) > 0 as existe from model_login where upper(login) = upper(?);";
		
		PreparedStatement preparedSQL = connection.prepareStatement(sql);
		preparedSQL.setString(1, login);
		
		ResultSet rs = preparedSQL.executeQuery();
		rs.next();
		
		return rs.getBoolean("existe");
	}
	
	
	public void deletarUsuario(String idUsuario) throws SQLException {
		String sql = "DELETE FROM model_login WHERE id = ?;";
		
		PreparedStatement preparedSQL = connection.prepareStatement(sql);
		preparedSQL.setLong(1, Long.parseLong(idUsuario));
		
		preparedSQL.executeUpdate();
		
		connection.commit();		
	}
	
	

}
