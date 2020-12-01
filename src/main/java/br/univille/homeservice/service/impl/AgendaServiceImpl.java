package br.univille.homeservice.service.impl;

import java.util.ArrayList;
import java.util.Date;

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
    public ArrayList<Agenda> getAgenda(long id, Date dataStart, Date dataEnd) {
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
