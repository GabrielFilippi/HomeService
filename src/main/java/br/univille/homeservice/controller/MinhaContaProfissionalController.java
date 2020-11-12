package br.univille.homeservice.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.univille.homeservice.model.Profissional;
import br.univille.homeservice.service.ProfissionalService;

@Controller
@RequestMapping("/conta-profissional")
public class MinhaContaProfissionalController {
    @Autowired
    private ProfissionalService service;

    Date dataAtual = new Date();

    long idProfissionalTeste = 1L;
    
    //pagina inicial do cliente
    @GetMapping("")
    public ModelAndView index(){

        Profissional profissional = service.getProfissional(idProfissionalTeste);
        return new ModelAndView("minha-conta-profissional/index", "profissional", profissional);
    }

    //carrega a página dos dados do cliente
    @GetMapping("dados")
    public ModelAndView dados(){
        
        Profissional profissional = service.getProfissional(idProfissionalTeste);
        return new ModelAndView("minha-conta-profissional/dados", "profissional", profissional);
    }
}
