package tp.logica;

import tp.dao.DescuentoPorUnidadDAO;
import tp.dto.ClienteDTO;
import tp.dto.DescuentoPorUnidadDTO;
import tp.entidad.Cliente;
import tp.entidad.DescuentoPorUnidad;
import tp.entidad.PorcentajeDescPorUnidad;
import tp.entidad.EstadoPoliza;

public class GestorDescuentoPorUnidad {
	
	public static PorcentajeDescPorUnidad getDescuentoPorUnidadByCliente(ClienteDTO dto) {
		Cliente cliente = GestorCliente.getCliente(dto);
		return getDescuentoPorUnidadByCliente(cliente);
	}
	
	public static PorcentajeDescPorUnidad getDescuentoPorUnidadByCliente(Cliente cliente) {
		DescuentoPorUnidadDAO dao = new DescuentoPorUnidadDAO();
		long cantUnidades = cliente.getPolizas().parallelStream().
				filter(p -> p.getEstado().equals(EstadoPoliza.VIGENTE)).
				map(p -> p.getVehiculoAsegurado().getChasis()).
				distinct().
				count();
		if(cantUnidades==0) {
			return dao.getByMinimo(0).orElseThrow(/*TODO*/).getValorActualDescPorUnidad();
		}else {
			return dao.getByMinimo(1).orElseThrow(/*TODO*/).getValorActualDescPorUnidad();
		}
	}
}
