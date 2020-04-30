package com.eventosapp.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eventosapp.models.Convidado;
import com.eventosapp.models.Evento;
import com.eventosapp.repository.ConvidadoRepository;
import com.eventosapp.repository.EventoRepository;

@Controller
public class EventoController {
	
	@Autowired
	private EventoRepository er;
	
	@Autowired
	private ConvidadoRepository cr;
		
	@RequestMapping(value="/cadastro", method=RequestMethod.GET)
	public String form() {
		return "evento/formEvento";
	}
	
	@RequestMapping(value="/cadastro", method=RequestMethod.POST)
	public String form(@Valid Evento evento, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os Campos em Branco!");
				return "redirect:/cadastro";
		}
		er.save(evento);
			attributes.addFlashAttribute("mensagem", "Evento Cadastrado!");
				return "redirect:/cadastro";
	}
		
	@RequestMapping(value="/eventos", method=RequestMethod.GET)
	public ModelAndView listaEventos() {
		ModelAndView mv = new ModelAndView("evento/listEvento");
		Iterable<Evento> eventos = er.findAll();
		mv.addObject("eventos", eventos);
		return mv;
	}
	
	@RequestMapping(value="/{codigo}", method=RequestMethod.GET)
	public ModelAndView detalhesEvento(@PathVariable("codigo") long codigo) {
		Evento evento = er.findByCodigo(codigo);
		ModelAndView mv = new ModelAndView("evento/detalhesEvento");
		mv.addObject("eventos", evento);
		Iterable<Convidado> convidados = cr.findAll();
		mv.addObject("convidados", convidados);
			return mv;
	}
	
	@RequestMapping(value="/{deletarEvento}")
	public String deletarEvento (long codigo) {
		Evento evento = er.findByCodigo(codigo);
		er.delete(evento);
		return "redirect:/eventos";
	}
	
	@RequestMapping(value="/{codigo}", method=RequestMethod.POST)
	public String detalhesEventoPost(@PathVariable("codigo") long codigo, @Valid Convidado convidado, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os Campos em Branco!");
				return "redirect:/{codigo}";
		}
		Evento eventos = er.findByCodigo(codigo);
		eventos.getConvidados().add(convidado);
		er.save(eventos);
		attributes.addFlashAttribute("mensagem", "Convidado adicionado!");
		return "redirect:/{codigo}";
	}
		
	@RequestMapping(value="/apagarConvidado")
	public String apagarConvidado(long codigo, long codigoConvidado) {

		Evento eventos = er.findByCodigo(codigo);
		Convidado convidados = cr.findByCodigo(codigoConvidado);
		eventos.getConvidados().remove(convidados);
		er.save(eventos);
//		attributes.addFlashAttribute("mensagem", "Convidado removido!");
		return "redirect:/" + codigo;
	}
}
