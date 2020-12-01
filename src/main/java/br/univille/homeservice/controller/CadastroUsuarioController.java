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
import br.univille.homeservice.model.Profissional;
import br.univille.homeservice.model.Usuario;
import br.univille.homeservice.service.ClienteService;
import br.univille.homeservice.service.ProfissionalService;

@Controller
@RequestMapping("/novo-usuario")
public class CadastroUsuarioController {
    @Autowired
    private ProfissionalService serviceProfissional;

    @Autowired
    private ClienteService serviceCliente;
    
    Date dataAtual = new Date();

    //index
    @GetMapping("")
    public ModelAndView index(){
        
        return new ModelAndView("registre-se/index");
    }

    //formulario para novo profissional
    @GetMapping("novo-profissional")
    public ModelAndView novoProfissional(@ModelAttribute Profissional profissional){
        profissional.setUsuario(new Usuario());
        profissional.setPessoa(new Pessoa());
        
        return new ModelAndView("registre-se/form-profissional", "profissional", profissional);
    }

    //formulario para novo cliente
    @GetMapping("novo-cliente")
    public ModelAndView novoCliente(@ModelAttribute Cliente cliente){
        cliente.setUsuario(new Usuario());
        cliente.setPessoa(new Pessoa());

        return new ModelAndView("registre-se/form-cliente", "cliente", cliente);
    }


    @PostMapping(params="formCadastroCliente")
    public ModelAndView save(Cliente cliente){

        cliente.setDataCriacao(dataAtual);
        cliente.setDataModificacao(dataAtual);
        cliente.getPessoa().setDataCriacao(dataAtual);
        cliente.getPessoa().setDataModificacao(dataAtual);
        cliente.getUsuario().setPermissao("ROLE_CLIENTE");
        
        serviceCliente.saveUsuario(cliente.getUsuario());
        serviceCliente.savePessoa(cliente.getPessoa());
        serviceCliente.saveCliente(cliente);
        return new ModelAndView("redirect:/login");
    }

    @PostMapping(params="formCadastroProfissional")
    public ModelAndView save(Profissional profissional){

        profissional.setDataCriacao(dataAtual);
        profissional.setDataModificacao(dataAtual);
        profissional.getPessoa().setDataCriacao(dataAtual);
        profissional.getPessoa().setDataModificacao(dataAtual);
        profissional.getUsuario().setPermissao("ROLE_PROFISSIONAL");

        serviceProfissional.saveUsuario(profissional.getUsuario());
        serviceProfissional.savePessoa(profissional.getPessoa());
        serviceProfissional.saveProfissional(profissional);
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