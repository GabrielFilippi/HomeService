package br.univille.homeservice.service;

import org.springframework.stereotype.Service;

import br.univille.homeservice.model.Profissional;

@Service
public interface ProfissionalService {
    void saveProfissional(Profissional profissional);
    Profissional getProfissional(Long id);
}
