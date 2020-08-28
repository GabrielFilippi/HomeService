package br.univille.homeservice.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.univille.homeservice.model.Pessoa;
import br.univille.homeservice.service.MinhaContaService;

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
}