package br.univille.homeservice.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Usuario {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(length=60)
	private String login;

	@Column(length=266)
	private String senha;

	@Column(length=1)
	private int permissao;

	@Temporal(value= TemporalType.TIMESTAMP)
	private Date ultimoAcesso;
	
	@Column(length=60)
	private String ultimoIp;


	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public int getPermissao() {
		return this.permissao;
	}

	public void setPermissao(int permissao) {
		this.permissao = permissao;
	}

	public Date getUltimoAcesso() {
		return this.ultimoAcesso;
	}

	public void setUltimoAcesso(Date ultimoAcesso) {
		this.ultimoAcesso = ultimoAcesso;
	}

	public String getUltimoIp() {
		return this.ultimoIp;
	}

	public void setUltimoIp(String ultimoIp) {
		this.ultimoIp = ultimoIp;
	}

	public Usuario cadastrar(Usuario usuario) {
		return null;
	}

	public void alterarSenha(String senhaAtual, String novaSenha) {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

}
