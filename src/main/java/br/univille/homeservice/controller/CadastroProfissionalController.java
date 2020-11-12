package br.univille.homeservice.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.univille.homeservice.model.Pessoa;
import br.univille.homeservice.model.Profissional;
import br.univille.homeservice.model.Usuario;
import br.univille.homeservice.service.ProfissionalService;

@Controller
@RequestMapping("/novo-profissional")
public class CadastroProfissionalController {

    @Autowired
    private ProfissionalService service;

    Date dataAtual = new Date();

    //pagina de cadastro do profissional
    @GetMapping("")
    public ModelAndView index(@ModelAttribute Profissional profissional){
        
        profissional.setUsuario(new Usuario());
        profissional.setPessoa(new Pessoa());
        
        return new ModelAndView("registre-se/form-profissional");
    }

    @PostMapping(params="formCadastro")
    public ModelAndView save(Profissional profissional){

        profissional.setDataCriacao(dataAtual);
        profissional.setDataModificacao(dataAtual);
        profissional.getPessoa().setDataCriacao(dataAtual);
        profissional.getPessoa().setDataModificacao(dataAtual);

        service.saveProfissional(profissional);
        return new ModelAndView("redirect:/login");
    }
}
