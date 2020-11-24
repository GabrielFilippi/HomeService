package br.univille.homeservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.univille.homeservice.model.Favorito;

public interface FavoritoRepository extends JpaRepository<Favorito, Long> {
    List<Favorito> findAllByClienteId(long idCliente);
}
