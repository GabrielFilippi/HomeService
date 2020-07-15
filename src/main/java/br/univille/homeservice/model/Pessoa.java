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
public class Pessoa {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(length = 150)
	private String nome;

	@Column(length = 15)
	private String cpf;

	@Column(length = 255)
	private String foto;

	@Column(length = 1)
	private char sexo;

	@Column(length = 15)
	private String telefone;

	@Column(length = 15)
	private String celular;

	@Column(length = 255)
	private String email;

	@Temporal(value= TemporalType.TIMESTAMP)
	private Date dataCriacao;

	@Temporal(value= TemporalType.TIMESTAMP)
	private Date dataModificacao;

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getFoto() {
		return this.foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public char getSexo() {
		return this.sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public String getTelefone() {
		return this.telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return this.celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDataCriacao() {
		return this.dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao; 
	}

	public Date getDataModificacao() {
		return this.dataModificacao;
	}

	public void setDataModificacao(Date dataModificacao) {
		this.dataModificacao = dataModificacao;
	}


}
