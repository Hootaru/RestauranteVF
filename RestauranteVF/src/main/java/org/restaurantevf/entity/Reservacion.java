package org.restaurantevf.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Reservaciones")

public class Reservacion{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Integer id;
	private String nombre_cliente;
	private Integer personas;
	private LocalDate fecha = LocalDate.now();
	private Integer precio;
	private String imagen = "no-image.png";
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre_cliente() {
		return nombre_cliente;
	}
	public void setNombre_cliente(String nombre_cliente) {
		this.nombre_cliente = nombre_cliente;
	}
	public Integer getPersonas() {
		return personas;
	}
	public void setPersonas(Integer personas) {
		this.personas = personas;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public Integer getPrecio() {
		return precio;
	}
	public void setPrecio(Integer precio) {
		this.precio = precio;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	
	@Override
	public String toString() {
		return "Reservacion [id=" + id + ", nombre_cliente=" + nombre_cliente + ", personas=" + personas + ", fecha="
				+ fecha + ", precio=" + precio + ", imagen=" + imagen + "]";
	}
	
}
