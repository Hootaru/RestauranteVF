package org.restaurantevf.controller;

import java.util.Date;
import java.util.List;

import  org.restaurantevf.entity.Perfil;
import org.restaurantevf.entity.Restaurante;
import org.restaurantevf.entity.Usuario;
import org.restaurantevf.services.PaisService;
import org.restaurantevf.services.RestauranteService;
import org.restaurantevf.services.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/busquedas")
public class BusquedaController {
	
	@Autowired
	private PaisService servicePais;
	
	// Inyectamos una instancia desde nuestro ApplicationContext
    @Autowired
	private RestauranteService serviceRestaurante;
    
    @Autowired
   	private UsuariosService serviceUsuarios;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
  
	@GetMapping("/index")
	public String mostrarBusqueda() {
		return "busqueda";
	}
	
	
	/**
	 * Método para realizar búsquedas desde el formulario de búsqueda del HomePage
	 * @param vacante
	 * @param model
	 * @return
	 */
	@GetMapping("/search")
	public String buscar(@ModelAttribute("search") Restaurante  restaurante, Model model) {
		
		/**
		 * La busqueda de vacantes desde el formulario debera de ser únicamente en Vacantes con estatus 
		 * "Aprobada". Entonces forzamos ese filtrado.
		 */
		restaurante.setEstatus("Aprobada");
		
		// Personalizamos el tipo de busqueda...
		ExampleMatcher matcher  = ExampleMatcher.matching().
			// and descripcion like '%?%'
			withMatcher("descripcion", ExampleMatcher.GenericPropertyMatchers.contains());
		
		Example<Restaurante> example = Example.of(restaurante, matcher);
		List<Restaurante> lista = serviceRestaurante.buscarByExample(example);
		model.addAttribute("vacantes", lista);
		return "home";
	}
	
	/**
	 * Método personalizado para cerrar la sesión del usuario
	 * @param request
	 * @return
	 */
	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
		logoutHandler.logout(request, null, null);
		return "redirect:/";
	}
	
	/**
     * Utileria para encriptar texto con el algorito BCrypt
     * @param texto
     * @return
     */
    @GetMapping("/bcrypt/{texto}")
    @ResponseBody
   	public String encriptar(@PathVariable("texto") String texto) {    	
   		return texto + " Encriptado en Bcrypt: " + passwordEncoder.encode(texto);
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

