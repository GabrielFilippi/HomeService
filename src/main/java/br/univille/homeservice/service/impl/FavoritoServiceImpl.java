package br.univille.homeservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.univille.homeservice.model.Favorito;
import br.univille.homeservice.repository.FavoritoRepository;
import br.univille.homeservice.service.FavoritoService;

@Service
public class FavoritoServiceImpl implements FavoritoService {
    @Autowired
    private FavoritoRepository favoritoRepository;

    @Override
    public List<Favorito> getFavoritos(long idCliente) {
        return favoritoRepository.findAllByClienteId(idCliente);
    }

    @Override
    public void deletarFavorito(Favorito favorito) {
        favoritoRepository.delete(favorito);
    }
    
}
