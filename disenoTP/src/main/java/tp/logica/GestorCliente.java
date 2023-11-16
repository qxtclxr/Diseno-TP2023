package tp.logica;

import tp.dao.*;
import tp.dto.*;
import tp.entidad.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public class GestorCliente {
	
	public Cliente getCliente(ClienteDTO dto) {
		ClienteDAO dao = new ClienteDAO();
		Cliente cliente = dao.getClienteByNroCliente(dto.getNroCliente()).orElseThrow(/*TODO*/);
		return cliente;
	}
	
	public Cliente crearCliente(ClienteDTO dto) {
		Cliente cliente = new Cliente();
		//TODO
		return cliente;
	}
	
	public RangoCantSiniestrosDTO getCantidadDeSiniestrosPorCliente(ClienteDTO dto) {
		GestorRangoCantSiniestros gestorSiniestros = new GestorRangoCantSiniestros();
		Cliente cliente = this.getCliente(dto);
		RangoCantSiniestros siniestros = gestorSiniestros.getRangoCantSiniestros(cliente.getCantSiniestrosCliente());
		return gestorSiniestros.getDTO(siniestros);
	}
	
	public boolean activoPorDosAños(Cliente cliente) {
		TipoCliente tipo = cliente.getTipoCliente();
		if(!(tipo == TipoCliente.ACTIVO || tipo == TipoCliente.PLATA)) {
			return false;
		}
		LocalDate ultimaModificacion = cliente.getFechaModificacionEstado().toLocalDate();
		LocalDate hoy = LocalDate.now();
		Period activoPor = Period.between(ultimaModificacion,hoy);
		int activoPorAños = activoPor.getYears();
		return activoPorAños >= 2;
	}
	
	public void actualizarConsideracion(Cliente cliente) {
		GestorPoliza gestorPoliza = new GestorPoliza();
		List<Poliza> polizas = cliente.getPolizas();
		if(polizas.isEmpty() || !gestorPoliza.existenPolizasVigentes(polizas)) {
			cliente.setTipoCliente(TipoCliente.NORMAL);
			return;
		}
		if(this.poseeSiniestrosEnUltimoAño(cliente) || gestorPoliza.existenCuotasImpagas(polizas) || !this.activoPorDosAños(cliente)) {
			cliente.setTipoCliente(TipoCliente.NORMAL);
		}else {
			cliente.setTipoCliente(TipoCliente.PLATA);
		}
		return;
	}
	
	public boolean poseeSiniestrosEnUltimoAño(Cliente cliente) {
		ClienteDAO dao = new ClienteDAO();
		return dao.poseeSiniestrosEnUltimoAño(cliente);
	}
	
}
