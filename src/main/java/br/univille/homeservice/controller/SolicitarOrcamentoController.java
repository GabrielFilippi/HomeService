package br.univille.homeservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/solicitar-orcamento")
public class SolicitarOrcamentoController {
     // pagina inicial do cliente
     @GetMapping("")
     public ModelAndView index() {
        return new ModelAndView("solicitar-orcamento/index");
     }
}
