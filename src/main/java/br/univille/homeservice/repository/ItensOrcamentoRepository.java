package br.univille.homeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.univille.homeservice.model.ItensOrcamento;

@Repository
public interface ItensOrcamentoRepository extends JpaRepository<ItensOrcamento, Long>{
}
