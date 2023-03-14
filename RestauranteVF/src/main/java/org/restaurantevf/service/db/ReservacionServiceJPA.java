package org.restaurantevf.service.db;

import java.util.List;
import java.util.Optional;

import org.restaurantevf.entity.Reservacion;
import org.restaurantevf.repository.ReservacionRepository;
import org.restaurantevf.services.IntServiceReservacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class ReservacionServiceJPA implements IntServiceReservacion {

	@Autowired
	private ReservacionRepository repoReservacion;
	
	@Override
	public List<Reservacion> obtenerReservacion() {
		return repoReservacion.findAll();
	}

	@Override
	public void guardar(Reservacion reservacion) {
		repoReservacion.save(reservacion);
	}

	@Override
	public Reservacion buscarPorId(Integer idReservacion) {
		// TODO Auto-generated method stub
		Optional<Reservacion> optional = repoReservacion.findById(idReservacion);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public void eliminar(Integer idReservacion) {
		repoReservacion.deleteById(idReservacion);
	}

	@Override
	public int totalReservacion() {
		// TODO Auto-generated method stub
		return (int) repoReservacion.count();
	}

}
