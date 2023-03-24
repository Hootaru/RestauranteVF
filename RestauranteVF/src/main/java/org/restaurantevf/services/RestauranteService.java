package org.restaurantevf.services;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.restaurantevf.entity.Restaurante;
//comentario
public interface RestauranteService {
	void guardar(Restaurante restaurante);
	void eliminar(Integer idRestaurante);
	List<Restaurante> buscarTodos();
	Restaurante buscarPorId(Integer idRestaurante);
	List<Restaurante> buscarDestacados();
	Page<Restaurante> buscarTodos(Pageable page);
	List<Restaurante> buscarByExample(Example<Restaurante> example);
}