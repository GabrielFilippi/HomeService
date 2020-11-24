package br.univille.homeservice.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Agenda {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
	private Profissional profissional;

	@Column (length = 50)
	private String titulo;

	@Column (length = 250)
	private String descricao;
	
	//@Temporal(value = TemporalType.DATE)
	@Column (length = 50)
	private String dataInicio;

	//@Temporal(value = TemporalType.DATE)
	@Column (length = 50)
	private String dataFinal;

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

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDataInicio() {
		return this.dataInicio;
	}

	public void setDataInicio(String data) {
		this.dataInicio = data;
	}
	
	public String getDataFinal() {
		return this.dataFinal;
	}

	public void setDataFinal(String dataFinal) {
		this.dataFinal = dataFinal;
	}

}
