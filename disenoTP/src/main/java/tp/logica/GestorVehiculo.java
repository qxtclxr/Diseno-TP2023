package tp.logica;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import tp.dto.*;
import tp.entidad.*;
import tp.dao.*;

public class GestorVehiculo {
	
	//TODO: A los tres le falta fijarse si la patente es vigente
	public static boolean existePatente(String patente) {
		if(patente==null) {
			return false;
		}
		PolizaDAO dao = new PolizaDAO();
		List<Poliza> polizas = dao.getByPatente(patente);
		
		return !polizas.isEmpty();
	}
	
	public static boolean existeMotor(String motor) {
		PolizaDAO dao = new PolizaDAO();
		List<Poliza> polizas = dao.getByMotor(motor);
		return !polizas.isEmpty();
	}
	
	public static boolean existeChasis(String chasis) {
		PolizaDAO dao = new PolizaDAO();
		List<Poliza> polizas = dao.getByChasis(chasis);
		return !polizas.isEmpty();
	}
	
	public static boolean valoresUnicosParaAltaVehiculo(VehiculoDTO dto) {
		boolean chasis = existeChasis(dto.getChasis());
		boolean motor = existeMotor(dto.getMotor());
		boolean patente = existePatente(dto.getPatente());
		return !(chasis || motor || patente);
	}
	
	public static Vehiculo crearVehiculo(VehiculoDTO dto) {
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
	
	public static List<AnioModeloDTO> getAniosByModelo(ModeloDTO dto){
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
	
	public static List<ModeloDTO> getModelosByMarca(MarcaDTO marcaDto){
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
	
	public static AnioModelo getAnioModelo(AnioModeloDTO dto) {
		AnioModeloDAO dao = new AnioModeloDAO();
		AnioModelo modelo = dao.getById(dto.getId()).orElseThrow(/*TODO*/);
		return modelo;
	}
	
	public static Modelo getModelo(ModeloDTO dto) {
		ModeloDAO dao = new ModeloDAO();
		Modelo modelo = dao.getById(dto.getId()).orElseThrow(/*TODO*/);
		return modelo;
	}
	
	public static Marca getMarca(MarcaDTO dto) {
		MarcaDAO dao = new MarcaDAO();
		Marca marca = dao.getById(dto.getId()).orElseThrow(/*TODO*/);
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

	public static PorcentajeEstadisticaRobo getPorcentajeEstadisticaRoboActualActual(AnioModeloDTO dto) {
		AnioModelo modelo = getAnioModelo(dto);
		return modelo.getValorActualPorcentajeEstadisticaRobo();
	}
	
	
}
