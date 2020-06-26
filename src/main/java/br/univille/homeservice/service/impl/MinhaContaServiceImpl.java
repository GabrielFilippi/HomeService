package br.univille.homeservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.univille.homeservice.model.Cliente;
import br.univille.homeservice.repository.ClienteRepository;
import br.univille.homeservice.service.MinhaContaService;

public class MinhaContaServiceImpl implements MinhaContaService {
    @Autowired
    private ClienteRepository repository;

    @Override
    public void cadastrar(Cliente cliente) {
        repository.save(cliente);
        
    }

    @Override
    public void editar(Cliente cliente) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<Cliente> visualizar() {
        return repository.findAll();
    }

    

}