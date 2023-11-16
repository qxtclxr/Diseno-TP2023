package tp.logica;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import tp.dto.*;
import tp.entidad.*;
import tp.dao.*;

public class GestorVehiculo {
	
	public boolean existePatente(String patente) {
		if(patente==null) {
			return false;
		}
		PolizaDAO dao = new PolizaDAO();
		List<Poliza> polizas = dao.getByPatente(patente);
		return !polizas.isEmpty();
	}
	
	public boolean existeMotor(String motor) {
		PolizaDAO dao = new PolizaDAO();
		List<Poliza> polizas = dao.getByMotor(motor);
		return !polizas.isEmpty();
	}
	
	public boolean existeChasis(String chasis) {
		PolizaDAO dao = new PolizaDAO();
		List<Poliza> polizas = dao.getByChasis(chasis);
		return !polizas.isEmpty();
	}
	
	public boolean valoresUnicosParaAltaVehiculo(VehiculoDTO dto) {
		boolean chasis = this.existeChasis(dto.getChasis());
		boolean motor = this.existeMotor(dto.getMotor());
		boolean patente = this.existePatente(dto.getPatente());
		return !(chasis || motor || patente);
	}
	
	public Vehiculo crearVehiculo(VehiculoDTO dto) {
		Vehiculo vehiculo = new Vehiculo();
		vehiculo.setPatente(dto.getPatente());
		vehiculo.setMotor(dto.getMotor());
		vehiculo.setChasis(dto.getChasis());
		vehiculo.setTieneModelo(this.getModelo(dto.getModelo()));
		vehiculo.setAnioVehiculo(LocalDate.ofYearDay(dto.getAño(), 1));
		return vehiculo;
	}
	
	public boolean esMayorADiezAños(VehiculoDTO dto) {
		return (LocalDateTime.now().getYear() - dto.getAño()) > 10;
	}
	
	public boolean datosObligatoriosPresentes(VehiculoDTO dto) {
		boolean datosPresentes = dto != null;
		datosPresentes &= dto.getModelo() != null;
		if(datosPresentes==false) {
			return false;
		}
		datosPresentes &= dto.getModelo().getMarca() != null;
		datosPresentes &= dto.getAño() != null;
		datosPresentes &= dto.getMotor() != null;
		datosPresentes &= dto.getChasis() != null;
		return datosPresentes;
	}
	
	public float getValorModelo(ModeloDTO dto) {
		Modelo modelo = this.getModelo(dto);
		return modelo.getValor();
	}
	
	public List<Modelo> getModelosByMarca(Marca marca){
		ModeloDAO dao = new ModeloDAO();
		List<Modelo> modelos = dao.getModelosByMarca(marca);
		return modelos;
	}
	
	public List<ModeloDTO> getModelosByMarca(MarcaDTO marcaDto){
		Marca marca = this.getMarca(marcaDto);
		List<Modelo> modelos = this.getModelosByMarca(marca);
		List<ModeloDTO> modelosDto = modelos.stream().
				map(entidad -> getModeloDTO(entidad)).
				collect(Collectors.toList());
		return modelosDto;
	}
	
	public List<Marca> getAllMarcas(){
		MarcaDAO dao = new MarcaDAO();
		return dao.getAll();
	}
	
	public List<MarcaDTO> getAllMarcaDTOs(){
		List<Marca> marcas = this.getAllMarcas();
		List<MarcaDTO> marcaDtos = marcas.stream().
				map(entidad -> this.getMarcaDTO(entidad)).
				collect(Collectors.toList());
		return marcaDtos;
	}
	
	public Modelo getModelo(ModeloDTO dto) {
		ModeloDAO dao = new ModeloDAO();
		Modelo modelo = dao.getById(dto.getId()).orElseThrow(/*TODO*/);
		return modelo;
	}
	
	public Marca getMarca(MarcaDTO dto) {
		MarcaDAO dao = new MarcaDAO();
		Marca marca = dao.getById(dto.getId()).orElseThrow(/*TODO*/);
		return marca;
	}
	
	public ModeloDTO getModeloDTO(Modelo entidad) {
		ModeloDTO dto = new ModeloDTO();
		dto.setId(entidad.getIdModelo());
		dto.setNombre(entidad.getNombreModelo());
		dto.setMarca(this.getMarcaDTO(entidad.getMarca()));
		return dto;
	}
	
	public MarcaDTO getMarcaDTO(Marca entidad) {
		MarcaDTO dto = new MarcaDTO();
		dto.setId(entidad.getIdMarca());
		dto.setNombre(entidad.getNombre());
		return dto;
	}
}
