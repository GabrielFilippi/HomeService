package br.univille.homeservice.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.univille.homeservice.service.impl.MyUserDetailsService;
 
@Controller // faz com essa classe seja um controllador. -> aonde injetamos rotas do sistemas
@RequestMapping({"home", "/"}) // definimos a rota. 
public class HomeController {

    @Autowired
    private MyUserDetailsService myUserDetailsService; //myUserDetailsService.getUserLogged().getUsuario();  TRAZ AS INFORMAÇÔES DO USUARIO
    
    @GetMapping("")
    @PostMapping("")
    public ModelAndView index(){

        return new ModelAndView("home/index");
    }
    
}