package br.univille.homeservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.univille.homeservice.model.Usuario;
import br.univille.homeservice.service.impl.MyUserDetailsService;

@Controller
@RequestMapping("/minha-conta/")
public class AcessarContaController {
    /**
     * 
     * GAMBIARRA!!!!!!!!! ~ NAO DEU TEMPO DE FAZER ALGO BOM.
     * 
     */

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    Usuario usuarioLogado = myUserDetailsService.getUserLogged();

    @GetMapping("")
    public ModelAndView index() {
        if (usuarioLogado.getPermissao() == "ROLE_CLIENTE") {
            return new ModelAndView("minha-conta-cliente/index");
        } else if (usuarioLogado.getPermissao() == "ROLE_PROFISSIONAL") {
            return new ModelAndView("minha-conta-profissional/index");
        }
        return new ModelAndView("redirect:/login");

    }

}
