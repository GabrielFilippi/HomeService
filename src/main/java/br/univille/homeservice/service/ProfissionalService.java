package br.univille.homeservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.univille.homeservice.model.Certificacao;
import br.univille.homeservice.model.Habilidade;
import br.univille.homeservice.model.Perfil;
import br.univille.homeservice.model.Pessoa;
import br.univille.homeservice.model.Profissional;

@Service
public interface ProfissionalService {
    void saveProfissional(Profissional profissional);
    Profissional getProfissional(Long id);
    Profissional getProfissionalByUser(Long idUser);

    void savePessoa(Pessoa pessoa);

    void saveCertificacoes(Certificacao certificacao);
    List<Certificacao> getAllCertificacaoById(long idProfissional);
    void deletarCertificacao(Certificacao certificacao);

    List<Habilidade> getAllHabilidadesById(long idProfissional);
    void saveHabilidade(Habilidade habilidade);
    void deletarHabilidade(Habilidade habilidade);

    void savePerfil(Perfil servicoOferecido);
    void deletarPerfil(Perfil servicoOferecido);
}
