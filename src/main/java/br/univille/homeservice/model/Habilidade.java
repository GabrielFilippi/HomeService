package br.univille.homeservice.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Habilidade {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
	private Profissional profissional;

	@Column(length = 100)
	private String nome;

	@Column(columnDefinition="TEXT")
	private String descricao;

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Profissional getProfissional() {
		return this.profissional;
	}

	public void setProfissional(Profissional profissional) {
		this.profissional = profissional;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void cadatrar(Habilidade habilidade) {

	}

	public void editar(Habilidade habilidade) {

	}

	public void excluir(Habilidade habilidade) {

	}

	public void visualizar(Habilidade habilidade) {

	}

}
