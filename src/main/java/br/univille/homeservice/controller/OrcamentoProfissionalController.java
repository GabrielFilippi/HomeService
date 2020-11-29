package br.univille.homeservice.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.univille.homeservice.model.Cliente;
import br.univille.homeservice.model.ItensOrcamento;
import br.univille.homeservice.model.Orcamento;
import br.univille.homeservice.model.Profissional;
import br.univille.homeservice.service.ClienteService;
import br.univille.homeservice.service.OrcamentoService;
import br.univille.homeservice.service.ProfissionalService;

@Controller
@RequestMapping("/conta-profissional/orcamentos")
public class OrcamentoProfissionalController {

    @Autowired
    private OrcamentoService orcamentoService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ProfissionalService profissionalService;
    
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
       
        ModelAndView mv = new ModelAndView("minha-conta-profissional/formOrcamento");
        mv.addObject("orcamento", orcamento);

        ItensOrcamento itensOrcamento = new ItensOrcamento();
        mv.addObject("itensOrcamento", itensOrcamento);

        List<Cliente> clientes = clienteService.visualizarTodos();
        mv.addObject("listaCliente", clientes);
        

        return mv;
    }

    // salva o orçamento
    @PostMapping(params = "formOrcamento")
    public ModelAndView salvarOrcamento(Orcamento orcamento, HttpServletRequest request) {
         
        Profissional profissional = profissionalService.getProfissional(idProfissionalTeste);
        orcamento.setProfissional(profissional);
        
        //ORCAMENTO
        orcamentoService.saveOrcamento(orcamento);

        //ITENS DE ORCAMENTO

        String[] intemNome = request.getParameterValues("itemNome[]");
        String[] intemQuantidade = request.getParameterValues("itemQuantidade[]");
        String[] itemValorUnitario = request.getParameterValues("itemQuantidade[]");

        for(int i=0; i<intemNome.length; i++ ){
            ItensOrcamento itensOrcamento = new ItensOrcamento();

            //nome
            itensOrcamento.setNome(intemNome[i]);

            //quantidade
            double quantidade = Double.parseDouble(intemQuantidade[i].replace(".", "").replace(",", "."));
            itensOrcamento.setQuantidade(quantidade);

            //valor unitario
            double valorUnitario = Double.parseDouble(itemValorUnitario[i].replace(".", "").replace(",", "."));
            itensOrcamento.setValorUnitario(valorUnitario);

            //total
            itensOrcamento.setValorTotal(valorUnitario * quantidade);

            //atribui o orcamento ao item
            itensOrcamento.setOrcamento(orcamento);
            //salva os itens de orcamento
            orcamentoService.saveItensOrcamento(itensOrcamento);
        }
    
        return new ModelAndView("redirect:/conta-profissional/orcamentos");
    }

    
}
