package br.univille.homeservice.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.univille.homeservice.model.Cliente;
import br.univille.homeservice.model.Pessoa;
import br.univille.homeservice.model.Usuario;
import br.univille.homeservice.service.ClienteService;

@Controller
@RequestMapping("/novo-cliente")
public class CadastroClienteController {
    
    @Autowired
    private ClienteService service;
    
    Date dataAtual = new Date();

    @GetMapping("")
    public ModelAndView novo(@ModelAttribute Cliente cliente){
        cliente.setUsuario(new Usuario());
        cliente.setPessoa(new Pessoa());
        
        return new ModelAndView("registre-se/form-cliente");
    }

    @PostMapping(params="formCadastro")
    public ModelAndView save(Cliente cliente){



        cliente.setDataCriacao(dataAtual);
        cliente.setDataModificacao(dataAtual);
        cliente.getPessoa().setDataCriacao(dataAtual);
        cliente.getPessoa().setDataModificacao(dataAtual);
        cliente.getUsuario().setPermissao("ROLE_CLIENTE");
        
        service.saveUsuario(cliente.getUsuario());
        service.savePessoa(cliente.getPessoa());
        service.saveCliente(cliente);
        return new ModelAndView("redirect:/login");
    }
    

    /*

    @GetMapping("/mostrar")
    public ModelAndView index(){
        List<Pessoa> listaPessoas = service.visualizarTodosPessoas();
        return new ModelAndView("login-cliente/index","listapessoas", listaPessoas);
    }

    @PostMapping(params="form")
    public ModelAndView save(Pessoa pessoa){
        service.cadastrarPessoa(pessoa);
        return new ModelAndView("redirect:/login-cliente");
    }

    @GetMapping(value="/alterar/{id}")
    public ModelAndView edit(@PathVariable("id") Pessoa pessoa){
        return new ModelAndView("login-cliente/form","pessoa",pessoa);
    }

    @GetMapping(value="/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Pessoa pessoa){
        service.deletarPessoa(pessoa);
        return new ModelAndView("redirect:/login-cliente");
    }

    */

}