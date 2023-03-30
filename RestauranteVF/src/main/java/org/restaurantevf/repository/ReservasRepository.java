package org.restaurantevf.repository;

import org.restaurantevf.entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservasRepository extends JpaRepository<Reserva, Integer> {

}
