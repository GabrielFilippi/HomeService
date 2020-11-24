package br.univille.homeservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.univille.homeservice.model.Favorito;

@Service
public interface FavoritoService {
    List<Favorito> getFavoritos(long idCliente);
    void deletarFavorito(Favorito favorito);
}
