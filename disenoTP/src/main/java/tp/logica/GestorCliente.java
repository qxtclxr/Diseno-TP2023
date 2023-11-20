package tp.logica;

import tp.dao.*;
import tp.dto.*;
import tp.entidad.*;
import tp.exception.NoExisteRangoCantSiniestos;
import tp.exception.ObjetoNoEncontradoException;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public class GestorCliente {
	
	public static Cliente getByNroCliente(String nro) throws ObjetoNoEncontradoException {
		ClienteDAO dao = new ClienteDAO();
		return dao.getClienteByNroCliente(nro).orElseThrow(() -> new ObjetoNoEncontradoException());
	}
	
	public static Cliente getCliente(ClienteDTO dto) throws ObjetoNoEncontradoException {
		ClienteDAO dao = new ClienteDAO();
		Cliente cliente = dao.getClienteByNroCliente(dto.getNroCliente()).orElseThrow(() -> new ObjetoNoEncontradoException());
		return cliente;
	}
	
	public static ClienteDTO getClienteDTO(Cliente cliente) {
		ClienteDTO dto = new ClienteDTO();
		dto.setApellido(cliente.getApellido());
		dto.setDomicilio(GestorLocalizacion.getDomicilioDTO(cliente.getDomicilio()));
		dto.setNombre(cliente.getNombres());
		dto.setNroCliente(cliente.getNroCliente());
		dto.setNroDocumento(cliente.getNroDocumento());
		dto.setTipoDocumento(cliente.getTipoDocumento());
		return dto;
	}
	
	public static Cliente crearCliente(ClienteDTO dto) {
		Cliente cliente = new Cliente();
		//TODO
		return cliente;
	}
	
	public static RangoCantSiniestrosDTO getCantidadDeSiniestrosPorCliente(ClienteDTO dto)
			throws ObjetoNoEncontradoException,
			NoExisteRangoCantSiniestos {
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
