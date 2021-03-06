package br.univille.homeservice.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.univille.homeservice.model.Cliente;
import br.univille.homeservice.model.Endereco;
import br.univille.homeservice.model.MeiosPagamento;
import br.univille.homeservice.model.Pessoa;
import br.univille.homeservice.model.Usuario;
import br.univille.homeservice.repository.ClienteRepository;
import br.univille.homeservice.repository.EnderecoRepository;
import br.univille.homeservice.repository.MeiosPagamentoRepository;
import br.univille.homeservice.repository.PessoaRepository;
import br.univille.homeservice.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private MeiosPagamentoRepository meiosPagamentoRepository;
    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Override
    public Cliente getCliente(Long id) {
        Optional<Cliente> retornoCliente = clienteRepository.findById(id);

        if (retornoCliente.isPresent()) {
            return retornoCliente.get();
        }
        return null;
    }

    @Override
    public Cliente getClienteByUser(Long idUser) {
        return clienteRepository.findByUsuarioId(idUser);
    }

    @Override
    public void saveCliente(Cliente cliente) {
        clienteRepository.save(cliente);
    }

    @Override
    public void deletar(Cliente cliente) {
        clienteRepository.delete(cliente);
    }

    @Override
    public List<Cliente> visualizarTodos() {
        return clienteRepository.findAll();
    }

    @Override
    public void savePessoa(Pessoa pessoa) {
        pessoaRepository.save(pessoa);
    }

    @Override
    public void saveEndereco(Endereco endereco) {
        enderecoRepository.save(endereco);

    }

    @Override
    public void saveMeiosPagamento(MeiosPagamento meiosPagamento) {
        meiosPagamentoRepository.save(meiosPagamento);
    }

    @Override
    public void saveUsuario(Usuario usuario) {
        myUserDetailsService.save(usuario);
    }
    
}
