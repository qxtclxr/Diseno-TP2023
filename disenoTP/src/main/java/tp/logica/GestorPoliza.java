package tp.logica;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import tp.app.App;
import tp.dao.*;
import tp.dto.*;
import tp.entidad.*;
import tp.exception.*;
import tp.util.CheckedFunction;

public class GestorPoliza {
	
	public static Poliza crearPoliza(PolizaDTO dto)throws ObjetoNoEncontradoException {
		Poliza poliza = new Poliza();
		
		poliza.setSumaAsegurada(dto.getSumaAsegurada());
		
		poliza.setFechaInicio(dto.getFechaInicio());
		
		poliza.setFechaFin(dto.getFechaFin());
		
		poliza.setEstado(EstadoPoliza.GENERADA);
		
		poliza.setTipoPoliza(dto.getTipoPoliza());
		
		poliza.setFechaEmision(LocalDateTime.now());
		
		poliza.setPremio(dto.getPremio());
		
		poliza.setDescuento(dto.getDescuento());
		
		poliza.setImporteTotal(dto.getImporteTotal());
		
		Vehiculo vehiculo = GestorVehiculo.crearVehiculo(dto.getVehiculo());
		poliza.setVehiculoAsegurado(vehiculo);
		
		List<HijoDeclarado> hijosDeclarados = GestorHijoDeclarado.crearHijosDeclarados(dto.getHijosDeclarados());
		poliza.setHijosDeclarados(hijosDeclarados);
		
		List<Cuota> cuotas = GestorCuota.crearCuotas(dto.getCuotas());
		cuotas.stream().forEach(cuota -> cuota.setPolizaAsociada(poliza));
		poliza.setCuotasAsociadas(cuotas);
		
		Cliente cliente = GestorCliente.getCliente(dto.getCliente());
		GestorCliente.actualizarConsideracion(cliente);
		poliza.setCliente(cliente);
		
		//Factores caracteristicos
		
		FactorCaracteristicoDTO factores = dto.getFactores();
		
		poliza.setPorcMedidaSeguridad(factores.getPorcentajeMedida());
		
		poliza.setPorcCobertura(factores.getPorcentajeCobertura());
		
		poliza.setFactorRiesgoLoc(factores.getPorcentajeRiesgoLocalidad());
		
		poliza.setPorcDescuentoPorU(factores.getDescuentoPorUnidad());
		
		poliza.setPorcCantSiniestros(factores.getPorcentajeSiniestros());
		
		poliza.setPorcKMRealizados(factores.getPorcentajeKm());
		
		poliza.setValorDerechosDeEmision(factores.getDerechosDeEmision());
		
		poliza.setPorcAjustePorHijo(factores.getPorcentajeHijos());
		
		poliza.setPorcEstRobo(factores.getPorcentajeEstadisticaRobo());
		
		poliza.setModificaciones(new ArrayList<ModificacionPoliza>());
		
		poliza.setNroPoliza(generarNroPoliza(dto));
		
		UsuarioDAO d=new UsuarioDAO();
		poliza.setProductorAsociado(d.getById(App.getUsuarioLogeado().getIdUsuario()).get());
		
		return poliza;
	}
	
	private static String generarNroPoliza(PolizaDTO dto) {
		StringBuilder nroPoliza = new StringBuilder();
		SucursalDAO dao = new SucursalDAO();
		Sucursal sucursal = App.getUsuarioLogeado().getSucursalAsociada();
		//format(%0<longitud>d,<numero>). <longitud> fija la longitud del String.
		//Si <numero> no tiene los digitos para llenar la longitud, se hace padding con 0's
		nroPoliza.append(String.format("%04d",sucursal.getCodigoSucursal()));
		nroPoliza.append('-');
		nroPoliza.append(String.format("%07d",dao.getSerialPoliza(sucursal)));
		nroPoliza.append('-');
		nroPoliza.append(String.format("%02d",0));
		return nroPoliza.toString();
	}

	public static Poliza altaPoliza(PolizaDTO dto)
			throws DatosObligatoriosAusentesException,
			ValoresParaVehiculoExistentesException,
			AutoMuyViejoParaCoberturaElegidaException,
			ObjetoNoEncontradoException {
		validarDTO(dto);
		Poliza poliza = crearPoliza(dto);
		PolizaDAO dao = new PolizaDAO();
		dao.altaPoliza(poliza);
		return poliza;
	}
	
	public static float calcularPremio(PolizaDTO dto) {
		FactorCaracteristicoDTO factores = dto.getFactores();
		float porcentaje = 0;
		//PorcentajeCobertura esta en base anual, y la poliza es semestral, por lo que el porcentaje se divide entre 2
		porcentaje += factores.getPorcentajeCobertura().getValorNumerico() / 2 ;
		porcentaje += factores.getPorcentajeRiesgoLocalidad().getValorNumerico();
		porcentaje += factores.getPorcentajeEstadisticaRobo().getValorNumerico();
		porcentaje += factores.getPorcentajeKm().getValorNumerico();
		porcentaje += factores.getPorcentajeSiniestros().getValorNumerico();
		porcentaje += factores.getPorcentajeHijos().getValorNumerico() * dto.getHijosDeclarados().size();
		for(PorcentajeMedidaDeSeguridad porc : factores.getPorcentajeMedida()) {
			porcentaje += porc.getValorNumerico();
		}
		float derechosEmision = factores.getDerechosDeEmision().getValorNumerico();
		float premio = dto.getSumaAsegurada() * (porcentaje/100) + derechosEmision;
		return premio;
	}
	
