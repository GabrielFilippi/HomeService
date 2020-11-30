package br.univille.homeservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.univille.homeservice.model.Certificacao;

@Repository
public interface CertificacaoRepository extends JpaRepository<Certificacao, Long>{
    List<Certificacao> findAllByProfissionalId(long idProfissional);
}
