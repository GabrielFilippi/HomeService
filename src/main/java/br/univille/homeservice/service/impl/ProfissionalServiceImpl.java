package br.univille.homeservice.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.univille.homeservice.model.Certificacao;
import br.univille.homeservice.model.Habilidade;
import br.univille.homeservice.model.Perfil;
import br.univille.homeservice.model.Pessoa;
import br.univille.homeservice.model.Profissional;
import br.univille.homeservice.repository.CertificacaoRepository;
import br.univille.homeservice.repository.HabilidadeRepository;
import br.univille.homeservice.repository.PerfilRepository;
import br.univille.homeservice.repository.PessoaRepository;
import br.univille.homeservice.repository.ProfissionalRepository;
import br.univille.homeservice.service.ProfissionalService;

@Service
public class ProfissionalServiceImpl implements ProfissionalService {

    @Autowired
    private ProfissionalRepository profissionalRepository;
    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private CertificacaoRepository certificacaoRepository;
    @Autowired
    private HabilidadeRepository habilidadeRepository;
    @Autowired
    private PerfilRepository perfilRepository;

    @Override
    public Profissional getProfissional(Long id) {
        Optional<Profissional> retornoProfissional = profissionalRepository.findById(id);

        if (retornoProfissional.isPresent()) {
            return retornoProfissional.get();
        }
        return null;
    }

    @Override
    public Profissional getProfissionalByUser(Long idUser) {
        return profissionalRepository.findByUsuarioId(idUser);
    }

    @Override
    public void saveProfissional(Profissional profissional) {
        profissionalRepository.save(profissional);
    }

    @Override
    public void savePessoa(Pessoa pessoa) {
        pessoaRepository.save(pessoa);
    }

    @Override
    public void saveCertificacoes(Certificacao certificacao) {
        certificacaoRepository.save(certificacao);
    }

    @Override
    public List<Certificacao> getAllCertificacaoById(long idProfissional) {
        return certificacaoRepository.findAllByProfissionalId(idProfissional);
    }

    @Override
    public void deletarCertificacao(Certificacao certificacao) {
        certificacaoRepository.delete(certificacao);
    }

    @Override
    public List<Habilidade> getAllHabilidadesById(long idProfissional) {
        return habilidadeRepository.findAllByProfissionalId(idProfissional);
    }

    @Override
    public void saveHabilidade(Habilidade habilidade) {
        habilidadeRepository.save(habilidade);
    }

    @Override
    public void deletarHabilidade(Habilidade habilidade) {
        habilidadeRepository.delete(habilidade);
    }

    @Override
    public void savePerfil(Perfil perfil) {
        perfilRepository.save(perfil);
    }

    @Override
    public void deletarPerfil(Perfil perfil) {
        perfilRepository.delete(perfil);
    }
    
}
