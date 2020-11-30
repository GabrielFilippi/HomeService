package br.univille.homeservice.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.univille.homeservice.model.Agenda;
import br.univille.homeservice.model.Certificacao;
import br.univille.homeservice.model.Habilidade;
import br.univille.homeservice.model.Perfil;
import br.univille.homeservice.model.Profissional;
import br.univille.homeservice.model.Usuario;
import br.univille.homeservice.service.AgendaService;
import br.univille.homeservice.service.ProfissionalService;
import br.univille.homeservice.service.impl.MyUserDetailsService;
import br.univille.homeservice.viewmodel.Evento;

@Controller
@RequestMapping("/minha-conta/profissional")
public class MinhaContaProfissionalController {
    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private ProfissionalService profissionalService;

    @Autowired
    private AgendaService agendaService;

    Date dataAtual = new Date();

    // pagina inicial do cliente
    @GetMapping("")
    public ModelAndView index() {
        
        Profissional profissional = profissionalService.getProfissionalByUser(myUserDetailsService.getUserLogged().getId());

        return new ModelAndView("minha-conta-profissional/index", "profissional", profissional);
    }

    // carrega a página dos dados do cliente
    @GetMapping("dados")
    public ModelAndView dados() {

        Profissional profissional = profissionalService.getProfissionalByUser(myUserDetailsService.getUserLogged().getId());
        return new ModelAndView("minha-conta-profissional/dados", "profissional", profissional);
    }

    @GetMapping("agenda")
    public ModelAndView openAgenda() {
        Profissional profissional = profissionalService.getProfissionalByUser(myUserDetailsService.getUserLogged().getId());
        Agenda agenda = new Agenda();

        ModelAndView mv = new ModelAndView("minha-conta-profissional/agenda");
        mv.addObject("profissional", profissional);
        mv.addObject("agenda", agenda);

        return mv;
    }

     // salva os dados do profissional
     @PostMapping(params = "formMeusDados", path = "dados")
     public ModelAndView salvarDados(Profissional profissional) {
        Profissional antigoProfissional = profissionalService.getProfissional(profissional.getId()); // recupera o cliente no banco
 
        antigoProfissional.setPessoa(profissional.getPessoa());
        antigoProfissional.getPessoa().setDataModificacao(dataAtual);
        profissionalService.savePessoa(antigoProfissional.getPessoa());
        profissionalService.saveProfissional(antigoProfissional);
        
        return new ModelAndView("redirect:/minha-conta/profissional/dados");
     }

    // carrega os eventos
    @GetMapping(value = "/carregarAgenda/{idProfissional}/{mes}")
    public @ResponseBody List<Evento> getEventos(@PathVariable("idProfissional") Profissional profissional, @PathVariable("mes") String mes) {
        
        //monta a data para busca dos eventos do mes selecionado
        String anoAtual = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
        String dataStart = anoAtual + "-" + mes + "-01";
        String dataEnd = anoAtual + "-" + mes + "-31";

        //busca os eventos
        List<Agenda> eventos = agendaService.getAgenda(profissional.getId(), dataStart, dataEnd);

        //monta a lista para retorno
        List<Evento> listEvento = new ArrayList<>();
        for (Agenda item : eventos) {
            listEvento.add(new Evento(item.getId(), item.getTitulo(), item.getDataInicio(), item.getDataFinal(), item.getDescricao()));
        }

        return listEvento;

    }

    // salva a agenda
    @PostMapping(value = "/salvarAgenda", consumes = "application/x-www-form-urlencoded")
    public ResponseEntity salvarDados(Agenda agenda) {
        Profissional profissional = profissionalService.getProfissionalByUser(myUserDetailsService.getUserLogged().getId());
        agenda.setProfissional(profissional);
        agendaService.save(agenda);
        return ResponseEntity.ok().build();
    }

    //deleta a agenda
    @GetMapping(value="/deleteAgenda/{id}")
    public ResponseEntity delete(@PathVariable("id") Agenda agenda){
        agendaService.deletar(agenda);
        return ResponseEntity.ok().build();
    }

    //pagina de certificacoes do profissional
    @GetMapping("certificacoes")
    public ModelAndView openCertificacoes() {
        Profissional profissional = profissionalService.getProfissionalByUser(myUserDetailsService.getUserLogged().getId());
        
        List<Certificacao> certificacao = profissionalService.getAllCertificacaoById(profissional.getId());

        return new ModelAndView("minha-conta-profissional/certificacao", "listaCertificacao", certificacao);
    }

    // abre um formulario para uma nova certificação
    @GetMapping("certificacoes/novo")
    public ModelAndView novoCertificado(@ModelAttribute Certificacao certificacao) {
        return new ModelAndView("minha-conta-profissional/formCertificacao", "certificacao", certificacao);
    }

