package br.univille.homeservice.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.univille.homeservice.model.Cliente;
import br.univille.homeservice.model.Favorito;
import br.univille.homeservice.model.Pessoa;
import br.univille.homeservice.model.Usuario;
import br.univille.homeservice.service.ClienteService;
import br.univille.homeservice.service.FavoritoService;
import br.univille.homeservice.service.impl.MyUserDetailsService;

@Controller
@RequestMapping("/minha-conta/cliente")
public class MinhaContaClienteController {

    @Autowired
    private MyUserDetailsService myUserDetailsService;
    
    @Autowired
    private ClienteService service;

    @Autowired
    private FavoritoService serviceFavoritos;

    //data atual
    Date dataAtual = new Date();

    // pagina inicial do cliente
    @GetMapping("")
    public ModelAndView index() {
        Cliente cliente = service.getClienteByUser(myUserDetailsService.getUserLogged().getId());
        
        return new ModelAndView("minha-conta-cliente/index", "cliente", cliente);
    }

    // carrega a página dos dados do cliente
    @GetMapping("dados")
    public ModelAndView dados() {

        Cliente cliente = service.getClienteByUser(myUserDetailsService.getUserLogged().getId());
        if (cliente.getPessoa() == null) {
            cliente.setPessoa(new Pessoa());
        }
        return new ModelAndView("minha-conta-cliente/dados", "cliente", cliente);
    }

    // carrega a pagina de endereço do cliente
    @GetMapping("endereco")
    public ModelAndView endereco() {

        Cliente cliente = service.getClienteByUser(myUserDetailsService.getUserLogged().getId());
        return new ModelAndView("minha-conta-cliente/endereco", "cliente", cliente);
    }

    // carrega a pagina de formas de pagamento padrões do cliente
    @GetMapping("forma-pagamento")
    public ModelAndView formaPagamento() {

        Cliente cliente = service.getClienteByUser(myUserDetailsService.getUserLogged().getId());
        return new ModelAndView("minha-conta-cliente/forma-pagamento", "cliente", cliente);
    }

    // carrega a pagina de profissionais favoritos do cliente
    @GetMapping("favoritos")
    public ModelAndView favoritos() {
        Cliente cliente = service.getClienteByUser(myUserDetailsService.getUserLogged().getId());

        List<Favorito> listaFavoritos = serviceFavoritos.getFavoritos(cliente.getId());
        return new ModelAndView("minha-conta-cliente/favoritos", "listaFavoritos", listaFavoritos);
    }

    // salva os dados do cliente
    @PostMapping(params = "formMeusDados", path = "dados")
    public ModelAndView salvarDados(Cliente cliente) {
        Cliente antigoCliente = service.getCliente(cliente.getId()); // recupera o cliente no banco

        antigoCliente.setPessoa(cliente.getPessoa());
        antigoCliente.getPessoa().setDataModificacao(dataAtual);
        service.savePessoa(antigoCliente.getPessoa());
        service.saveCliente(antigoCliente);
        return new ModelAndView("redirect:/minha-conta/cliente/dados");
    }

    // salva o endereço do cliente
    @PostMapping(params = "formEndereco", path = "endereco")
    public ModelAndView salvarEndereco(Cliente cliente) {
        Cliente antigoCliente = service.getCliente(cliente.getId()); // recupera o cliente no banco

        antigoCliente.setEndereco(cliente.getEndereco());
        service.saveEndereco(antigoCliente.getEndereco());
        service.saveCliente(antigoCliente);
        return new ModelAndView("redirect:/minha-conta/cliente/endereco");
    }

    // salva a forma de pagamento padrão do cliente
    @PostMapping(params = "formPagamento", path = "forma-pagamento")
    public ModelAndView salvarFormaPagamento(Cliente cliente) {
        Cliente antigoCliente = service.getCliente(cliente.getId()); // recupera o cliente no banco

        if (cliente.getMeiosPagamento().getId() == 0) {
            cliente.getMeiosPagamento().setDataCriacao(dataAtual);
        }
        cliente.getMeiosPagamento().setDataModificacao(dataAtual);

        antigoCliente.setMeiosPagamento(cliente.getMeiosPagamento()); // pega o cliente antigo e salva o meio de
                                                                      // pagamento nele
        service.saveMeiosPagamento(antigoCliente.getMeiosPagamento()); // salva o meio de pagamento
        service.saveCliente(antigoCliente); // salva o cliente
        return new ModelAndView("redirect:/minha-conta/cliente/forma-pagamento");
    }

    //remove um profissional da lista de favoritos
    @GetMapping(value="/deleteFavorito/{id}")
    public ResponseEntity delete(@PathVariable("id") Favorito favorito){
        serviceFavoritos.deletarFavorito(favorito);
        return ResponseEntity.ok().build();
    }
    
    //pagina de segurança do cliente
    @GetMapping("seguranca")
    public ModelAndView openSeguranca() {
         Cliente cliente = service.getClienteByUser(myUserDetailsService.getUserLogged().getId());
         Usuario usuario = cliente.getUsuario();
         
         return new ModelAndView("minha-conta-cliente/seguranca", "usuario", usuario);
    }
     
    // salva o perfil do cliente
    @PostMapping(params = "formUsuario")
    public ModelAndView salvarUsuario(Usuario usuario) {
        Cliente cliente = service.getClienteByUser(myUserDetailsService.getUserLogged().getId());
        
        usuario.setLogin(cliente.getUsuario().getLogin());
        usuario.setPermissao(cliente.getUsuario().getPermissao());
        usuario.setUltimoAcesso(cliente.getUsuario().getUltimoAcesso());
        usuario.setUltimoIp(cliente.getUsuario().getUltimoIp());
        
        myUserDetailsService.save(usuario);
        return new ModelAndView("redirect:/minha-conta/cliente/seguranca");
        
    }
}
