package org.restaurantevf.service.db;

import java.util.List;
import java.util.Optional;

import org.restaurantevf.entity.Restaurante;
import org.restaurantevf.repository.RestauranteRepository;
import org.restaurantevf.services.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
/**
 * 	Hay 2 clases que implementan la interfaz IVacantesService:
 *		- VacantesServiceImpl -> Guardamos las Vacantes en una LinkedList
 *		- VacantesServiceJpa  -> Guardamos las Vacantes en bd
 *	Spring no puede decidir cual Inyectar. Nosotros tenemos que decidir.
 *	Por lo tanto, con la anotación @Primary le decimos que esta es la implementación por Defecto.
 *
 */
@Primary
public class RestauranteServiceJpa implements RestauranteService{

	@Autowired
	private RestauranteRepository restaurantesRepo;

	@Override
	public void guardar(Restaurante restaurante) {
		restaurantesRepo.save(restaurante);
	}

	@Override
	public void eliminar(Integer idRestaurante) {
		restaurantesRepo.deleteById(idRestaurante);
	}

	@Override
	public List<Restaurante> buscarTodos() {		
		return restaurantesRepo.findAll();
	}

	@Override
	public Restaurante buscarPorId(Integer idRestaurante) {
		Optional<Restaurante> optional = restaurantesRepo.findById(idRestaurante);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public List<Restaurante> buscarDestacados() {
		return restaurantesRepo.findByDestacadoAndEstatusOrderByIdDesc(1, "Aprobado");
	}

	@Override
	public Page<Restaurante> buscarTodos(Pageable page) {		
		return restaurantesRepo.findAll(page);
	}

	@Override
	public List<Restaurante> buscarByExample(Example<Restaurante> example) {
		return restaurantesRepo.findAll(example);
	}
		
}
