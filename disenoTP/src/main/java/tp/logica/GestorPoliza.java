package tp.logica;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import tp.dao.CoberturaDAO;
import tp.dao.PolizaDAO;
import tp.dto.*;
import tp.entidad.*;

public class GestorPoliza {
	
	public static Poliza crearPoliza(PolizaDTO dto) {
		Poliza poliza = new Poliza();
		
		poliza.setSumaAsegurada(dto.getSumaAsegurada());
		poliza.setFechaInicio(dto.getFechaInicio());
		poliza.setEstado(EstadoPoliza.GENERADA);
		poliza.setTipoPoliza(dto.getTipoPoliza());
		poliza.setFechaEmision(LocalDateTime.now());
		poliza.setPremio(dto.getPremio());
		GestorVehiculo gestorVehiculo = new GestorVehiculo();
		Vehiculo vehiculo = gestorVehiculo.crearVehiculo(dto.getVehiculo());
		poliza.setVehiculoAsegurado(vehiculo);
		
		GestorHijoDeclarado gestorHijos = new GestorHijoDeclarado();
		List<HijoDeclarado> hijosDeclarados = gestorHijos.crearHijosDeclarados(dto.getHijosDeclarados());
		poliza.setHijosDeclarados(hijosDeclarados);
		
		GestorCuota gestorCuota = new GestorCuota();
		List<Cuota> cuotas = gestorCuota.crearCuotas(dto.getCuotas());
		poliza.setCuotasAsociadas(cuotas);
		
		GestorCliente gestorCliente = new GestorCliente();
		Cliente cliente = gestorCliente.getCliente(dto.getCliente());
		gestorCliente.actualizarConsideracion(cliente);
		poliza.setCliente(cliente);
		
		/*TODO ESTO DE ABAJO CAMBIA SEGURO*/
		
		//Dependiendo si el DTO contiene "[Derechos|Descuentos|Premio]DTO", hay que cambiar por recuperacion de entidad.
		poliza.setDerechosDeEmision(dto.getDerechosDeEmision());
		poliza.setDescuentoPorU(dto.getDescuentosPorUnidad());
		
		GestorMedidaDeSeguridad gestorMedida = new GestorMedidaDeSeguridad();
		List<RespuestaSeguridad> respuestas = gestorMedida.crearRespuestasSeguridad(dto.getRespuestasSeguridad());
		poliza.setRespuestasSeguridad(respuestas);
		
		GestorRangoKMRealizados gestorKm = new GestorRangoKMRealizados();
		RangoKMRealizados km = gestorKm.getRangoKMRealizados(dto.getKmRealizados());
		poliza.setRangoKMRealizados(km);
		
		GestorRangoCantSiniestros gestorSiniestros = new GestorRangoCantSiniestros();
		RangoCantSiniestros siniestros = gestorSiniestros.getRangoCantSiniestros(dto.getCantidadSiniestros());
		poliza.setRangoCantSiniestros(siniestros);
		
		GestorCobertura gestorCobertura = new GestorCobertura();
		Cobertura cobertura = gestorCobertura.getCobertura(dto.getCobertura());
		poliza.setCobertura(cobertura);
		
		GestorLocalizacion gestorLocal = new GestorLocalizacion();
		Localidad localidad = gestorLocal.getLocalidad(dto.getLocalidad());
		poliza.setDomicilioDeRiesgo(localidad);
		
		poliza.setNroPoliza(generarNroPoliza(dto));
		
		return poliza;
	}
	
	public static Poliza altaPoliza(PolizaDTO dto) {
		
		//TODO: calcularDescuentos()
		validarDTO(dto);
		//Ver si estas dos cosas van aca o en crearPoliza()
		
		Poliza poliza = crearPoliza(dto);
		PolizaDAO dao = new PolizaDAO();
		//Ver si es ese metodo (saveInstance) o hay un metodo particular a implementar en PolizaDAO.
		dao.saveInstance(poliza);
		
		return poliza;
	}
	
	
	//TODO: Esto probablemente haya que hacerlo de nuevo.
	public static float calcularPrima(PolizaDTO dto) {
		
	}
	
