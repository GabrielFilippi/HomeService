package br.univille.homeservice.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.univille.homeservice.model.Agenda;
import br.univille.homeservice.model.Profissional;
import br.univille.homeservice.service.AgendaService;
import br.univille.homeservice.service.ProfissionalService;
import br.univille.homeservice.viewmodel.Evento;

@Controller
@RequestMapping("/conta-profissional")
public class MinhaContaProfissionalController {
    @Autowired
    private ProfissionalService profissionalService;

    @Autowired
    private AgendaService agendaService;

    Date dataAtual = new Date();
    long idProfissionalTeste = 1L;

    // pagina inicial do cliente
    @GetMapping("")
    public ModelAndView index() {

        Profissional profissional = profissionalService.getProfissional(idProfissionalTeste);
        return new ModelAndView("minha-conta-profissional/index", "profissional", profissional);
    }

    // carrega a página dos dados do cliente
    @GetMapping("dados")
    public ModelAndView dados() {

        Profissional profissional = profissionalService.getProfissional(idProfissionalTeste);
        return new ModelAndView("minha-conta-profissional/dados", "profissional", profissional);
    }

    @GetMapping("agenda")
    public ModelAndView openAgenda() {
        Profissional profissional = profissionalService.getProfissional(idProfissionalTeste);
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
        
        return new ModelAndView("redirect:/conta-profissional/dados");
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
        agendaService.save(agenda);
        System.out.println(agenda.getTitulo());

        return ResponseEntity.ok().build();
    }

    //deleta a agenda
    @GetMapping(value="/deleteAgenda/{id}")
    public ResponseEntity delete(@PathVariable("id") Agenda agenda){
        agendaService.deletar(agenda);
        return ResponseEntity.ok().build();
    }
}
