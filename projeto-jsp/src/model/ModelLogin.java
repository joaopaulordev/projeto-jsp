package model;

import java.io.Serializable;

public class ModelLogin implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nome;
	private String email;
	private String login;
	private String senha;
	private boolean useradmin;
	private String perfil;
	private String sexo;
	private String fotoUser;
	private String fotoUserExtensao;
	
	
	public boolean isNovo() {
		if (this.id == null) {
			return true;
		}else if (this.id != null && this.id > 0) {
			return false;
		}
		
		return true;
	}
	
		
	public String getFotoUser() {
		return fotoUser;
	}
	
	public void setFotoUser(String fotoUser) {
		this.fotoUser = fotoUser;
	}

	public String getFotoUserExtensao() {
		return fotoUserExtensao;
	}

	public void setFotoUserExtensao(String fotoUserExtensao) {
		this.fotoUserExtensao = fotoUserExtensao;
	}

	public String getSexo() {
		return sexo;
	}
	
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public boolean isUseradmin() {
		return useradmin;
	}

	public void setUseradmin(boolean useradmin) {
		this.useradmin = useradmin;
	}

	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	

}
