package br.univille.homeservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.univille.homeservice.model.Cliente;
import br.univille.homeservice.model.ItensOrcamento;
import br.univille.homeservice.model.Orcamento;
import br.univille.homeservice.service.OrcamentoService;
import br.univille.homeservice.service.ProfissionalService;

@Controller
@RequestMapping("/conta-profissional/orcamentos")
public class OrcamentoProfissionalController {

    @Autowired
    private ProfissionalService profissionalService;

    @Autowired
    private OrcamentoService orcamentoService;
    
    long idProfissionalTeste = 1L;

    // pagina inicial de orcamentos
    @GetMapping("")
    public ModelAndView index() {

        List<Orcamento> orcamentosExistentes = orcamentoService.visualizarTodos(idProfissionalTeste);
        Orcamento orcamentoNovo = new Orcamento();

        ModelAndView mv = new ModelAndView("minha-conta-profissional/orcamentos");
        mv.addObject("listaOrcamento", orcamentosExistentes);
        mv.addObject("orcamentoNovo", orcamentoNovo);
        return mv;
    }

    // abre um formulario para um novo orcamento
    @GetMapping("/novo")
    public ModelAndView novoOrcamento(@ModelAttribute Orcamento orcamento) {

        ModelAndView mv = new ModelAndView("minha-conta-profissional/formOcamento");
        mv.addObject("orcamento", orcamento);

        ItensOrcamento itensOrcamento = new ItensOrcamento();
        mv.addObject("itensOrcamento", itensOrcamento);


        return mv;
    }



    
}
