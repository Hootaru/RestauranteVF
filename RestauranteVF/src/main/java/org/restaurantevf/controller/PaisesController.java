package org.restaurantevf.controller;

import java.util.List;

import org.restaurantevf.services.PaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import org.restaurantevf.entity.Pais;

@Controller
@RequestMapping("/paises")
public class PaisesController {
	
	// Inyectamos una instancia desde nuestro ApplicationContext   
    @Autowired
   	private PaisService servicePaises;
	  
    /**
	 * Metodo que muestra la lista de categorias sin paginacion
	 * @param model
	 * @param page
	 * @return
	 */
    @GetMapping("/index")
	public String mostrarIndex(Model model) {
    	List<Pais> lista = servicePaises.buscarTodos();
    	model.addAttribute("paises", lista);
		return "paises/listPaises";
	}
    
    /**
	 * Metodo que muestra la lista de categorias con paginacion
	 * @param model
	 * @param page
	 * @return
	 */
	@GetMapping("/indexPaginate")
	public String mostrarIndexPaginado(Model model, Pageable page) {
		Page<Pais> lista = servicePaises.buscarTodos(page);
		model.addAttribute("paises", lista);
		return "paises/listPaises";
	}
    
	/**
	 * Método para renderizar el formulario para crear una nueva Categoría
	 * @param categoria
	 * @return
	 */
	@GetMapping("/create")
	public String crear(Pais pais) {		
		return "paises/formPais";
	}
	
	/**
	 * Método para guardar una Categoría en la base de datos
	 * @param categoria
	 * @param result
	 * @param model
	 * @param attributes
	 * @return
	 */
	@PostMapping("/save")
	public String guardar(Pais pais, BindingResult result, Model model, RedirectAttributes attributes) {	
		
		if (result.hasErrors()){
			
			System.out.println("Existieron errores");
			return "paises/formPais";
		}	
				
		// Guadamos el objeto categoria en la bd
		servicePaises.guardar(pais);
		attributes.addFlashAttribute("msg", "Los datos del país fueron guardados!");
			
		//return "redirect:/categorias/index";
		return "redirect:/paises/indexPaginate";		
	}
	
	/**
	 * Método para renderizar el formulario para editar una Categoría
	 * @param idCategoria
	 * @param model
	 * @return
	 */
	@GetMapping("/edit/{id}")
	public String editar(@PathVariable("id") int idPais, Model model) {		
		Pais pais = servicePaises.buscarPorId(idPais);			
		model.addAttribute("pais", pais);
		return "paises/formPais";
	}
	
	/**
	 * Método para eliminar una Categoría de la base de datos
	 * @param idCategoria
	 * @param attributes
	 * @return
	 */
	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") int idPais, RedirectAttributes attributes) {
		
		// Eliminamos la categoria.
		servicePaises.eliminar(idPais);
			
		attributes.addFlashAttribute("msg", "El país fue eliminado!.");
		//return "redirect:/categorias/index";
		return "redirect:/paises/indexPaginate";
	}
		
}
