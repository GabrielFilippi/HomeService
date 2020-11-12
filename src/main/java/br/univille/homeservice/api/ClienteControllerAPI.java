package br.univille.homeservice.api;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.univille.homeservice.model.Cliente;
import br.univille.homeservice.model.Pessoa;
import br.univille.homeservice.service.ClienteService;


@RestController
@RequestMapping("/api/v1/cliente")
public class ClienteControllerAPI {

    @Autowired
    private ClienteService service;

    @GetMapping
    public ResponseEntity<List<Cliente>> visualizarTodos(){
        List<Cliente> lista = service.visualizarTodos();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getById(@PathVariable("id") Cliente cliente){
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Cliente> save (@RequestBody Cliente cliente){
        service.saveCliente(cliente);
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> update(@PathVariable("id") Cliente clienteAntigo, @RequestBody Cliente clienteAtualizado){

        Pessoa pessoa = new Pessoa();
        
        Pessoa pessoaAtualizada = clienteAtualizado.getPessoa();

        pessoa.setNome(pessoaAtualizada.getNome());
        pessoa.setEmail(pessoaAtualizada.getEmail());
        pessoa.setCelular(pessoaAtualizada.getCelular());
        pessoa.setCpf(pessoaAtualizada.getCpf());
        pessoa.setDataCriacao(pessoaAtualizada.getDataCriacao());
        pessoa.setFoto(pessoaAtualizada.getFoto());
        pessoa.setSexo(pessoaAtualizada.getSexo());
        pessoa.setTelefone(pessoaAtualizada.getTelefone());

        clienteAntigo.setPessoa(pessoa);
        service.saveCliente(clienteAntigo);

        return new ResponseEntity<>(clienteAntigo, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Cliente> delete(@PathVariable("id") Cliente cliente) {
        service.deletar(cliente);
        
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }
    


}