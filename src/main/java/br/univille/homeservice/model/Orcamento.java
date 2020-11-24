package br.univille.homeservice.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Orcamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Profissional profissional;

	@OneToOne(cascade = CascadeType.ALL)
	private Cliente cliente;

	@OneToOne(cascade = CascadeType.ALL)
	private Pagamento pagamento;

	@Column(length = 60)
	private String descricao;

	@Column(length = 2)
	private int status;

	@Temporal(value= TemporalType.TIMESTAMP)
	private Date dataCriacao;

	@Temporal(value= TemporalType.TIMESTAMP)
	private Date dataAgendamento;

	@Temporal(value= TemporalType.TIMESTAMP)
	private Date dataValidade;

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getDataCriacao() {
		return this.dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Date getDataAgendamento() {
		return this.dataAgendamento;
	}

	public void setDataAgendamento(Date dataAgendamento) {
		this.dataAgendamento = dataAgendamento;
	}

	public Date getDataValidade() {
		return this.dataValidade;
	}

	public void setDataValidade(Date dataValidade) {
		this.dataValidade = dataValidade;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Pagamento getPagamento() {
		return this.pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	
	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/*
	public void cadastrar(Orcamento orcamento) {

	}

	public void editar(Orcamento orcamento) {

	}

	public void visualizar(Orcamento orcamento) {

	}

	public void aprovar(Orcamento orcamento) {

	}

	public void cancelar(Orcamento orcamento) {

	}

	public void notificar(Orcamento orcamento) {

	}
	*/

}
