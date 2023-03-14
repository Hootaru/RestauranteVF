package org.restaurantevf.services;

import java.util.List;

import org.restaurantevf.entity.Reservacion;

public interface IntServiceReservacion {
	
	public List<n> obtenerReservacion();
	public void guardar(Reservacion reservacion);
	public void eliminar(Integer idReservacion);
	public Reservacion buscarPorId(Integer idReservacion);
	public int totalReservacion();	
}
