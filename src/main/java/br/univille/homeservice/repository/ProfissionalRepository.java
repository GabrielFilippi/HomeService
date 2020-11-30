package br.univille.homeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.univille.homeservice.model.Profissional;

@Repository
public interface ProfissionalRepository extends JpaRepository<Profissional, Long>{
    Profissional findByUsuarioId(long idUser);
    
}
