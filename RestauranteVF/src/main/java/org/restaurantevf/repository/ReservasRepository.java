package org.restaurantevf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.teniskia.entity.Reserva;

public interface ReservasRepository extends JpaRepository<Reserva, Integer> {

}
