package br.univille.homeservice.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.univille.homeservice.model.Cliente;
import br.univille.homeservice.repository.ClienteRepository;
import br.univille.homeservice.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public Cliente getCliente(Long id) {
        Optional<Cliente> retornoCliente = clienteRepository.findById(id);

        if (retornoCliente.isPresent()) {
            return retornoCliente.get();
        }
        return null;
    }

    @Override
    public void saveCliente(Cliente cliente){
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
    
}
