package org.restaurantevf.repository;

import org.restaurantevf.entity.Reservacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservacionRepository extends JpaRepository<Reservacion, Integer> {

}
