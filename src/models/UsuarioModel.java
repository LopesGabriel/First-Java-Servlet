package models;

public class UsuarioModel {
	
	private Long id;
	private String login;
	private String senha;
	private String email;
	private String cep;
	private String rua;
	private String bairro;
	private String cidade;
	private String uf;
	private String file;
	private String fileType;
	private String tempFotoUser;
	
	public UsuarioModel(String login, String senha, String email) {
		this.login = login;
		this.senha = senha;
		this.email = email;
	}
	
	public String getTempFotoUser() {
		
		tempFotoUser = "data:" + fileType + ";base64," + file;
		
		return tempFotoUser;
	}
	
	public void setFile(String file) {
		this.file = file;
	}
	
	public String getFile() {
		return file;
	}
	
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	
	public String getFileType() {
		return fileType;
	}
	
	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		//int id = Integer.parseInt(idd);
		this.id = id;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
