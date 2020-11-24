package br.univille.homeservice.model;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Favorito {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@OneToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
	private Profissional profissional;

	@OneToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
	private Cliente cliente;
	
	@Temporal(value= TemporalType.TIMESTAMP)
	private Date dataCriacao;

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

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Date getDataCriacao() {
		return this.dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	
}
