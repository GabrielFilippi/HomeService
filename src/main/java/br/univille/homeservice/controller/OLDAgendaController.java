package br.univille.homeservice.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.univille.homeservice.model.Agenda;

@Controller
@RequestMapping("OLDconta-profissional/agenda")
public class OLDAgendaController {
    
    @GetMapping("")
    public ModelAndView index(){

        return new ModelAndView("minha-conta-profissional/agenda");
    }

    @RequestMapping(value = "/getEventos.json", method = RequestMethod.GET)	
	public @ResponseBody List<Agenda> GetEventos(){

		List<Agenda> eventos = new ArrayList();
				
		String mesAtual = String.valueOf(Calendar.getInstance().get(Calendar.MONTH)+ 1);
		
		if(mesAtual.length() <2){
			mesAtual = "0" + mesAtual;
        }
		
		String anoAtual = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
				
		/*ADICIONANDO OS EVENTOS*/
		eventos.add(new Agenda("Estudar Linux",     anoAtual+"-"+mesAtual+"-02T12:00:00",anoAtual+"-"+mesAtual+"-02T13:30:00", null));
		eventos.add(new Agenda("Estudar Java", 	   anoAtual+"-"+mesAtual+"-02T14:00:00",anoAtual+"-"+mesAtual+"-02T14:30:00", null));
		eventos.add(new Agenda("Estudar C#", 	   anoAtual+"-"+mesAtual+"-02T15:00:00",anoAtual+"-"+mesAtual+"-02T15:30:00", null));
		eventos.add(new Agenda("Estudar SOA Suite", anoAtual+"-"+mesAtual+"-02T16:00:00",anoAtual+"-"+mesAtual+"-02T17:30:00", null));
		eventos.add(new Agenda("Estudar Jquery",    anoAtual+"-"+mesAtual+"-02T19:00:00",anoAtual+"-"+mesAtual+"-02T20:30:00", null));		
		eventos.add(new Agenda("Correr",     	   anoAtual+"-"+mesAtual+"-03T13:00:00",anoAtual+"-"+mesAtual+"-03T13:30:00", null));
		eventos.add(new Agenda("Reunião",	       anoAtual+"-"+mesAtual+"-05T12:00:00",anoAtual+"-"+mesAtual+"-05T13:30:00", null));		
		eventos.add(new Agenda("Dois dias de evento", anoAtual+"-"+mesAtual+"-07T12:00:00",anoAtual+"-"+mesAtual+"-08T12:00:00", null));
		
		eventos.add(new Agenda("Publicar Artigo",   anoAtual+"-"+mesAtual+"-10T12:00:00",anoAtual+"-"+mesAtual+"-10T13:30:00", null));
		eventos.add(new Agenda("Reunião",	       anoAtual+"-"+mesAtual+"-10T15:00:00",anoAtual+"-"+mesAtual+"-10T18:30:00", null));		
		
		eventos.add(new Agenda("Festa",  		   anoAtual+"-"+mesAtual+"-13T12:00:00",anoAtual+"-"+mesAtual+"-13T13:30:00", null));
		eventos.add(new Agenda("Festa 2",	       anoAtual+"-"+mesAtual+"-13T15:00:00",anoAtual+"-"+mesAtual+"-13T18:30:00", null));		
		eventos.add(new Agenda("Curso de Inglês",   anoAtual+"-"+mesAtual+"-15",null, null));				
		eventos.add(new Agenda("Blog Cícero",       anoAtual+"-"+mesAtual+"-23",null, "http://www.ciceroednilson.com.br"));
				
		return eventos;
		
	}


}
