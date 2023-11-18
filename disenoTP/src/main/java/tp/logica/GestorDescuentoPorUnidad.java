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
		DescuentoPorUnidad descuento = dao.getAll().get(0);
		long cantUnidades = cliente.getPolizas().parallelStream().
				filter(p -> p.getEstado().equals(EstadoPoliza.VIGENTE)).
				map(p -> p.getVehiculoAsegurado().getChasis()).
				distinct().
				count();
		if(cantUnidades==0) {
			return descuento.getByMinimo(0).getValorActualDescPorUnidad();
		}else {
			return descuento.getByMinimo(1).getValorActualDescPorUnidad();
		}
	}
}
