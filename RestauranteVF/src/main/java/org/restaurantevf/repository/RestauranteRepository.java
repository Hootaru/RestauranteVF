package org.restaurantevf.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.restaurantevf.entity.Restaurante;

public interface RestauranteRepository extends JpaRepository<Restaurante, Integer> {
	
	List<Restaurante> findByEstatus(String estatus);
	
	List<Restaurante> findByDestacadoAndEstatusOrderByIdDesc(int destacado, String estatus);
	
	List<Restaurante> findByPrecioBetweenOrderByPrecioDesc(double s1, double s2);
	
	List<Restaurante> findByEstatusIn(String[] estatus);

}
