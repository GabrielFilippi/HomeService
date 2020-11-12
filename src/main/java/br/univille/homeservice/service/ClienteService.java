package br.univille.homeservice.service;

import java.util.List;

import org.springframework.stereotype.Service;
import br.univille.homeservice.model.Cliente;

@Service
public interface ClienteService {
    
    Cliente getCliente(Long id);
    void saveCliente(Cliente cliente);
    void deletar(Cliente cliente);
	List<Cliente> visualizarTodos();
}
