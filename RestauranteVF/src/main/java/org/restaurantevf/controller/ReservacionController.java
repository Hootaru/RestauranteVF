package org.restaurantevf.controller;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.restaurantevf.entity.Reservacion;
import org.restaurantevf.services.IntServiceReservacion;
import org.restaurantevf.util.Utileria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/reservaciones")
public class ReservacionController {

	@Autowired
	private IntServiceReservacion serviceReservacion;

	@GetMapping("/buscar")
	public String buscarReservacion(@RequestParam("id") int idReservacion, Model model) {
		Reservacion reservacion = serviceReservacion.buscarPorId(idReservacion);
		model.addAttribute("reservacion", reservacion);
		return "reservaciones/formReservacion";
	}

	@PostMapping("/guardar")
	public String guardar(Reservacion reservacion, @RequestParam("archivoImagen") MultipartFile multiPart) {
		if (!multiPart.isEmpty()) {
			String ruta = "c:/empleos/img-vacantes/"; 
			String nombreImagen = Utileria.guardarArchivo(multiPart, ruta);
			if (nombreImagen != null) {
				reservacion.setImagen(nombreImagen);
			}
		}
		serviceReservacion.guardar(reservacion);
		return "redirect:/reservaciones/index";

	}

	@GetMapping("/nueva")
	public String nuevaReservacion(Reservacion reservacion) {
		return "reservaciones/formReservacion";
	}

	@GetMapping("/eliminar")
	public String eliminarReservacion(@RequestParam("id") int idReservacion, RedirectAttributes model) {
		serviceReservacion.eliminar(idReservacion);
		model.addFlashAttribute("msg", "Reservacion Eliminado");
		return "redirect:/reservaciones/index";

	}

	@GetMapping("/index")
	public String mostrarIndex(Model model) {
		List<Reservacion> lista = serviceReservacion.obtenerReservacion();
		model.addAttribute("reservacion", lista);
		model.addAttribute("total", serviceReservacion.totalReservacion());
		for (Reservacion r : lista) {
			System.out.println(r);
		}
		return "reservaciones/listaReservacion";
	}

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) throws IllegalArgumentException {
				setValue(LocalDate.parse(text, DateTimeFormatter.ofPattern("dd-MM-yyyy")));
			}

			@Override
			public String getAsText() throws IllegalArgumentException {
				return DateTimeFormatter.ofPattern("dd-MM-yyyy").format((LocalDate) getValue());
			}

		});
	}
}
