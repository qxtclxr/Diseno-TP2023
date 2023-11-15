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
	
	//TODO: Ver si lo que termina retornando es Poliza o List<Poliza>
	public boolean existePatente(String patente) {
		if(patente==null) {
			return false;
		}
		PolizaDAO dao = new PolizaDAO();
		Optional<Poliza> poliza = dao.getByPatente(patente);
		return poliza.isPresent();
	}
	
	//TODO
	public boolean existeMotor(String motor) {
		PolizaDAO dao = new PolizaDAO();
		Optional<Poliza> poliza = dao.getByMotor(motor);
		return poliza.isPresent();
	}
	
	//TODO
	public boolean existeChasis(String chasis) {
		PolizaDAO dao = new PolizaDAO();
		Optional<Poliza> poliza = dao.getByChasis(chasis);
		return poliza.isPresent();
	}
	
	public boolean valoresUnicosParaVehiculo(PolizaDTO dto) {
		boolean chasis = this.existeChasis(dto.getVehiculo().getChasis());
		boolean motor = this.existeMotor(dto.getVehiculo().getMotor());
		boolean patente = this.existePatente(dto.getVehiculo().getPatente());
		return !(chasis || motor || patente);
	}
	
	public boolean datosObligatoriosPresentes(PolizaDTO dto) {
		boolean datosPresentes = true;
		GestorLocalizacion gestorLocal = new GestorLocalizacion();
		GestorVehiculo gestorVehiculo = new GestorVehiculo();
		datosPresentes &= gestorLocal.datosObligatoriosPresentes(dto.getLocalidad());
		datosPresentes &= gestorVehiculo.datosObligatoriosPresentes(dto.getVehiculo());
		datosPresentes &= dto.getKmRealizados() != null;
		datosPresentes &= dto.getCantidadSiniestros() != null;
		return datosPresentes;
	}
	
	public boolean coberturaElegidaEsValida(PolizaDTO dto) {
		GestorVehiculo gestorVehic = new GestorVehiculo();
		boolean vehiculoMayorADiezAños = gestorVehic.esMayorADiezAños(dto.getVehiculo());
		//TODO: Esta linea de abajo esta bastante floja de papeles
		//Ver si se puede hacer reconocer la cobertura por la id, si es que la id se puede conocer.
		boolean coberturaEsResponsabilidadCivil = dto.getCobertura().getText().toUpperCase().equals("RESPONSABILIDAD CIVIL");
		//La expresion cuyo resultado retorna es equivalente logicamente a
		//"vehiculoMayorADiezAños IMPLIES coberturaEsResponsabilidadCivil
		return !vehiculoMayorADiezAños || coberturaEsResponsabilidadCivil;
	}
	
	public Poliza crearPoliza(PolizaDTO dto) {
		//TODO: calcularDescuentos()
		
		//Ver de meter todas estas valdiaciones en su propio metodo
		if(!this.datosObligatoriosPresentes(dto)) {
			//TODO: throw DatosObligatoriosAusentesException
		}
		
		if(!this.valoresUnicosParaVehiculo(dto)) {
			//TODO: throw ValoresParaVehiculoExistentesException
		}
		
		if(!this.coberturaElegidaEsValida(dto)) {
			//TODO throw AutoMuyViejoParaCoberturaElegidaException
		}
		
		Poliza poliza = new Poliza();
		
		poliza.setSumaAsegurada(dto.getSumaAsegurada());
		poliza.setFechaInicio(dto.getFechaInicio());
		poliza.setEstadoPoliza(EstadoPoliza.GENERADA);
		poliza.setTipoPoliza(dto.getTipoPoliza());
		poliza.setFechaEmision(LocalDateTime.now());
		//setPremio(): La poliza o las cuotas tienen premio? Ver todo este tema de los
		//descuentos all together. RESPUESTA: En la poliza.
		
		GestorHijoDeclarado gestorHijos = new GestorHijoDeclarado();
		//altaHijosDeclarados(): Ver si realmente es un alta o es crear los objetos y el alta se hace
		//toda junta con la poliza para ser transaccional. Mismo problema para todas las generaciones de
		//entidades que vengan de DTOs en el resto del metodo. RESPUESTA: Se crea el objeto y hace la alta
		//junto con Poliza
		List<HijoDeclarado> hijosDeclarados = gestorHijos.crearHijosDeclarados(dto.getHijosDeclarados());
		poliza.setHijosDeclarados(hijosDeclarados);
		
		GestorCuota gestorCuota = new GestorCuota();
		List<Cuota> cuotas = gestorCuota.crearCuotas(dto.getCuotas());
		poliza.setCuotas(cuotas);
		
		GestorMedidaDeSeguridad gestorMedida = new GestorMedidaDeSeguridad();
		List<RespuestaSeguridad> respuestas = gestorMedida.crearRespuestasSeguridad(dto.getRespuestasSeguridad());
		poliza.setRespuestasSeguridad(respuestas);
		
		GestorVehiculo gestorVehiculo = new GestorVehiculo();
		Vehiculo vehiculo = gestorVehiculo.crearVehiculo(dto.getVehiculo());
		poliza.setVehiculo(vehiculo);
		
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
		//TODO: Ver si es transaccional o si puede ser aparte. Si es transaccional, hacerlo antes de
		//saveInstance y despues de recuperar cliente, y que el metodo solo modifique el estado del objeto.
		//RESPUESTA: No pregunte pero lo hice para que modifique el objeto y se haga con 
		gestorCliente.actualizarConsideracion(cliente);
		poliza.setCliente(cliente);
		
		GestorLocalizacion gestorLocal = new GestorLocalizacion();
		Localidad localidad = gestorLocal.getLocalidad(dto.getLocalidad());
		poliza.setLocalidad(localidad);
		
		poliza.setNroPoliza(this.generarNroPoliza(dto));
		
		return poliza;
	}
	
	public Poliza altaPoliza(PolizaDTO dto) {
		
		Poliza poliza = this.crearPoliza(dto);
		PolizaDAO dao = new PolizaDAO();
		//Ver si es ese metodo (saveInstance) o hay un metodo particular a implementar en PolizaDAO.
		dao.saveInstance(poliza);
		
		return poliza;
	}
}
