package br.univille.homeservice.model;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Profissional {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@OneToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
	private Usuario usuario;

	@OneToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
	private Pessoa pessoa;

	@OneToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
	private Perfil perfil;

	@Column(length = 1)
	private boolean statusAtivacao;

	@Temporal(value= TemporalType.TIMESTAMP)
	private Date dataCriacao;

	@Temporal(value= TemporalType.TIMESTAMP)
	private Date dataModificacao;

	/*

	private Avaliacao[] avaliacao;
	
	*/

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Pessoa getPessoa() {
		return this.pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Perfil getPerfil() {
		return this.perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public boolean isStatusAtivacao() {
		return this.statusAtivacao;
	}

	public void setStatusAtivacao(boolean statusAtivacao) {
		this.statusAtivacao = statusAtivacao;
	}

	public Date getDataModificacao() {
		return this.dataModificacao;
	}

	public void setDataModificacao(Date dataModificacao) {
		this.dataModificacao = dataModificacao;
	}

	public Date getDataCriacao() {
		return this.dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

}
