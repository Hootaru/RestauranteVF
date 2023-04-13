package org.restaurantevf.controller;

import java.util.List;


import org.restaurantevf.entity.Restaurante;

import org.restaurantevf.services.PaisService;
import org.restaurantevf.services.RestauranteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/busquedas")
public class BusquedaController {
	
	@Autowired
	private PaisService servicePais;
	
	// Inyectamos una instancia desde nuestro ApplicationContext
    @Autowired
	private RestauranteService serviceRestaurante;
  
	@GetMapping("/indexx")
	public String mostrarBusqueda() {
		return "busqueda";
	}

	@GetMapping("/search")
	public String buscar(@ModelAttribute("search") Restaurante restaurante, Model model) {
		
		/**
		 * La busqueda de vacantes desde el formulario debera de ser únicamente en Vacantes con estatus 
		 * "Aprobada". Entonces forzamos ese filtrado.
		 */
		
		restaurante.setEstatus("Aprobado");
		
		// Personalizamos el tipo de busqueda...
		ExampleMatcher matcher  = ExampleMatcher.matching().
			// and descripcion like '%?%'
			withMatcher("descripcion", ExampleMatcher.GenericPropertyMatchers.contains());
		
		Example<Restaurante> example = Example.of(restaurante, matcher);
		List<Restaurante> lista = serviceRestaurante.buscarByExample(example);
		model.addAttribute("restaurantes", lista);
		return "busqueda";
	}
	
	
	/**
	 * Metodo que agrega al modelo datos genéricos para todo el controlador
	 * @param model
	 */
	@ModelAttribute
	public void setGenericos(Model model){
		Restaurante restauranteSearch = new Restaurante();
		restauranteSearch.reset();
		model.addAttribute("search", restauranteSearch);
		model.addAttribute("restaurantes", serviceRestaurante.buscarDestacados());	
		model.addAttribute("paises", servicePais.buscarTodos());	
	}
	
	/**
	 * InitBinder para Strings si los detecta vacios en el Data Binding los settea a NULL
	 * @param binder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
}