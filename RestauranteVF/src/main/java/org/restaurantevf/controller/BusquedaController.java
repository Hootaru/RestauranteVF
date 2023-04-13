package org.restaurantevf.controller;

import org.restaurantevf.entity.Restaurante;
import org.restaurantevf.services.PaisService;
import org.restaurantevf.services.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


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
	public String modificarProducto(@RequestParam("id") int idProducto, Model model) {
		Restaurante restaurante = serviceRestaurante.buscarPorId(idProducto);
		model.addAttribute("restaurante", restaurante);
		model.addAttribute("pais", servicePais.buscarTodos());
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