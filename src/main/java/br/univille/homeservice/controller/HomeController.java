package br.univille.homeservice.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

 
@Controller // faz com essa classe seja um controllador. -> aonde injetamos rotas do sistemas
@RequestMapping({"home", "/"}) // definimos a rota. 
public class HomeController {

    @GetMapping("")
    @PostMapping("")
    public ModelAndView index(){

        return new ModelAndView("home/index");
    }
    
    @RequestMapping("/login")
	public ModelAndView login() {
		return new ModelAndView("login/index");
	}
}