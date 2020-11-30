package br.univille.homeservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.univille.homeservice.model.Habilidade;

@Repository
public interface HabilidadeRepository extends JpaRepository<Habilidade, Long>{
    List<Habilidade> findAllByProfissionalId(long idProfissional);
}
