package br.univille.homeservice.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import br.univille.homeservice.model.Agenda;
import br.univille.homeservice.model.Profissional;

@Service
public interface AgendaService {
    ArrayList<Agenda> getAgenda(long id);
}
