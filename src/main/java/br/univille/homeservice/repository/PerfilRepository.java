package br.univille.homeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.univille.homeservice.model.Perfil;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long> {
}
