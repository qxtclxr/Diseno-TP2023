package tp.logica;

import tp.dto.DescuentoPorUnidadDTO;

public class GestorDescuentoPorUnidad {
	
	public DescuentoPorUnidad getDescuentoPorUnidadByCliente(Cliente cliente) {
		DescuentoPorUnidadDAO dao = new DescuentoPorUnidadDAO();
		return dao.getDescuentoByCliente(cliente);
	}
}
