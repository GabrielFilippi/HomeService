package br.univille.homeservice.controller;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.univille.homeservice.model.Cliente;
import br.univille.homeservice.service.ClienteService;

@Controller
@RequestMapping("/minha-conta")
public class MinhaContaClienteController {

    @Autowired
    private ClienteService service;

    Date dataAtual = new Date();

    long idClienteTeste = 1L;
    
    //pagina inicial do cliente
    @GetMapping("")
    public ModelAndView index(){
        
        Cliente cliente = service.getCliente(idClienteTeste);
        return new ModelAndView("minha-conta-cliente/index", "cliente", cliente);
    }

    //carrega a página dos dados do cliente
    @GetMapping("dados")
    public ModelAndView dados(){
        
        Cliente cliente = service.getCliente(idClienteTeste);
        return new ModelAndView("minha-conta-cliente/dados", "cliente", cliente);
    }

    //carrega a pagina de endereço do cliente
    @GetMapping("endereco")
    public ModelAndView endereco(){
        
        Cliente cliente = service.getCliente(idClienteTeste);
        return new ModelAndView("minha-conta-cliente/endereco", "cliente", cliente);
    }

    //carrega a pagina de formas de pagamento padrões do cliente
    @GetMapping("forma-pagamento")
    public ModelAndView formaPagamento(){
        
        Cliente cliente = service.getCliente(idClienteTeste);
        return new ModelAndView("minha-conta-cliente/forma-pagamento", "cliente", cliente);
    }

    //salva os dados do cliente
    @PostMapping(params="formMeusDados", path = "dados")
    public ModelAndView salvarDados(Cliente cliente){

        cliente.getPessoa().setDataCriacao(dataAtual);
        cliente.getPessoa().setDataModificacao(dataAtual);

        service.saveCliente(cliente);
        return new ModelAndView("redirect:/minha-conta-cliente/dados");
    }

    //salva o endereço do cliente
    @PostMapping(params="formEndereco", path = "endereco")
    public ModelAndView salvarEndereco(Cliente cliente){
        
        service.saveCliente(cliente);
        return new ModelAndView("redirect:/minha-conta-cliente/endereco");
    }

    //salva a forma de pagamento padrão do cliente
    @PostMapping(params="formPagamento", path = "forma-pagamento")
    public ModelAndView salvarFormaPagamento(Cliente cliente){

        cliente.getMeiosPagamento().setDataCriacao(dataAtual);
        cliente.getMeiosPagamento().setDataModificacao(dataAtual);

        service.saveCliente(cliente);
        return new ModelAndView("redirect:/minha-conta-cliente/forma-pagamento");
    }

    
}
