package br.univille.homeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.univille.homeservice.model.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    
}
