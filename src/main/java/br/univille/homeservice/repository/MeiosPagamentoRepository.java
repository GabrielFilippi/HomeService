package br.univille.homeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.univille.homeservice.model.MeiosPagamento;

public interface MeiosPagamentoRepository extends JpaRepository<MeiosPagamento, Long> {
    
}
