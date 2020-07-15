package br.univille.homeservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.univille.homeservice.model.Pessoa;


/**
 * 
 * AQUI IMPLEMENTAMOS A REGRA DE NEGOCIO DE CADA CLASSE
 * 
 */

@Service
public interface MinhaContaService {
    
	public void cadastrar(Pessoa pessoa);

	public void deletar(Pessoa pessoa);

	public List<Pessoa> visualizarTodos();

}