package tp.logica;

import tp.dao.*;
import tp.dto.*;
import tp.entidad.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public class GestorCliente {
	
	public static Cliente getCliente(ClienteDTO dto) {
		ClienteDAO dao = new ClienteDAO();
		Cliente cliente = dao.getClienteByNroCliente(dto.getNroCliente()).orElseThrow(/*TODO*/);
		return cliente;
	}
	
	public static Cliente crearCliente(ClienteDTO dto) {
		Cliente cliente = new Cliente();
		//TODO
		return cliente;
	}
	
	public static RangoCantSiniestrosDTO getCantidadDeSiniestrosPorCliente(ClienteDTO dto) {
		Cliente cliente = getCliente(dto);
		RangoCantSiniestros siniestros = GestorRangoCantSiniestros.getRangoCantSiniestros(cliente.getCantSiniestrosCliente());
		return GestorRangoCantSiniestros.getDTO(siniestros);
	}
	
	public static boolean activoPorDosAnios(Cliente cliente) {
		TipoCliente tipo = cliente.getTipoCliente();
		if(!(tipo == TipoCliente.ACTIVO || tipo == TipoCliente.PLATA)) {
			return false;
		}
		LocalDate ultimaModificacion = cliente.getFechaModificacionEstado().toLocalDate();
		LocalDate hoy = LocalDate.now();
		Period activoPor = Period.between(ultimaModificacion,hoy);
		int activoPorAnios = activoPor.getYears();
		return activoPorAnios >= 2;
	}
	
	public static void actualizarConsideracion(Cliente cliente) {
		List<Poliza> polizas = cliente.getPolizas();
		if(polizas.isEmpty() || !GestorPoliza.existenPolizasVigentes(polizas)) {
			cliente.setTipoCliente(TipoCliente.NORMAL);
			return;
		}
		if(tieneSiniestrosUltimoAnio(cliente) || GestorPoliza.existenCuotasImpagas(polizas) || !activoPorDosAnios(cliente)) {
			cliente.setTipoCliente(TipoCliente.NORMAL);
		}else {
			cliente.setTipoCliente(TipoCliente.PLATA);
		}
		return;
	}
	
	public static boolean tieneSiniestrosUltimoAnio(Cliente cliente) {
		return FacadeSistemaSiniestros.tieneSiniestrosUltimoAnio(cliente);
	}
	
}
