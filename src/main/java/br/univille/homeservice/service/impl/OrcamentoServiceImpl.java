package br.univille.homeservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.univille.homeservice.model.ItensOrcamento;
import br.univille.homeservice.model.Orcamento;
import br.univille.homeservice.repository.ItensOrcamentoRepository;
import br.univille.homeservice.repository.OrcamentoRepository;
import br.univille.homeservice.service.OrcamentoService;

@Service
public class OrcamentoServiceImpl implements OrcamentoService {

    @Autowired
    private OrcamentoRepository orcamentoRepository;
    @Autowired
    private ItensOrcamentoRepository itensOrcamentoRepository;

    @Override
    public List<Orcamento> visualizarTodos(long idProfissional) {
        return orcamentoRepository.findAllByProfissionalId(idProfissional);
    }

    @Override
    public void saveOrcamento(Orcamento orcamento) {
        orcamentoRepository.save(orcamento);
    }

    @Override
    public void saveItensOrcamento(ItensOrcamento itensOrcamento) {
        itensOrcamentoRepository.save(itensOrcamento);
    }
    
}
