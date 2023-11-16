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
	
	public float calcularPrima(PolizaDTO dto) {
		GestorLocalizacion gestorLocal = new GestorLocalizacion();
		GestorVehiculo gestorVehic = new GestorVehiculo();
		GestorRangoKMRealizados gestorKm = new GestorRangoKMRealizados();
		GestorMedidaDeSeguridad gestorMedidas = new GestorMedidaDeSeguridad();
		GestorCobertura gestorCob = new GestorCobertura();
		GestorHijoDeclarado gestorHijo = new GestorHijoDeclarado();
		List<FactorCaracteristico> listaFactores = new ArrayList<>();
		float prima = 0F;
		
		listaFactores.add(gestorCob.getCobertura(dto.getCobertura()));
		listaFactores.add(gestorLocal.getLocalidad(dto.getLocalidad()));
		listaFactores.add(gestorVehic.getModelo(dto.getVehiculo().getModelo()));
		listaFactores.add(gestorKm.getRangoKMRealizados(dto.getKmRealizados()));
		List<MedidaDeSeguridad> medidasSeleccionadas = 
				gestorMedidas.crearRespuestasSeguridad(dto.getRespuestasSeguridad()).
				stream().
				filter(respuesta -> respuesta.getValorRespuesta().equals(true)).
				map(respuesta -> respuesta.getMedida()).
				collect(Collectors.toList());
		listaFactores.addAll(medidasSeleccionadas);
		//TODO: Hay que charlar esto ultimo. Por ahora se considera que hay un porcentaje fijo por hijo
		//y se multiplica este por la cantidad de hijos
		float porcentajeHijos = gestorHijo.getPorcentajeCantHijos() * dto.getHijosDeclarados().size();
		
		prima += porcentajeHijos;
		for(FactorCaracteristico factor : listaFactores) {			
			prima += factor.getPorcentaje();
		}
		
		return (prima/100) * dto.getSumaAsegurada();
	}
	
	public calcularPremio() {
		
	}
	
	public Poliza crearPoliza(PolizaDTO dto) {
		Poliza poliza = new Poliza();
		
		poliza.setSumaAsegurada(dto.getSumaAsegurada());
		poliza.setFechaInicio(dto.getFechaInicio());
		poliza.setEstado(EstadoPoliza.GENERADA);
		poliza.setTipoPoliza(dto.getTipoPoliza());
		poliza.setFechaEmision(LocalDateTime.now());
		//Dependiendo si el DTO contiene "[Derechos|Descuentos|Premio]DTO", hay que cambiar por recuperacion de entidad.
		poliza.setDerechosDeEmision(dto.getDerechosDeEmision());
		poliza.setDescuentoPorU(dto.getDescuentosPorUnidad());
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
		
		GestorCliente gestorCliente = new GestorCliente();
		Cliente cliente = gestorCliente.getCliente(dto.getCliente());
		gestorCliente.actualizarConsideracion(cliente);
		poliza.setCliente(cliente);
		
		GestorLocalizacion gestorLocal = new GestorLocalizacion();
		Localidad localidad = gestorLocal.getLocalidad(dto.getLocalidad());
		poliza.setDomicilioDeRiesgo(localidad);
		
		poliza.setNroPoliza(this.generarNroPoliza(dto));
		
		return poliza;
	}
	
	public Poliza altaPoliza(PolizaDTO dto) {
		
		//TODO: calcularDescuentos()
		this.validarDTO(dto);
		//Ver si estas dos cosas van aca o en crearPoliza()
		
		Poliza poliza = this.crearPoliza(dto);
		PolizaDAO dao = new PolizaDAO();
		//Ver si es ese metodo (saveInstance) o hay un metodo particular a implementar en PolizaDAO.
		dao.saveInstance(poliza);
		
		return poliza;
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
	
	public boolean existenPolizasVigentes(List<Poliza> polizas) {
		boolean algunaVigente = polizas.stream().anyMatch(poliza -> poliza.getEstado().equals(EstadoPoliza.VIGENTE));
		return algunaVigente;
	}
	
	public boolean existenCuotasImpagas(Poliza poliza) {
		List<Cuota> cuotas = poliza.getCuotasAsociadas();
		boolean algunaImpaga = cuotas.stream().anyMatch(cuota -> !cuota.getEstado().equals(EstadoCuota.PAGA));
		return algunaImpaga;
	}
	
	public boolean existenCuotasImpagas(List<Poliza> polizas) {
		boolean algunaPolizaImpaga = polizas.stream().anyMatch(poliza -> this.existenCuotasImpagas(poliza));
		return algunaPolizaImpaga;
	}
	
	public List<CuotaDTO> generarCuotas(PolizaDTO dto){
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
	
	public void validarDTO(PolizaDTO dto) {
		
		GestorVehiculo gestorVehic = new GestorVehiculo();
		
		if(!this.datosObligatoriosPresentes(dto)) {
			throw new DatosObligatoriosAusentesException;
		}
		
		if(!gestorVehic.valoresUnicosParaAltaVehiculo(dto.getVehiculo())) {
			throw new ValoresParaVehiculoExistentesException;
		}
		
		if(!this.coberturaElegidaEsValida(dto)) {
			throw new AutoMuyViejoParaCoberturaElegidaException;
		}
	}
}
