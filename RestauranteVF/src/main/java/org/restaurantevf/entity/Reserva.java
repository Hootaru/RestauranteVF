/**
 * Esta clase representa una entidad (un registro) en la tabla de Solicitudes de la base de datos
 */
package org.restaurantevf.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Reservas")
public class Reserva {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment MySQL
	private Integer id;
	private LocalDate fecha; 
	private String comentarios;
	private String imagen; 

	@OneToOne
	@JoinColumn(name = "idRestaurante") // foreignKey en la tabla de solicitudes
	private Restaurante restaurante;

	@OneToOne
	@JoinColumn(name = "idUsuario") // foreignKey en la tabla de usuarios
	private Usuario usuario;

	public Reserva() {
		this.fecha = LocalDate.now();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public Restaurante getRestaurante() {
		return restaurante;
	}

	public void setCatalogo(Restaurante restaurante) {
		this.restaurante = restaurante;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	@Override
	public String toString() {
		return "Solicitud [id=" + id + ", fecha=" + fecha + ", comentarios=" + comentarios + ", imagen=" + imagen
				+ ", restaurante=" + restaurante + ", usuario=" + usuario + "]";
	}

}
