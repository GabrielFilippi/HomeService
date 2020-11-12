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
public class Cliente {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@OneToOne(cascade = CascadeType.ALL) //CascadeType.ALL -> Tudo que acontecer no usuario reflete  no Cliente, como por exemplo apaga o Usuario, apaga o cliente.
	private Usuario usuario;             //CascadeType.REFRESH -> Cada vez que trazer o Cliente vai Trazer o Usuario junto.
										 //CascadeType.MERGE -> se acontecer uma mudanca no Usuario Ja salva o Cliente.
										 
	@OneToOne(cascade = CascadeType.ALL)
	private Pessoa pessoa;

	@OneToOne(cascade = CascadeType.ALL)
	private Endereco endereco;

	@OneToOne(cascade = CascadeType.ALL)
	private MeiosPagamento meiosPagamento;

	@Temporal(value= TemporalType.TIMESTAMP)
	private Date dataCriacao;
	
	@Temporal(value= TemporalType.TIMESTAMP)
	private Date dataModificacao;


/*
	private Avaliacao avaliacao;

	private Favorito favorito;

	private Avaliacao avaliacao;

	private CartaoCredito cartaoCredito;

	private Recibo recibo;
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

	public Endereco getEndereco() {
		return this.endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public MeiosPagamento getMeiosPagamento() {
		return this.meiosPagamento;
	}

	public void setMeiosPagamento(MeiosPagamento meiosPagamento) {
		this.meiosPagamento = meiosPagamento;
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
