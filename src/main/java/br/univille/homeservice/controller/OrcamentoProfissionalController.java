package br.univille.homeservice.controller;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
import br.univille.homeservice.service.impl.MyUserDetailsService;

@Controller
@RequestMapping("/minha-conta/profissional/orcamentos")
public class OrcamentoProfissionalController {

    @Autowired
    private MyUserDetailsService myUserDetailsService;
    
    @Autowired
    private OrcamentoService orcamentoService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ProfissionalService profissionalService;

    Date dataAtual = new Date();

    // pagina inicial de orcamentos
    @GetMapping("")
    public ModelAndView index() {
        Profissional profissional = profissionalService.getProfissionalByUser(myUserDetailsService.getUserLogged().getId());

        List<Orcamento> orcamentosExistentes = orcamentoService.visualizarTodos(profissional.getId());
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
        Profissional profissional = profissionalService.getProfissionalByUser(myUserDetailsService.getUserLogged().getId());

        orcamento.setProfissional(profissional);
        orcamento.setDataCriacao(dataAtual);

        //ORCAMENTO
        orcamentoService.saveOrcamento(orcamento);

        //ITENS DE ORCAMENTO
        String[] intemNome = request.getParameterValues("itemNome[]");
        String[] itemQuantidade = request.getParameterValues("itemQuantidade[]");
        String[] itemValorUnitario = request.getParameterValues("itemValorUnitario[]");

        BigDecimal totalOrcamento = new BigDecimal(0);

        for(int i=0; i<intemNome.length; i++ ){
            ItensOrcamento itensOrcamento = new ItensOrcamento();

            //nome
            itensOrcamento.setNome(intemNome[i]);

            //quantidade
            BigDecimal quantidade = new BigDecimal(itemQuantidade[i].replace(".", "").replace(",", "."));
            itensOrcamento.setQuantidade(quantidade);

            //valor unitario
            BigDecimal valorUnitario = new BigDecimal(itemValorUnitario[i].replace(".", "").replace(",", "."));
            itensOrcamento.setValorUnitario(valorUnitario);

            //total
            BigDecimal total = valorUnitario.multiply(quantidade);
            itensOrcamento.setValorTotal(total);
            totalOrcamento = totalOrcamento.add(total);

            //atribui o orcamento ao item
            itensOrcamento.setOrcamento(orcamento);

            itensOrcamento.setDataCriacao(dataAtual);
            //salva os itens de orcamento
            orcamentoService.saveItensOrcamento(itensOrcamento);
        }
        orcamento.setTotalOrcamento(totalOrcamento);
        orcamentoService.saveOrcamento(orcamento);

        return new ModelAndView("redirect:/minha-conta/profissional/orcamentos");
    }

    //Visualizar o orcamento
    @GetMapping(value="/visualizar/{id}")
    public ModelAndView visualizarOrcamento(@PathVariable("id") Orcamento orcamento){

        List<ItensOrcamento> listaItensOrcamento = orcamentoService.visualizarTodosItens(orcamento.getId());

        ModelAndView mv = new ModelAndView("minha-conta-profissional/visualizar-orcamento");
        mv.addObject("orcamento", orcamento);
        mv.addObject("listaItensOrcamento", listaItensOrcamento);
        return mv;
    }
    
}
