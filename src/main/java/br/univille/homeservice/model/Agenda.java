package br.univille.homeservice.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Agenda {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
	private Profissional profissional;

	@Column (length = 50)
	private String titulo;

	@Column (length = 255)
	private String descricao;
	
	@Temporal(value = TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm")
	private Date dataInicio;

	@Temporal(value = TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm")
	private Date dataFinal;

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

	public Date getDataInicio() {
		return this.dataInicio;
	}

	public void setDataInicio(Date data) {
		this.dataInicio = data;
	}
	
	public Date getDataFinal() {
		return this.dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

}
