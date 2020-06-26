package br.univille.homeservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.univille.homeservice.model.Cliente;


/**
 * 
 * AQUI IMPLEMENTAMOS A REGRA DE NEGOCIO DE CADA CLASSE
 * 
 */

@Service
public interface MinhaContaService {
    
	public void cadastrar(Cliente cliente);

	public void editar(Cliente cliente);

    public List<Cliente> visualizar();
    
}