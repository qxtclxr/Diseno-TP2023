package tp.logica;

import tp.dao.ClienteDAO;
import tp.dto.ClienteDTO;
import tp.dto.RangoCantSiniestrosDTO;
import tp.entidad.Cliente;
import tp.entidad.RangoCantSiniestros;

public class GestorCliente {
	
	public Cliente getCliente(ClienteDTO dto) {
		ClienteDAO dao = new ClienteDAO();
		Cliente cliente = dao.getByNroCliente(dto.getNroCliente()).orElseThrow(/*TODO*/);
		return cliente;
	}
	
	public RangoCantSiniestrosDTO getCantidadDeSiniestrosPorCliente(ClienteDTO dto) {
		GestorRangoCantSiniestros gestorSiniestros = new GestorRangoCantSiniestros();
		Cliente cliente = this.getCliente(dto);
		RangoCantSiniestros siniestros = dao.cantidadDeSiniestrosPorCliente(cliente);
		return gestorSiniestros.getDTO(siniestros);
	}
	
}
