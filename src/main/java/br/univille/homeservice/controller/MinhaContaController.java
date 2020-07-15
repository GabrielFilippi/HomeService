package br.univille.homeservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.univille.homeservice.model.Pessoa;
import br.univille.homeservice.service.MinhaContaService;

@Controller
@RequestMapping("/minha-conta")
public class MinhaContaController {
    
    @Autowired
    private MinhaContaService service;
    
    @GetMapping("")
    public ModelAndView index(){
        List<Pessoa> listaPessoas = service.visualizarTodos();
        return new ModelAndView("minha-conta/index","listapessoas", listaPessoas);
    }

    @GetMapping("/novo")
    public ModelAndView novo(@ModelAttribute Pessoa pessoa){
        return new ModelAndView("minha-conta/form");
    }

    @PostMapping(params="form")
    public ModelAndView save(Pessoa pessoa){
        service.cadastrar(pessoa);
        return new ModelAndView("redirect:/minha-conta");
    }

    @GetMapping(value="/alterar/{id}")
    public ModelAndView edit(@PathVariable("id") Pessoa pessoa){
        return new ModelAndView("minha-conta/form","pessoa",pessoa);
    }

    @GetMapping(value="/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Pessoa pessoa){
        service.deletar(pessoa);
        return new ModelAndView("redirect:/minha-conta");
    }

}