package br.univille.homeservice.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    
    //pagina inicial do cliente
    @GetMapping("")
    public ModelAndView index(){

        Profissional profissional = profissionalService.getProfissional(idProfissionalTeste);
        return new ModelAndView("minha-conta-profissional/index", "profissional", profissional);
    }

    //carrega a página dos dados do cliente
    @GetMapping("dados")
    public ModelAndView dados(){
        
        Profissional profissional = profissionalService.getProfissional(idProfissionalTeste);
        return new ModelAndView("minha-conta-profissional/dados", "profissional", profissional);
    }

    @GetMapping("agenda")
    public ModelAndView agenda(){
        Profissional profissional = profissionalService.getProfissional(idProfissionalTeste);

        return new ModelAndView("minha-conta-profissional/agenda", "profissional", profissional);
    }

    @GetMapping(value="/carregarAgenda/{idProfissional}/{mes}")
	public @ResponseBody List<Evento> GetEventos(@PathVariable("idProfissional") Profissional profissional, @PathVariable("mes") String mes){

        String anoAtual = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
        

        /*
        
        FAZER AQUI UMA BUSCA, ENTRE O PRIMEIRO DIA DO MES E O ULTIMO
        
        */
        List<Agenda> eventos = agendaService.getAgenda(profissional.getId());
                
        List<Evento> listEvento = new ArrayList<>();
        for(Agenda item:eventos){
            listEvento.add(new Evento(item.getTitulo(),item.getData(),item.getDataFinal()));
        }

        
		return listEvento;
		
	}
}
