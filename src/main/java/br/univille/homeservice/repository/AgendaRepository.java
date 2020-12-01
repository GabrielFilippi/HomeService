package br.univille.homeservice.repository;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.univille.homeservice.model.Agenda;

@Repository
public interface AgendaRepository extends JpaRepository<Agenda, Long>{
    ArrayList<Agenda> findAll();
    ArrayList<Agenda> findAllByProfissionalIdAndDataFinalBetween(long id, Date dataStart, Date dataEnd);
}

