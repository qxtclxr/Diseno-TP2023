package tp.logica;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import tp.dto.*;
import tp.entidad.*;
import tp.exception.ObjetoNoEncontradoException;
import tp.dao.*;

public class GestorVehiculo {
	
	public static boolean existePatente(String patente) {
		if(patente==null || patente=="") {
			return false;
		}
		PolizaDAO dao = new PolizaDAO();
		List<Poliza> polizas = dao.getByPatente(patente);
		boolean existeVigente = polizas.stream().anyMatch(p -> p.getEstado().equals(EstadoPoliza.VIGENTE));
		return existeVigente;
	}
	
	public static boolean existeMotor(String motor) {
		PolizaDAO dao = new PolizaDAO();
		List<Poliza> polizas = dao.getByMotor(motor);
		boolean existeVigente = polizas.stream().anyMatch(p -> p.getEstado().equals(EstadoPoliza.VIGENTE));
		return existeVigente;
	}
	
	public static boolean existeChasis(String chasis) {
		PolizaDAO dao = new PolizaDAO();
		List<Poliza> polizas = dao.getByChasis(chasis);
		boolean existeVigente = polizas.stream().anyMatch(p -> p.getEstado().equals(EstadoPoliza.VIGENTE));
		return existeVigente;
	}
	
	public static boolean valoresUnicosParaAltaVehiculo(VehiculoDTO dto) {
		boolean chasis = existeChasis(dto.getChasis());
		boolean motor = existeMotor(dto.getMotor());
		boolean patente = existePatente(dto.getPatente());
		return !(chasis || motor || patente);
	}
	
	public static Vehiculo crearVehiculo(VehiculoDTO dto)
			throws ObjetoNoEncontradoException {
		Vehiculo vehiculo = new Vehiculo();
		vehiculo.setPatente(dto.getPatente());
		vehiculo.setMotor(dto.getMotor());
		vehiculo.setChasis(dto.getChasis());
		vehiculo.setAnioModelo(getAnioModelo(dto.getModelo()));
		return vehiculo;
	}
	
	public static boolean esMayorADiezAnios(VehiculoDTO dto) {
		return (LocalDateTime.now().getYear() - dto.getModelo().getAnio()) > 10;
	}
	
	public static boolean datosObligatoriosPresentes(VehiculoDTO dto) {
		boolean datosPresentes = dto != null;
		datosPresentes &= dto.getModelo() != null;
		if(datosPresentes==false) {
			return false;
		}
		datosPresentes &= dto.getModelo().getModelo() != null;
		if(datosPresentes==false) {
			return false;
		}
		datosPresentes &= dto.getModelo().getModelo().getMarca() != null;
		datosPresentes &= dto.getMotor() != null;
		datosPresentes &= dto.getChasis() != null;
		return datosPresentes;
	}
	
	public static List<AnioModeloDTO> getAniosByModelo(ModeloDTO dto)
			throws ObjetoNoEncontradoException{
		Modelo modelo = getModelo(dto);
		List<AnioModeloDTO> anioDtos = modelo.getAniosFabricacion().stream().
				map(entidad -> getAnioModeloDTO(entidad)).
				collect(Collectors.toList());
		return anioDtos;
		
	}
	
	public static List<Modelo> getModelosByMarca(Marca marca){
		ModeloDAO dao = new ModeloDAO();
		List<Modelo> modelos = dao.getModelosByMarca(marca);
		return modelos;
	}
	
	public static List<ModeloDTO> getModelosByMarca(MarcaDTO marcaDto)
			throws ObjetoNoEncontradoException{
		Marca marca = getMarca(marcaDto);
		List<Modelo> modelos = getModelosByMarca(marca);
		List<ModeloDTO> modelosDto = modelos.stream().
				map(entidad -> getModeloDTO(entidad)).
				collect(Collectors.toList());
		return modelosDto;
	}
	
	public static List<Marca> getAllMarcas(){
		MarcaDAO dao = new MarcaDAO();
		return dao.getAll();
	}
	
	public static List<MarcaDTO> getAllMarcaDTOs(){
		List<Marca> marcas = getAllMarcas();
		List<MarcaDTO> marcaDtos = marcas.stream().
				map(entidad -> getMarcaDTO(entidad)).
				collect(Collectors.toList());
		return marcaDtos;
	}
	
	public static AnioModelo getAnioModelo(AnioModeloDTO dto) throws ObjetoNoEncontradoException {
		AnioModeloDAO dao = new AnioModeloDAO();
		AnioModelo modelo = dao.getById(dto.getId()).orElseThrow(() -> new ObjetoNoEncontradoException());
		return modelo;
	}
	
	public static Modelo getModelo(ModeloDTO dto) throws ObjetoNoEncontradoException {
		ModeloDAO dao = new ModeloDAO();
		Modelo modelo = dao.getById(dto.getId()).orElseThrow(() -> new ObjetoNoEncontradoException());
		return modelo;
	}
	
	public static Marca getMarca(MarcaDTO dto) throws ObjetoNoEncontradoException {
		MarcaDAO dao = new MarcaDAO();
		Marca marca = dao.getById(dto.getId()).orElseThrow(() -> new ObjetoNoEncontradoException());
		return marca;
	}
	
	public static AnioModeloDTO getAnioModeloDTO(AnioModelo entidad) {
		AnioModeloDTO dto = new AnioModeloDTO();
		dto.setId(entidad.getIdAnioModelo());
		dto.setAnio(entidad.getAnio());
		dto.setValoracion(entidad.getValorVehiculo());
		dto.setModelo(getModeloDTO(entidad.getTieneModelo()));
		return dto;
	}
	
	public static ModeloDTO getModeloDTO(Modelo entidad) {
		ModeloDTO dto = new ModeloDTO();
		dto.setId(entidad.getIdModelo());
		dto.setNombre(entidad.getNombreModelo());
		dto.setMarca(getMarcaDTO(entidad.getMarca()));
		return dto;
	}
	
	public static MarcaDTO getMarcaDTO(Marca entidad) {
		MarcaDTO dto = new MarcaDTO();
		dto.setId(entidad.getIdMarca());
		dto.setNombre(entidad.getNombre());
		return dto;
	}

	public static PorcentajeEstadisticaRobo getPorcentajeEstadisticaRoboActual(AnioModeloDTO dto)
			throws ObjetoNoEncontradoException {
		AnioModelo modelo = getAnioModelo(dto);
		return modelo.getValorActualPorcentajeEstadisticaRobo();
	}
	
	
}
