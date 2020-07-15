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
	@Temporal(value= TemporalType.TIMESTAMP)
	private Date dataCriacao;
	
	@Temporal(value= TemporalType.TIMESTAMP)
	private Date dataModificacao;

/*
	private Avaliacao avaliacao;

	private Favorito favorito;

	private Avaliacao avaliacao;

	
*/
	@OneToOne(cascade = CascadeType.ALL)
	private Pessoa pessoa;

/*
	private MeiosPagamento meiosPagamento;

	private CartaoCredito cartaoCredito;

	private Endereco endereco;

	private Recibo recibo;
*/
	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


}
