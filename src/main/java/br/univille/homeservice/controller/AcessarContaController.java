package br.univille.homeservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.univille.homeservice.model.Usuario;
import br.univille.homeservice.service.impl.MyUserDetailsService;

@Controller
@RequestMapping("/minha-conta")
public class AcessarContaController {
    /**
     * 
     * GAMBIARRA!!!!!!!!! ~ NAO DEU TEMPO DE FAZER ALGO BOM.
     * 
     */

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @GetMapping("")
    @PostMapping("")
    public ModelAndView index() {
        Usuario usuarioLogado = myUserDetailsService.getUserLogged();
        if(usuarioLogado!=null){
            if (usuarioLogado.getPermissao().equals("ROLE_CLIENTE")) {
                return new ModelAndView("redirect:/minha-conta/cliente");
            } else if (usuarioLogado.getPermissao().equals("ROLE_PROFISSIONAL")) {
                return new ModelAndView("redirect:/minha-conta/profissional");
            }
            return new ModelAndView("redirect:/logout");
        }
       
        return new ModelAndView("redirect:/login");

    }

}
