package tp.logica;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import tp.dao.CoberturaDAO;
import tp.dao.PolizaDAO;
import tp.dto.CoberturaDTO;
import tp.dto.NoModificableDTO;
import tp.dto.PolizaDTO;
import tp.entidad.*;

public class GestorPoliza {
	
	//TODO
	public boolean existePatente(String patente) {
//		PolizaDAO dao = new PolizaDAO();
//		Optional<Poliza> poliza = dao.getByPatente(patente);
//		return vehiculo.isPresent();
		return false;
	}
	
	//TODO
	public boolean existeMotor(String motor) {
//		PolizaDAO dao = new PolizaDAO();
//		Optional<Poliza> poliza = dao.getByMotor(motor);
//		return poliza.isPresent();
		return false;
	}
	
	//TODO
	public boolean existeChasis(String chasis) {
//		PolizaDAO dao = new PolizaDAO();
//		Optional<Poliza> poliza = dao.getByChasis(chasis);
//		return poliza.isPresent();
		return false;
	}
	
	public boolean valoresUnicosParaVehiculo(PolizaDTO dto) {
		boolean chasis = this.existeChasis(dto.getVehiculo().getChasis());
		boolean motor = this.existeChasis(dto.getVehiculo().getMotor());
		boolean patente = this.existeChasis(dto.getVehiculo().getPatente());
		return !(chasis || motor || patente);
	}
	
	public Poliza altaPoliza(PolizaDTO dto) {
		//TODO: calcularDescuentos()
		boolean valoresVehiculoValidos = valoresUnicosParaVehiculo(dto);
		if(!valoresVehiculoValidos) {
			//TODO: throw valoresParaVehiculoExistentesException
		}
		Poliza poliza = new Poliza();
		poliza.setSumaAsegurada(dto.getSumaAsegurada());
		poliza.setFechaInicio(dto.getFechaInicio());
		poliza.setEstadoPoliza(EstadoPoliza.GENERADA);
		poliza.setTipoPoliza(dto.getTipoPoliza());
		poliza.setFechaEmision(LocalDateTime.now());
		//setPremio(): La poliza o las cuotas tienen premio? Ver todo este tema de los descuentos all together.
		
		GestorHijoDeclarado gestorHijos = new GestorHijoDeclarado();
		//altaHijosDeclarados(): Ver si realmente es un alta o es crear los objetos y el alta se hace
		//toda junta con la poliza para ser transaccional. Mismo problema para todas las generaciones de
		//entidades que vengan de DTOs en el resto del metodo
		List<HijoDeclarado> hijosDeclarados = gestorHijos.altaHijosDeclarados(dto.getHijosDeclarados());
		poliza.setHijosDeclarados(hijosDeclarados);
		
		GestorCuota gestorCuota = new GestorCuota();
		List<GestorCuota> cuotas = gestorCuota.altaCuotas(dto.getCuotas());
		poliza.setCuotas(cuotas);
		
		//Dependiendo si el DTO contiene "[Derechos|Descuentos]DTO", hay que cambiar por recuperacion de entidad.
		poliza.setDerechosDeEmision(dto.getDerechosDeEmision());
		poliza.setDescuentosPorUnidad(dto.getDescuentosPorUnidad());
		
		GestorRangoKMRealizados gestorKm = new GestorRangoKMRealizados();
		RangoKMRealizados km = gestorKm.getRangoKMRealizados(dto.getRangoKMRealizados());
		poliza.setRangoKMRealizados(km);
		
		GestorRangoCantSiniestros gestorSiniestros = new GestorRangoCantSiniestros();
		RangoCantSiniestros siniestros = gestorSiniestros.getRangoCantSiniestros(dto.getCantidadSiniestros());
		poliza.setRangoCantSiniestros(siniestros);
		
		GestorCobertura gestorCobertura = new GestorCobertura();
		Cobertura cobertura = gestorCobertura.getCobertura(dto.getCobertura());
		poliza.setCobertura(cobertura);
		
		GestorCliente gestorCliente = new GestorCliente();
		Cliente cliente = gestorCliente.getCliente(dto.getCliente());
		poliza.setCliente(cliente);
		
		GestorLocalizacion gestorLocal = new GestorLocalizacion();
		Localidad localidad = gestorLocal.getLocalidad(dto.getLocalidad());
		poliza.setLocalidad(localidad);
		
		poliza.setNroPoliza(this.generarNroPoliza(dto));
		
		PolizaDAO dao = new PolizaDAO();
		//Ver si es ese metodo (saveInstance) o hay un metodo particular a implementar en PolizaDAO.
		dao.saveInstance(poliza);
		
		//TODO: Ver si es transaccional o si puede ser aparte. Si es transaccional, hacerlo antes de
		//saveInstance y despues de recuperar cliente, y que el metodo solo modifique el estado del objeto.
		gestorCliente.actualizarConsideracion(cliente);
	}
}
