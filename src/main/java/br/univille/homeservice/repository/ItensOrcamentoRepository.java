package br.univille.homeservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.univille.homeservice.model.ItensOrcamento;

@Repository
public interface ItensOrcamentoRepository extends JpaRepository<ItensOrcamento, Long>{
    List<ItensOrcamento> findAllByOrcamentoId(long idOrcamento);
}
