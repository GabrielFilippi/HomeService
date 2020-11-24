package br.univille.homeservice.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import br.univille.homeservice.model.Agenda;

@Service
public interface AgendaService {
    ArrayList<Agenda> getAgenda(long id, String dataStart, String dataEnd);
    void save(Agenda agenda);
    void deletar(Agenda agenda);
}
