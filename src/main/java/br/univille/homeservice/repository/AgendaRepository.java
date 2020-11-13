package br.univille.homeservice.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.univille.homeservice.model.Agenda;

@Repository
public interface AgendaRepository extends JpaRepository<Agenda, Long>{
    ArrayList<Agenda> findAll();
    ArrayList<Agenda> findAllByProfissionalId(long id);
}
