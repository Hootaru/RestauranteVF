package org.restaurantevf.services;

import java.util.List;

import org.restaurantevf.entity.Reserva;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReservasService {
	void guardar(Reserva reserva);
	void eliminar(Integer idReserva);
	List<Reserva> buscarTodas();
	Reserva buscarPorId(Integer idReserva);
	Page<Reserva> buscarTodas(Pageable page);
}
