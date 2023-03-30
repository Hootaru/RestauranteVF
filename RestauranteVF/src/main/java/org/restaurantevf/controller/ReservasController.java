package org.restaurantevf.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.teniskia.entity.Catalogo;
import org.teniskia.entity.Reserva;
import org.teniskia.entity.Usuario;
import org.teniskia.service.CatalogosService;
import org.teniskia.service.ReservasService;
import org.teniskia.service.UsuariosService;
import org.teniskia.util.Utileria;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/reservas")
public class ReservasController {
	
	@Value("${empleosapp.ruta.cv}")
	private String ruta;
	
	// Inyectamos una instancia desde nuestro ApplicationContext
    @Autowired
	private CatalogosService serviceCatalogos;
    
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
	@GetMapping("/create/{idCatalogo}")
	public String crear(Reserva reserva, @PathVariable Integer idCatalogo, Model model) {
		// Traemos los detalles de la Vacante seleccionada para despues mostrarla en la vista
		Catalogo catalogo = serviceCatalogos.buscarPorId(idCatalogo);
		System.out.println("idCatalogo: " + idCatalogo);
		model.addAttribute("catalogo", catalogo);
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
	public String guardar(Reserva reserva, BindingResult result, Model model, HttpSession session,
			@RequestParam("archivoCV") MultipartFile multiPart, RedirectAttributes attributes, Authentication authentication) {	
		
		// Recuperamos el username que inicio sesión
		String username = authentication.getName();
		
		if (result.hasErrors()){
			
			System.out.println("Existieron errores");
			return "reservas/formReserva";
		}	
		
		if (!multiPart.isEmpty()) {
			//String ruta = "/empleos/files-cv/"; // Linux/MAC
			//String ruta = "c:/empleos/files-cv/"; // Windows
			String nombreArchivo = Utileria.guardarArchivo(multiPart, ruta);
			if (nombreArchivo!=null){ // El archivo (CV) si se subio				
				reserva.setArchivo(nombreArchivo); // Asignamos el nombre de la imagen
			}	
		}

		// Buscamos el objeto Usuario en BD	
		Usuario usuario = serviceUsuario.buscarPorUsername(username);			
		reserva.setUsuario(usuario); // Referenciamos la solicitud con el usuario
		
		// Guadamos el objeto solicitud en la bd
		reservasService.guardar(reserva);
		attributes.addFlashAttribute("msg", "Gracias por reservar en TenisKIA!");
			
		System.out.println("Reserva:" + reserva);
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
