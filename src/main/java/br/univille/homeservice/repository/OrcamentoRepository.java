package br.univille.homeservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.univille.homeservice.model.Orcamento;

@Repository
public interface OrcamentoRepository extends JpaRepository<Orcamento, Long>{
    List<Orcamento> findAllByProfissionalId(long idProfissional);
    List<Orcamento> findTop3ByProfissionalId(long idProfissional);

    List<Orcamento> findAllByClienteId(long idCliente);
    List<Orcamento> findTop3ByClienteId(long idCliente);
}
