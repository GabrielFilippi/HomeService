package br.univille.homeservice.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Profissional {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private boolean statusAtivacao;

	@Temporal(value= TemporalType.TIMESTAMP)
	private Date dataCriacao;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="habilidade_id")
	private List<Habilidade> listaHabilidade = new ArrayList<Habilidade>();

	

	/*
	private ServicoOferecido[] servicoOferecido;

	private Certificacao[] certificacao;

	private Orcamento orcamento;

	private Agenda agenda;

	private Agenda[] agenda;

	private Favorito[] favorito;

	private Avaliacao[] avaliacao;

	private Pessoa pessoa;
	*/

	@OneToOne(cascade = CascadeType.ALL)
	private Usuario usuario;

	

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isStatusAtivacao() {
		return this.statusAtivacao;
	}

	public void setStatusAtivacao(boolean statusAtivacao) {
		this.statusAtivacao = statusAtivacao;
	}

	public Date getDataCriacao() {
		return this.dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public List<Habilidade> getListaHabilidade() {
		return this.listaHabilidade;
	}

	public void setListaHabilidade(List<Habilidade> listaHabilidade) {
		this.listaHabilidade = listaHabilidade;
	}
	
	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void cadastrar(Profissional profissional) {

	}

	public void editar(Profissional profissional) {

	}

	public void excluir(Profissional profissional) {

	}

	public void visualizar(Profissional profissional) {

	}

}