	public static boolean datosObligatoriosPresentes(PolizaDTO dto) {
		boolean datosPresentes = true;
		GestorLocalizacion gestorLocal = new GestorLocalizacion();
		GestorVehiculo gestorVehiculo = new GestorVehiculo();
		datosPresentes &= gestorLocal.datosObligatoriosPresentes(dto.getLocalidad());
		datosPresentes &= gestorVehiculo.datosObligatoriosPresentes(dto.getVehiculo());
		datosPresentes &= dto.getKmRealizados() != null;
		datosPresentes &= dto.getCantidadSiniestros() != null;
		return datosPresentes;
	}
	
	public static boolean coberturaElegidaEsValida(PolizaDTO dto) {
		GestorVehiculo gestorVehic = new GestorVehiculo();
		boolean vehiculoMayorADiezAnios = gestorVehic.esMayorADiezAnios(dto.getVehiculo());
		//TODO: Esta linea de abajo esta bastante floja de papeles
		//Ver si se puede hacer reconocer la cobertura por la id, si es que la id se puede conocer.
		boolean coberturaEsResponsabilidadCivil = dto.getCobertura().getText().toUpperCase().equals("RESPONSABILIDAD CIVIL");
		//La expresion cuyo resultado retorna es equivalente logicamente a
		//"vehiculoMayorADiezAnios IMPLIES coberturaEsResponsabilidadCivil"
		return !vehiculoMayorADiezAnios || coberturaEsResponsabilidadCivil;
	}
	
	public static boolean existenPolizasVigentes(List<Poliza> polizas) {
		boolean algunaVigente = polizas.stream().anyMatch(poliza -> poliza.getEstado().equals(EstadoPoliza.VIGENTE));
		return algunaVigente;
	}
	
	public static boolean existenCuotasImpagas(Poliza poliza) {
		List<Cuota> cuotas = poliza.getCuotasAsociadas();
		boolean algunaImpaga = cuotas.stream().anyMatch(cuota -> !cuota.getEstado().equals(EstadoCuota.PAGA));
		return algunaImpaga;
	}
	
	public static boolean existenCuotasImpagas(List<Poliza> polizas) {
		boolean algunaPolizaImpaga = polizas.stream().anyMatch(poliza -> existenCuotasImpagas(poliza));
		return algunaPolizaImpaga;
	}
	
	public static List<CuotaDTO> generarCuotas(PolizaDTO dto){
		List<CuotaDTO> cuotaDtos = new ArrayList<CuotaDTO>();
		switch(dto.getTipoPoliza()) {
			case SEMESTRAL: {
				CuotaDTO cuota = new CuotaDTO();
				cuota.setEstado(EstadoCuota.PENDIENTE);
				cuota.setFechaVencimiento(dto.getFechaInicio().minusDays(1));
				cuota.setImporteTotal(dto.getImporteTotal());
				cuotaDtos.add(cuota);
				break;
			}
			case MENSUAL: {
				float importeMensual = dto.getImporteTotal() / 6;
				LocalDateTime fechaPrimerPago = dto.getFechaInicio().minusDays(1);
				for(int i = 0 ; i < 6 ; i++) {
					CuotaDTO cuota = new CuotaDTO();
					cuota.setEstado(EstadoCuota.PENDIENTE);
					cuota.setFechaVencimiento(fechaPrimerPago.plusMonths(i));
					cuota.setImporteTotal(importeMensual);
					cuotaDtos.add(cuota);
				}
				break;
			}
		}
		return cuotaDtos;
	}
	
	public static void validarDTO(PolizaDTO dto) {
		if(!datosObligatoriosPresentes(dto)) {
			throw new DatosObligatoriosAusentesException;
		}
		
		if(!GestorVehiculo.valoresUnicosParaAltaVehiculo(dto.getVehiculo())) {
			throw new ValoresParaVehiculoExistentesException;
		}
		
		if(!coberturaElegidaEsValida(dto)) {
			throw new AutoMuyViejoParaCoberturaElegidaException;
		}
	}
}
