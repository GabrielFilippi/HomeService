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

import br.univille.homeservice.model.Pessoa;
import br.univille.homeservice.service.MinhaContaService;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/v1/pessoas")
public class PessoaControllerAPI {

    @Autowired
    private MinhaContaService service;

    @GetMapping
    public ResponseEntity<List<Pessoa>> visualizarTodos(){
        List<Pessoa> lista = service.visualizarTodos();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> getById(@PathVariable("id") Pessoa pessoa){
        return new ResponseEntity<>(pessoa, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Pessoa> save (@RequestBody Pessoa pessoa){
        service.cadastrar(pessoa);
        return new ResponseEntity<>(pessoa, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> update(@PathVariable("id") Pessoa pessoaAntigo, @RequestBody Pessoa pessoaAtualizado){
        pessoaAntigo.setNome(pessoaAtualizado.getNome());
        pessoaAntigo.setEmail(pessoaAtualizado.getEmail());
        pessoaAntigo.setCelular(pessoaAtualizado.getCelular());
        pessoaAntigo.setCpf(pessoaAtualizado.getCpf());
        pessoaAntigo.setDataCriacao(pessoaAtualizado.getDataCriacao());
        pessoaAntigo.setFoto(pessoaAtualizado.getFoto());
        pessoaAntigo.setSexo(pessoaAtualizado.getSexo());
        pessoaAntigo.setTelefone(pessoaAtualizado.getTelefone());

        service.cadastrar(pessoaAntigo);

        return new ResponseEntity<>(pessoaAntigo, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Pessoa> delete(@PathVariable("id") Pessoa pessoa) {
        service.deletar(pessoa);
        
        return new ResponseEntity<>(pessoa, HttpStatus.OK);
    }
    


}