package br.univille.homeservice.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.univille.homeservice.model.Pessoa;
import br.univille.homeservice.model.Profissional;
import br.univille.homeservice.repository.PessoaRepository;
import br.univille.homeservice.repository.ProfissionalRepository;
import br.univille.homeservice.service.ProfissionalService;

@Service
public class ProfissionalServiceImpl implements ProfissionalService {

    @Autowired
    private ProfissionalRepository profissionalRepository;
    @Autowired
    private PessoaRepository pessoaRepository;

    @Override
    public Profissional getProfissional(Long id) {
        Optional<Profissional> retornoProfissional = profissionalRepository.findById(id);

        if (retornoProfissional.isPresent()) {
            return retornoProfissional.get();
        }
        return null;
    }

    @Override
    public void saveProfissional(Profissional profissional) {
        profissionalRepository.save(profissional);
    }

    @Override
    public void savePessoa(Pessoa pessoa) {
        pessoaRepository.save(pessoa);
    }
}
