package org.restaurantevf.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.restaurantevf.entity.Pais;

public interface PaisService {
	void guardar(Pais pais);
	void eliminar(Integer idPais);
	List<Pais> buscarTodos();
	Pais buscarPorId(Integer idPais);
	Page<Pais> buscarTodas(Pageable page);
}
