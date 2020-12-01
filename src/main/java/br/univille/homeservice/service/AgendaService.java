package br.univille.homeservice.service;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.stereotype.Service;

import br.univille.homeservice.model.Agenda;

@Service
public interface AgendaService {
    ArrayList<Agenda> getAgenda(long id, Date dataStart, Date dataEnd);
    void save(Agenda agenda);
    void deletar(Agenda agenda);
}
