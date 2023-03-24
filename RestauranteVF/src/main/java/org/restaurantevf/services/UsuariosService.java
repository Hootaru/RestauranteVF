package org.restaurantevf.services;

import java.util.List;

import org.restaurantevf.entity.Usuario;
//comentario
public interface UsuariosService {
	void guardar(Usuario usuario);
	void eliminar(Integer idUsuario);
	List<Usuario> buscarTodos();
	List<Usuario> buscarRegistrados();
	Usuario buscarPorId(Integer idUsuario);
	Usuario buscarPorUsername(String username);
	int bloquear(int idUsuario);
	int activar(int idUsuario);
}
