package br.univille.homeservice.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.univille.homeservice.model.Agenda;
import br.univille.homeservice.repository.AgendaRepository;
import br.univille.homeservice.service.AgendaService;

@Service
public class AgendaServiceImpl implements AgendaService{

    @Autowired
    private AgendaRepository agendaRepository;
    
    @Override
    public ArrayList<Agenda> getAgenda(long id, String dataStart, String dataEnd) {
        return agendaRepository.findAllByProfissionalIdAndDataFinalBetween(id, dataStart, dataEnd);
    }

    @Override
    public void save(Agenda agenda) {
        agendaRepository.save(agenda);
    }

    @Override
    public void deletar(Agenda agenda) {
        agendaRepository.delete(agenda);

    }
}
