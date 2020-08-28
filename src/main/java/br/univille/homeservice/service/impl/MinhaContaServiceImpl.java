package br.univille.homeservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.univille.homeservice.model.Pessoa;
import br.univille.homeservice.repository.PessoaRepository;
import br.univille.homeservice.service.MinhaContaService;

@Service
public class MinhaContaServiceImpl implements MinhaContaService {
    @Autowired
    private PessoaRepository repository;

    @Override
    public void cadastrar(Pessoa pessoa) {
        repository.save(pessoa);
        
    }
    
    @Override
    public void deletar(Pessoa pessoa) {
        repository.delete(pessoa);
    }

    @Override
    public List<Pessoa> visualizarTodos() {
        return repository.findAll();
    }
    
}