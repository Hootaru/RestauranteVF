package org.restaurantevf.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.teniskia.entity.Reserva;
import org.teniskia.repository.ReservasRepository;
import org.teniskia.service.ReservasService;

@Service
public class ReservasServiceJpa implements ReservasService {

	@Autowired
	private ReservasRepository reservasRepo;
	
	@Override
	public void guardar(Reserva reserva) {
		reservasRepo.save(reserva);
	}

	@Override
	public void eliminar(Integer idReserva) {
		reservasRepo.deleteById(idReserva);
	}

	@Override
	public List<Reserva> buscarTodas() {
		return reservasRepo.findAll();
	}

	@Override
	public Reserva buscarPorId(Integer idReserva) {
		Optional<Reserva> optional = reservasRepo.findById(idReserva);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public Page<Reserva> buscarTodas(Pageable page) {
		return reservasRepo.findAll(page);
	}

}
