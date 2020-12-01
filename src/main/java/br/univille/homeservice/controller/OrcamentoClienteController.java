package br.univille.homeservice.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.univille.homeservice.model.Cliente;
import br.univille.homeservice.model.ItensOrcamento;
import br.univille.homeservice.model.Orcamento;
import br.univille.homeservice.service.ClienteService;
import br.univille.homeservice.service.OrcamentoService;
import br.univille.homeservice.service.ProfissionalService;
import br.univille.homeservice.service.impl.MyUserDetailsService;

@Controller
@RequestMapping("/minha-conta/cliente/orcamentos")
public class OrcamentoClienteController {

    @Autowired
    private MyUserDetailsService myUserDetailsService;
    
    @Autowired
    private OrcamentoService orcamentoService;

    @Autowired
    private ClienteService clienteService;

    Date dataAtual = new Date();

    // pagina inicial de orcamentos
    @GetMapping("")
    public ModelAndView index() {
        Cliente cliente = clienteService.getClienteByUser(myUserDetailsService.getUserLogged().getId());

        List<Orcamento> orcamentosExistentes = orcamentoService.visualizarTodosByCliente(cliente.getId());
        Orcamento orcamentoNovo = new Orcamento();

        ModelAndView mv = new ModelAndView("minha-conta-cliente/orcamentos");
        mv.addObject("listaOrcamento", orcamentosExistentes);
        mv.addObject("orcamentoNovo", orcamentoNovo);
        return mv;
    }

    //Visualizar o orcamento
    @GetMapping(value="/visualizar/{id}")
    public ModelAndView visualizarOrcamento(@PathVariable("id") Orcamento orcamento){

        List<ItensOrcamento> listaItensOrcamento = orcamentoService.visualizarTodosItens(orcamento.getId());

        ModelAndView mv = new ModelAndView("minha-conta-cliente/visualizar-orcamento");
        mv.addObject("orcamento", orcamento);
        mv.addObject("listaItensOrcamento", listaItensOrcamento);
        return mv;
    }
    
}
