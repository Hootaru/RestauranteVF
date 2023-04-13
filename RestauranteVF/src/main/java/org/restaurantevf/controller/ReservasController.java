package org.restaurantevf.controller;

import java.util.List;

import org.restaurantevf.entity.Reserva;
import org.restaurantevf.entity.Usuario;
import org.restaurantevf.services.ReservasService;
import org.restaurantevf.services.RestauranteService;
import org.restaurantevf.services.UsuariosService;
import org.restaurantevf.util.Utileria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.restaurantevf.entity.Restaurante;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/reservas")
public class ReservasController {
	
	@Autowired
	private Utileria util;
	
	// Inyectamos una instancia desde nuestro ApplicationContext
    @Autowired
	private RestauranteService serviceRestaurantes;
    
    @Autowired
   	private UsuariosService serviceUsuario;
	
    // Inyectamos una instancia desde nuestro ApplicationContext
    @Autowired
	private ReservasService reservasService;    
    
    /**
	 * Metodo que muestra la lista de solicitudes con paginacion
	 * @param model
	 * @param page
	 * @return
	 */
	@GetMapping("/indexPaginate")
	public String mostrarIndexPaginado(Model model, @PageableDefault(sort= {"id"},direction=Sort.Direction.DESC, size=20) Pageable page) {
		Page<Reserva> lista = reservasService.buscarTodas(page);
		model.addAttribute("reservas", lista);
		return "reservas/listReservas";
	}
	
	/**
	 * Metodo que muestra la lista de solicitudes sin paginacion
	 * @param model
	 * @param page
	 * @return
	 */
    @GetMapping("/index")
	public String mostrarIndex(Model model) {
    	List<Reserva> lista = reservasService.buscarTodas();
    	model.addAttribute("reservas", lista);
		return "reservas/listReservas";
	}
    
	/**
	 * Método para renderizar el formulario para aplicar para una Vacante
	 * @param solicitud
	 * @param idVacante
	 * @param model
	 * @return
	 */
	@GetMapping("/create/{idRestaurante}")
	public String crear(Reserva reserva, @PathVariable Integer idRestaurante, Model model) {
		// Traemos los detalles de la Vacante seleccionada para despues mostrarla en la vista
		Restaurante restaurante = serviceRestaurantes.buscarPorId(idRestaurante);
		System.out.println("idRestaurante: " + idRestaurante);
		model.addAttribute("restaurante", restaurante);
		return "reservas/formReserva";
	}
	
	/**
	 * Método que guarda la solicitud enviada por el usuario en la base de datos
	 * @param solicitud
	 * @param result
	 * @param model
	 * @param session
	 * @param multiPart
	 * @param attributes
	 * @return
	 */
	@PostMapping("/save")
	public String guardar(Reserva reserva, BindingResult result, Model model, RedirectAttributes model2,
			@RequestParam("archivoImagen") MultipartFile file) {
		if (result.hasErrors()) {
			for (ObjectError error : result.getAllErrors()) {
				System.out.println("Ocurrio un error: " + error.getDefaultMessage());
			}
			model.addAttribute("reservas", reservasService.buscarTodas());
			return "reservas/formReserva";
		}
		if (reserva.getId() == null) {
			if (!file.isEmpty()) {
				String fileName = util.uploadImage(file);
				if (fileName != null) {
					reserva.setImagen(fileName);
				}
			}
			model2.addFlashAttribute("msg", "La información del Restaurante ha sido agregada correctamente.");
		} else {
			if (!file.isEmpty()) {
				String fileName = util.uploadImage(file);
				if (fileName != null) {
					reserva.setImagen(fileName);
					model2.addFlashAttribute("msg", "La información del Restaurante ha sido modificada correctamente");
				}
			} else {
				Restaurante r = serviceRestaurantes.buscarPorId(reserva.getId());
				reserva.setImagen(r.getImagen());
			}
		}
		reservasService.guardar(reserva);
		return "redirect:/";		
	}
	
	/**
	 * Método para eliminar una solicitud 
	 * @param idSolicitud
	 * @param attributes
	 * @return
	 */
	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") int idReserva, RedirectAttributes attributes) {
		
		// Eliminamos la solicitud.
		reservasService.eliminar(idReserva);
			
		attributes.addFlashAttribute("msg", "La reserva fue eliminada!");
		//return "redirect:/solicitudes/index";
		return "redirect:/reservas/indexPaginate";
	}
				
}