    // salva o certificado do profissional
    @PostMapping(params = "formCertificado")
    public ModelAndView salvarCertificado(Certificacao certificacao) {
        Profissional profissional = profissionalService.getProfissionalByUser(myUserDetailsService.getUserLogged().getId());
        certificacao.setProfissional(profissional);
        
        profissionalService.saveCertificacoes(certificacao);
        return new ModelAndView("redirect:/minha-conta/profissional/certificacoes");
    }

    //visualizar/editar o certificado
    @GetMapping(value="/certificacoes/visualizar/{id}")
    public ModelAndView editarCertificado(@PathVariable("id") Certificacao certificacao){
        return new ModelAndView("minha-conta-profissional/formCertificacao", "certificacao", certificacao);
    }

    //deleta o certificado
    @GetMapping(value="/certificacoes/deletar/{id}")
    public ModelAndView deletarCertificado(@PathVariable("id") Certificacao certificacao){
        profissionalService.deletarCertificacao(certificacao);
        return new ModelAndView("redirect:/minha-conta/profissional/certificacoes");
    }

    //pagina de Habilidades do profissional
    @GetMapping("habilidades")
    public ModelAndView openHabilidades() {
        Profissional profissional = profissionalService.getProfissionalByUser(myUserDetailsService.getUserLogged().getId());
        
        List<Habilidade> habilidade = profissionalService.getAllHabilidadesById(profissional.getId());

        return new ModelAndView("minha-conta-profissional/habilidade", "listaHabilidade", habilidade);
    }

    // abre um formulario para uma nova certificação
    @GetMapping("habilidades/novo")
    public ModelAndView novaHabilidade(@ModelAttribute Habilidade habilidade) {
        return new ModelAndView("minha-conta-profissional/formHabilidade", "habilidade", habilidade);
    }

    // salva a habilidade do profissional
    @PostMapping(params = "formHabilidade")
    public ModelAndView salvarHabilidade(Habilidade habilidade) {
        Profissional profissional = profissionalService.getProfissionalByUser(myUserDetailsService.getUserLogged().getId());
        habilidade.setProfissional(profissional);
        
        profissionalService.saveHabilidade(habilidade);
        return new ModelAndView("redirect:/minha-conta/profissional/habilidades");
    }

    //visualizar/editar a habilidade
    @GetMapping(value="/habilidades/visualizar/{id}")
    public ModelAndView editarHabilidade(@PathVariable("id") Habilidade habilidade){
        return new ModelAndView("minha-conta-profissional/formHabilidade", "habilidade", habilidade);
    }

    //deleta a habilidade
    @GetMapping(value="/habilidades/deletar/{id}")
    public ModelAndView deletarHabilidade(@PathVariable("id") Habilidade habilidade){
        profissionalService.deletarHabilidade(habilidade);
        return new ModelAndView("redirect:/minha-conta/profissional/habilidades");
    }

    //pagina do perfil do profissional
    @GetMapping("perfil")
    public ModelAndView openPerfil() {
        Profissional profissional = profissionalService.getProfissionalByUser(myUserDetailsService.getUserLogged().getId());
        
        Perfil perfil = profissional.getPerfil();
        if(perfil==null){
            perfil = new Perfil();
        }
        return new ModelAndView("minha-conta-profissional/perfil", "perfil", perfil);
    }

    // salva o perfil do profissional
    @PostMapping(params = "formPerfil")
    public ModelAndView salvarPerfil(Perfil perfil) {
        Profissional profissional = profissionalService.getProfissionalByUser(myUserDetailsService.getUserLogged().getId());
        
        profissionalService.savePerfil(perfil);
        profissional.setPerfil(perfil);
        profissionalService.saveProfissional(profissional);
        return new ModelAndView("redirect:/minha-conta/profissional/perfil");
    }

    //pagina de segurança do profissional
    @GetMapping("seguranca")
    public ModelAndView openSeguranca() {
         Profissional profissional = profissionalService.getProfissionalByUser(myUserDetailsService.getUserLogged().getId());
         Usuario usuario = profissional.getUsuario();
         
         return new ModelAndView("minha-conta-profissional/seguranca", "usuario", usuario);
    }
     
    // salva o perfil do profissional
    @PostMapping(params = "formUsuario")
    public ModelAndView salvarUsuario(Usuario usuario) {
        Profissional profissional = profissionalService.getProfissionalByUser(myUserDetailsService.getUserLogged().getId());
        
        usuario.setLogin(profissional.getUsuario().getLogin());
        usuario.setPermissao(profissional.getUsuario().getPermissao());
        usuario.setUltimoAcesso(profissional.getUsuario().getUltimoAcesso());
        usuario.setUltimoIp(profissional.getUsuario().getUltimoIp());
        
        myUserDetailsService.save(usuario);
        return new ModelAndView("redirect:/minha-conta/profissional/seguranca");
        
    }
}
 