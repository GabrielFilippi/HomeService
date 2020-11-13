package br.univille.homeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.univille.homeservice.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    
}
