package org.restaurantevf.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.teniskia.entity.Reserva;

public interface ReservasService {
	void guardar(Reserva reserva);
	void eliminar(Integer idReserva);
	List<Reserva> buscarTodas();
	Reserva buscarPorId(Integer idReserva);
	Page<Reserva> buscarTodas(Pageable page);
}
