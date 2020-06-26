package br.univille.homeservice.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.univille.homeservice.model.Cliente;
import br.univille.homeservice.service.MinhaContaService;

@Controller
@RequestMapping("/minha-conta")
public class MinhaContaController {

    private MinhaContaService service;
    
    @GetMapping("")
    public ModelAndView index(){
        List<Cliente> listaCliente = service.visualizar();
        return new ModelAndView("minha-conta/index", "listaCliente", listaCliente);
    }
}