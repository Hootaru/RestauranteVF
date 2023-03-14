package org.restaurantevf.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.LinkedList;
import java.util.List;

import org.restaurantevf.entity.Reservacion;
import org.springframework.stereotype.Service;

@Service
public class ReservacionServiceIMP implements IntServiceReservacion {

	private List<Reservacion> reservaciones;
	
	public ReservacionServiceIMP() {
	DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		try {
			reservaciones = new LinkedList<>();
			Reservacion r1 = new Reservacion();
			r1.setId(1);
			r1.setNombre_cliente("Juan Flores");
			r1.setPersonas(5);
			r1.setFecha(LocalDate.parse("14/03/2023", formato));
			r1.setPrecio(850);
			r1.setImagen("");
			
			reservaciones.add(r1);
		
		}catch( DateTimeParseException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	@Override
	public List<Reservacion> obtenerReservacion() {
		return reservaciones;
	}

	@Override
	public void guardar(Reservacion reservacion) {
		reservaciones.add(reservacion);
	}

	@Override
	public void eliminar(Integer idReservacion) {
			reservaciones.remove(buscarPorId(idReservacion));
	}

	@Override
	public Reservacion buscarPorId(Integer idReservacion) {
		// TODO Auto-generated method stub
		for(Reservacion r : reservaciones) {
			if ( r.getId() == idReservacion) {
				return r;
			}
		}
		return null;
	}

	@Override
	public int totalReservacion() {
		// TODO Auto-generated method stub
		return reservaciones.size();
	}

}

