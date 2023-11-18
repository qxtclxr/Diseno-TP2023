package tp.logica;

import tp.dao.DescuentoPorUnidadDAO;
import tp.dto.ClienteDTO;
import tp.dto.DescuentoPorUnidadDTO;
import tp.entidad.Cliente;
import tp.entidad.DescuentoPorUnidad;
import tp.entidad.PorcentajeDescPorUnidad;
import tp.exception.ObjetoNoEncontradoException;
import tp.entidad.EstadoPoliza;

public class GestorDescuentoPorUnidad {
	
	public static PorcentajeDescPorUnidad getDescuentoPorUnidadByCliente(ClienteDTO dto)
			throws ObjetoNoEncontradoException {
		Cliente cliente = GestorCliente.getCliente(dto);
		return getDescuentoPorUnidadByCliente(cliente);
	}
	
	public static PorcentajeDescPorUnidad getDescuentoPorUnidadByCliente(Cliente cliente)
			throws ObjetoNoEncontradoException {
		DescuentoPorUnidadDAO dao = new DescuentoPorUnidadDAO();
		long cantUnidades = cliente.getPolizas().parallelStream().
				filter(p -> p.getEstado().equals(EstadoPoliza.VIGENTE)).
				map(p -> p.getVehiculoAsegurado().getChasis()).
				distinct().
				count();
		if(cantUnidades==0) {
			return dao.getByMinimo(0).orElseThrow(() -> new ObjetoNoEncontradoException()).getValorActualDescPorUnidad();
		}else {
			return dao.getByMinimo(1).orElseThrow(() -> new ObjetoNoEncontradoException()).getValorActualDescPorUnidad();
		}
	}
}
