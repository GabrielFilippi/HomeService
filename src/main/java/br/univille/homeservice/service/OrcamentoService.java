package br.univille.homeservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.univille.homeservice.model.ItensOrcamento;
import br.univille.homeservice.model.Orcamento;

@Service
public interface OrcamentoService {
    List<Orcamento> visualizarTodos(long idProfissional);
    Orcamento visualizarOrcamento(long idOrcamento);
    void saveOrcamento(Orcamento orcamento);
    void saveItensOrcamento(ItensOrcamento itensOrcamento);
    List<ItensOrcamento> visualizarTodosItens(long idOrcamento);
    List<Orcamento> visualizarTop3Orcamentos(long idProfissional);
}
