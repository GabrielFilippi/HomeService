package br.univille.homeservice.service;

import java.util.List;

import org.springframework.stereotype.Service;
import br.univille.homeservice.model.Cliente;
import br.univille.homeservice.model.Endereco;
import br.univille.homeservice.model.MeiosPagamento;
import br.univille.homeservice.model.Pessoa;

@Service
public interface ClienteService {
    
    Cliente getCliente(Long id);
    void saveCliente(Cliente cliente);
    void deletar(Cliente cliente);
    List<Cliente> visualizarTodos();
    void savePessoa(Pessoa pessoa);
    void saveEndereco(Endereco endereco);
    void saveMeiosPagamento(MeiosPagamento meiosPagamento);
}
