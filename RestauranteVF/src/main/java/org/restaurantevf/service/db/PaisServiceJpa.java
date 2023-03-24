package org.restaurantevf.service.db;

import java.util.List;
import java.util.Optional;

import org.restaurantevf.services.PaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import org.restaurantevf.entity.Pais;
import org.restaurantevf.repository.PaisRepository;

@Service
@Primary
public class PaisServiceJpa implements PaisService{

	@Autowired
	private PaisRepository paisRepo;
	
	@Override
	public void guardar(Pais pais) {
		paisRepo.save(pais);
	}

	@Override
	public void eliminar(Integer idPais) {
		paisRepo.deleteById(idPais);
	}

	@Override
	public List<Pais> buscarTodos() {		
		return paisRepo.findAll();
	}

	@Override
	public Pais buscarPorId(Integer idPais) {
		Optional<Pais> optional = paisRepo.findById(idPais);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public Page<Pais> buscarTodos(Pageable page) {
		return paisRepo.findAll(page);	
	}

}

