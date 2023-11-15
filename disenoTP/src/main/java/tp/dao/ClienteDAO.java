package tp.dao;

import tp.entidad.Cliente;

public class ClienteDAO extends AbstractDAO<Cliente> {
	public ClienteDAO() {
		this.setClase(Cliente.class);
	}
	
}