	public static float calcularDescuentos(PolizaDTO dto) {
		FactorCaracteristicoDTO factores = dto.getFactores();
		float porcentaje = 0;
		porcentaje += factores.getDescuentoPorUnidad().getValorNumerico();
		//Este porcentaje esta en base anual, y la poliza es semestral, por lo que el porcentaje se divide entre 2
		porcentaje += factores.getBonificacionTipoPoliza() / 2;
		return porcentaje;
	}
	
	public static FactorCaracteristicoDTO getFactoresCaracteristicos(PolizaDTO dto) throws ObjetoNoEncontradoException {
		FactorCaracteristicoDTO factores = new FactorCaracteristicoDTO();
		factores.setPorcentajeCobertura(GestorCobertura.getPorcentajeCoberturaActual(dto.getCobertura()));
		factores.setPorcentajeRiesgoLocalidad(GestorLocalizacion.getPorcentajeRiesgoLocalidadActual(dto.getLocalidad()));
		factores.setPorcentajeEstadisticaRobo(GestorVehiculo.getPorcentajeEstadisticaRoboActual(dto.getVehiculo().getModelo()));
		factores.setPorcentajeKm(GestorRangoKMRealizados.getPorcentajeKMRealizadosActual(dto.getKmRealizados()));
		factores.setPorcentajeSiniestros(GestorRangoCantSiniestros.getPorcentajeCantSiniestrosActual(dto.getCantidadSiniestros()));
		factores.setPorcentajeHijos(GestorAjusteHijos.getPorcentajeAjusteHijosActual());
		//CheckedFunction.wrap(): Hace catch de la Exception y la relanza como RuntimeException.
		//De lo contrario la funcion lambda tendra un error de compilacion
		factores.setPorcentajeMedida(dto.getMedidas().stream().
				map(CheckedFunction.wrap(GestorMedidaDeSeguridad::getPorcentajeMedidaDeSeguridadActual)).
				toList());
		factores.setDerechosDeEmision(GestorDerechosDeEmision.getDerechosDeEmisionActual());
		factores.setDescuentoPorUnidad(GestorDescuentoPorUnidad.getDescuentoPorUnidadByCliente(dto.getCliente()));
		factores.setBonificacionTipoPoliza(FacadeSistemaFinanciero.getBonificacionPorTipoPoliza(dto.getTipoPoliza()));
		return factores;
	}
	
	public static void calcularPremioDerechoDeEmisionYDescuentos(PolizaDTO dto)
			throws ObjetoNoEncontradoException {
		dto.setFactores(getFactoresCaracteristicos(dto));
		dto.setPremio(calcularPremio(dto));
		dto.setDescuento(dto.getPremio() * (calcularDescuentos(dto)/100));
		dto.setImporteTotal(dto.getPremio() - dto.getDescuento());
	}
	
	public static boolean datosObligatoriosPresentes(PolizaDTO dto) {
		boolean datosPresentes = true;
		datosPresentes &= GestorLocalizacion.datosObligatoriosPresentes(dto.getLocalidad());
		datosPresentes &= GestorVehiculo.datosObligatoriosPresentes(dto.getVehiculo());
		datosPresentes &= dto.getKmRealizados() != null;
		datosPresentes &= dto.getCantidadSiniestros() != null;
		return datosPresentes;
	}
	
	public static boolean coberturaElegidaEsValida(PolizaDTO dto) {
		boolean vehiculoMayorADiezAnios = GestorVehiculo.esMayorADiezAnios(dto.getVehiculo());
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
				cuota.setOrden(1);
				cuotaDtos.add(cuota);
				break;
			}
			case MENSUAL: {
				float importeMensual = dto.getImporteTotal() / 6;
				LocalDate fechaPrimerPago = dto.getFechaInicio().minusDays(1);
				for(int i = 0 ; i < 6 ; i++) {
					CuotaDTO cuota = new CuotaDTO();
					cuota.setEstado(EstadoCuota.PENDIENTE);
					cuota.setFechaVencimiento(fechaPrimerPago.plusMonths(i));
					cuota.setImporteTotal(importeMensual);
					cuota.setOrden(i+1);
					cuotaDtos.add(cuota);
				}
				break;
			}
		}
		return cuotaDtos;
	}
	
	public static void validarDTO(PolizaDTO dto)
			throws DatosObligatoriosAusentesException,
			ValoresParaVehiculoExistentesException,
			AutoMuyViejoParaCoberturaElegidaException {
		if(!datosObligatoriosPresentes(dto)) {
			throw new DatosObligatoriosAusentesException();
		}
		
		if(!GestorVehiculo.valoresUnicosParaAltaVehiculo(dto.getVehiculo())) {
			throw new ValoresParaVehiculoExistentesException();
		}
		
		if(!coberturaElegidaEsValida(dto)) {
			throw new AutoMuyViejoParaCoberturaElegidaException();
		}
	}
}
