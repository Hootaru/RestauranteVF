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
	private String archivo; 

	@OneToOne
	@JoinColumn(name = "idCatalogo") // foreignKey en la tabla de solicitudes
	private Catalogo catalogo;

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

	public String getArchivo() {
		return archivo;
	}

	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}

	public Catalogo getCatalogo() {
		return catalogo;
	}

	public void setCatalogo(Catalogo catalogo) {
		this.catalogo = catalogo;
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
		return "Solicitud [id=" + id + ", fecha=" + fecha + ", comentarios=" + comentarios + ", archivo=" + archivo
				+ ", catalogo=" + catalogo + ", usuario=" + usuario + "]";
	}

}
