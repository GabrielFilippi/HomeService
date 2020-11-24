package br.univille.homeservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.univille.homeservice.model.Orcamento;

@Service
public interface OrcamentoService {
    List<Orcamento> visualizarTodos(long idProfissional);
}
