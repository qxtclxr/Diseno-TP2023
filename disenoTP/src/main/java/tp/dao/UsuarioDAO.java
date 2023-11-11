package tp.dao;

import tp.entidad.Usuario;

public class UsuarioDAO extends AbstractDAO<Usuario> {
	public UsuarioDAO() {
		this.setClase(Usuario.class);
	}
}
