package tp.logica;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javafx.util.Pair;
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
		hijosDeclarados.stream().forEach(hijo-> hijo.setPolizaAsociada(poliza));
		poliza.setHijosDeclarados(hijosDeclarados);
		
		List<Cuota> cuotas = GestorCuota.crearCuotas(dto.getCuotas());
		cuotas.stream().forEach(cuota -> cuota.setPolizaAsociada(poliza));
		poliza.setCuotasAsociadas(cuotas);
		
		Cliente cliente = GestorCliente.getCliente(dto.getCliente());
		GestorCliente.actualizarConsideracion(cliente);
		poliza.setCliente(cliente);
		
		//Factores caracteristicos
		
		FactorCaracteristicoDTO factores = dto.getFactores();
		
		PorcentajeMedidaDeSeguridadDAO medidaDao = new PorcentajeMedidaDeSeguridadDAO();
		//CheckedFunction.wrap(): Hace catch de la Exception y la relanza como RuntimeException.
		//De lo contrario la funcion lambda tendra un error de compilacion
		poliza.setPorcMedidaSeguridad(factores.getPorcentajeMedida().entrySet().stream().
				map(pair -> pair.getKey()).
				map(CheckedFunction.wrap(id -> medidaDao.getById(id).
						orElseThrow(()->new ObjetoNoEncontradoException()))).
				collect(Collectors.toList()));
		
		PorcentajeCoberturaDAO coberDao = new PorcentajeCoberturaDAO();
		poliza.setPorcCobertura(coberDao.getById(factores.getPorcentajeCobertura().getKey()).
				orElseThrow(()->new ObjetoNoEncontradoException()));
		
		PorcentajeRiesgoLocalidadDAO riesgoDao = new PorcentajeRiesgoLocalidadDAO();
		poliza.setFactorRiesgoLoc(riesgoDao.getById(factores.getPorcentajeRiesgoLocalidad().getKey()).
				orElseThrow(()->new ObjetoNoEncontradoException()));
		
		PorcentajeDescPorUnidadDAO descPorUnidadDao = new PorcentajeDescPorUnidadDAO();
		poliza.setPorcDescuentoPorU(descPorUnidadDao.getById(factores.getDescuentoPorUnidad().getKey()).
				orElseThrow(()->new ObjetoNoEncontradoException()));
		
		PorcentajeCantSiniestrosDAO siniesDao = new PorcentajeCantSiniestrosDAO();
		
		poliza.setPorcCantSiniestros(siniesDao.getById(factores.getPorcentajeSiniestros().getKey()).
				orElseThrow(()->new ObjetoNoEncontradoException()));
		
		PorcentajeKMRealizadosDAO kmDao = new PorcentajeKMRealizadosDAO();
		poliza.setPorcKMRealizados(kmDao.getById(factores.getPorcentajeKm().getKey()).
				orElseThrow(()->new ObjetoNoEncontradoException()));
		
		ValorDerechosDeEmisionDAO derechosDao = new ValorDerechosDeEmisionDAO();
		poliza.setValorDerechosDeEmision(derechosDao.getById(factores.getDerechosDeEmision().getKey()).
				orElseThrow(()-> new ObjetoNoEncontradoException()));
		
		PorcentajeAjusteHijosDAO ajusteHijosDao = new PorcentajeAjusteHijosDAO();
		
		poliza.setPorcAjustePorHijo(ajusteHijosDao.getById(factores.getPorcentajeHijos().getKey()).
				orElseThrow(()-> new ObjetoNoEncontradoException()));
		
		PorcentajeEstadisticaRoboDAO estadRoboDao = new PorcentajeEstadisticaRoboDAO();
		
		poliza.setPorcEstRobo(estadRoboDao.getById(factores.getPorcentajeEstadisticaRobo().getKey()).
				orElseThrow(()->new ObjetoNoEncontradoException()));
		
		poliza.setModificaciones(new ArrayList<ModificacionPoliza>());
		
		poliza.setNroPoliza(generarNroPoliza(dto));
		
		UsuarioDAO usuarioDao = new UsuarioDAO();
		poliza.setProductorAsociado(usuarioDao.getById(App.getUsuarioLogeado().getIdUsuario()).
				orElseThrow(()->new ObjetoNoEncontradoException()));
		
		return poliza;
	}
	
	private static String generarNroPoliza(PolizaDTO dto) {
		StringBuilder nroPoliza = new StringBuilder();
		SucursalDAO dao = new SucursalDAO();
		Sucursal sucursal = App.getUsuarioLogeado().getSucursalAsociada();
		//format(%0<longitud>d,<numero>). <longitud> fija la longitud del String.
		//Si <numero> no tiene los digitos para llenar la longitud, se hace padding con 0's
		//Codigo correspondiente a sucursal
		nroPoliza.append(String.format("%04d",sucursal.getCodigoSucursal()));
		nroPoliza.append('-');
		//Codigo serial de cliente con vehiculo (cambia por sucursal)
		nroPoliza.append(String.format("%07d",dao.getSerialPoliza(sucursal)));
		nroPoliza.append('-');
		//Codigo de renovacion
		nroPoliza.append(String.format("%02d",0));
		return nroPoliza.toString();
	}

	public static Poliza altaPoliza(PolizaDTO dto)
			throws DatosObligatoriosAusentesException,
			ValoresParaVehiculoExistentesException,
			AutoMuyViejoParaCoberturaElegidaException,
			ObjetoNoEncontradoException,
			FechaNacimientoHijoInvalidaException {
		validarDTO(dto);
		Poliza poliza = crearPoliza(dto);
		PolizaDAO dao = new PolizaDAO();
		dao.altaPoliza(poliza);
		return poliza;
	}
	
	public static float calcularPremio(PolizaDTO dto) {
		FactorCaracteristicoDTO factores = dto.getFactores();
		float porcentaje = 0F;
		//PorcentajeCobertura esta en base anual, y la poliza es semestral, por lo que el porcentaje se divide entre 2
		porcentaje += factores.getPorcentajeCobertura().getValue() / 2 ;
		porcentaje += factores.getPorcentajeRiesgoLocalidad().getValue();
		porcentaje += factores.getPorcentajeEstadisticaRobo().getValue();
		porcentaje += factores.getPorcentajeKm().getValue();
		porcentaje += factores.getPorcentajeSiniestros().getValue();
		porcentaje += factores.getPorcentajeHijos().getValue() * dto.getHijosDeclarados().size();
		//Se suma el porcentaje de cada medida, y esa suma se suma al porcentaje total
		porcentaje += factores.getPorcentajeMedida().entrySet().stream().
				map(pair -> pair.getValue()).
				reduce(0F,(a,b)->a+b);
		float derechosEmision = factores.getDerechosDeEmision().getValue();
		float premio = dto.getSumaAsegurada() * (porcentaje/100) + derechosEmision;
		return premio;
	}
	
	public static float calcularDescuentos(PolizaDTO dto) {
		FactorCaracteristicoDTO factores = dto.getFactores();
		float porcentaje = 0;
		porcentaje += factores.getDescuentoPorUnidad().getValue();
		//Este porcentaje esta en base anual, y la poliza es semestral, por lo que el porcentaje se divide entre 2
		porcentaje += factores.getBonificacionTipoPoliza() / 2;
		return porcentaje;
	}
	
	public static FactorCaracteristicoDTO getFactoresCaracteristicos(PolizaDTO dto) throws ObjetoNoEncontradoException {
		FactorCaracteristicoDTO factores = new FactorCaracteristicoDTO();
		
		PorcentajeCobertura cobertura = GestorCobertura.getPorcentajeCoberturaActual(dto.getCobertura());
		factores.setPorcentajeCobertura(new Pair<>(cobertura.getIdPorcentajeCobertura(),cobertura.getValorNumerico()));
		
		PorcentajeRiesgoLocalidad riesgoLocal = GestorLocalizacion.getPorcentajeRiesgoLocalidadActual(dto.getLocalidad());
		factores.setPorcentajeRiesgoLocalidad(new Pair<>(riesgoLocal.getIdFactorRiesgoLocalidad(),riesgoLocal.getValorNumerico()));
		
		PorcentajeEstadisticaRobo estadRobo = GestorVehiculo.getPorcentajeEstadisticaRoboActual(dto.getVehiculo().getModelo());
		factores.setPorcentajeEstadisticaRobo(new Pair<>(estadRobo.getIdPorcEstadRobo(),estadRobo.getValorNumerico()));
		
		PorcentajeKMRealizados rangoKm = GestorRangoKMRealizados.getPorcentajeKMRealizadosActual(dto.getKmRealizados());
		factores.setPorcentajeKm(new Pair<>(rangoKm.getIdPorcentajeKMRealizados(),rangoKm.getValorNumerico()));
		
		PorcentajeCantSiniestros siniestros = GestorRangoCantSiniestros.getPorcentajeCantSiniestrosActual(dto.getCantidadSiniestros());		
		factores.setPorcentajeSiniestros(new Pair<>(siniestros.getIdPorcCantSin(),siniestros.getValorNumerico()));
		
		PorcentajeAjusteHijos hijos = GestorAjusteHijos.getPorcentajeAjusteHijosActual();
		factores.setPorcentajeHijos(new Pair<>(hijos.getIdPorcentajeAjusteHijos(),hijos.getValorNumerico()));
		
		//CheckedFunction.wrap(): Hace catch de la Exception y la relanza como RuntimeException.
		//De lo contrario la funcion lambda tendra un error de compilacion
		factores.setPorcentajeMedida(dto.getMedidas().stream().
				map(CheckedFunction.wrap(GestorMedidaDeSeguridad::getPorcentajeMedidaDeSeguridadActual)).
				collect(Collectors.toMap(
						PorcentajeMedidaDeSeguridad::getIdPorcentajeMedidaDeSeguridad,
						PorcentajeMedidaDeSeguridad::getValorNumerico))
				);
		
		ValorDerechosDeEmision derechos = GestorDerechosDeEmision.getDerechosDeEmisionActual();
		factores.setDerechosDeEmision(new Pair<>(derechos.getIdValorDerechosDeEmision(),derechos.getValorNumerico()));
		
		PorcentajeDescPorUnidad descPorUnidad = GestorDescuentoPorUnidad.getDescuentoPorUnidadByCliente(dto.getCliente());
		factores.setDescuentoPorUnidad(new Pair<>(descPorUnidad.getIdPorcentajeDescPorUnidad(),descPorUnidad.getValorNumerico()));
		
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
	
	public static void validarDTO(PolizaDTO dto)
			throws DatosObligatoriosAusentesException,
			ValoresParaVehiculoExistentesException,
			AutoMuyViejoParaCoberturaElegidaException,
			FechaNacimientoHijoInvalidaException {
		if(!datosObligatoriosPresentes(dto)) {
			throw new DatosObligatoriosAusentesException();
		}
		
		if(!GestorVehiculo.valoresUnicosParaAltaVehiculo(dto.getVehiculo())) {
			throw new ValoresParaVehiculoExistentesException();
		}
		
		if(!coberturaElegidaEsValida(dto)) {
			throw new AutoMuyViejoParaCoberturaElegidaException();
		}
		if(!GestorHijoDeclarado.fechaNacimientoEsValida(dto.getHijosDeclarados())) {
			throw new FechaNacimientoHijoInvalidaException();
		}
	}
}
