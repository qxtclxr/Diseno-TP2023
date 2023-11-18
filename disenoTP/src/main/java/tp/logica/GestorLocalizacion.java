package tp.logica;

import java.util.List;
import java.util.stream.Collectors;
import tp.dao.*;
import tp.dto.*;
import tp.entidad.*;

public class GestorLocalizacion {
	//Puede que el traer localidad te traiga provincia y pais con ella, depende la implementacion.
	public static Localidad getLocalidad(LocalidadDTO dto) {
		LocalidadDAO dao = new LocalidadDAO();
		Localidad objeto = dao.getById(dto.getId()).orElseThrow(/*TODO*/);
		return objeto;
	}
	
	public static Provincia getProvincia(ProvinciaDTO dto) {
		ProvinciaDAO dao = new ProvinciaDAO();
		Provincia objeto = dao.getById(dto.getId()).orElseThrow(/*TODO*/);
		return objeto;
	}
	
	public static Pais getPais(PaisDTO dto) {
		PaisDAO dao = new PaisDAO();
		Pais objeto = dao.getById(dto.getId()).orElseThrow(/*TODO*/);
		return objeto;
	}
	
	public static LocalidadDTO getLocalidadDTO(Localidad entidad) {
		LocalidadDTO dto = new LocalidadDTO();
		dto.setId(entidad.getIdLocalidad());
		dto.setNombre(entidad.getNombre());
		dto.setProvincia(getProvinciaDTO(entidad.getProvincia()));
		return dto;
	}
	
	public static ProvinciaDTO getProvinciaDTO(Provincia entidad) {
		ProvinciaDTO dto = new ProvinciaDTO();
		dto.setId(entidad.getIdProvincia());
		dto.setNombre(entidad.getNombre());
		dto.setPais(getPaisDTO(entidad.getPais()));
		return dto;
	}
	
	public static PaisDTO getPaisDTO(Pais entidad) {
		PaisDTO dto = new PaisDTO();
		dto.setId(entidad.getIdPais());
		dto.setNombre(entidad.getNombre());
		return dto;
	}
	
	public static List<ProvinciaDTO> getProvinciasByPais(PaisDTO paisDto){
		Pais pais = getPais(paisDto);
		List<Provincia> provincias = getProvinciasByPais(pais);
		List<ProvinciaDTO> provinciasDto = provincias.stream().
				map(entidad -> getProvinciaDTO(entidad)).
				collect(Collectors.toList());
		return provinciasDto;
	}
	
	public static List<Provincia> getProvinciasByPais(Pais pais) {
		ProvinciaDAO dao = new ProvinciaDAO();
		List<Provincia> provincias = dao.getProvinciasByPais(pais);
		return provincias;
		
	}
	
	public static List<LocalidadDTO> getLocalidadesByProvincia(ProvinciaDTO provinciaDto){
		Provincia provincia = getProvincia(provinciaDto);
		List<Localidad> localidades = getLocalidadesByProvincia(provincia);
		List<LocalidadDTO> localidadesDto = localidades.stream().
				map(entidad -> getLocalidadDTO(entidad)).
				collect(Collectors.toList());
		return localidadesDto;
	}
	
	public static List<Localidad> getLocalidadesByProvincia(Provincia provincia){
		LocalidadDAO dao = new LocalidadDAO();
		List<Localidad> localidades = dao.getLocalidadesByProvincia(provincia);
		return localidades;
	}
	
	public static boolean datosObligatoriosPresentes(LocalidadDTO dto) {
		boolean datosPresentes = dto != null;
		if(datosPresentes==false) {
			return false;
		}
		datosPresentes &= dto.getProvincia() != null;
		if(datosPresentes == false) {
			return false;
		}
		datosPresentes &= dto.getProvincia().getPais() != null;
		return datosPresentes;
	}

	public static FactorRiesgoLocalidad getPorcentajeRiesgoLocalidadActual(LocalidadDTO dto) {
		Localidad localidad = getLocalidad(dto);
		return localidad.getValorActualFactorRiesgo();
	}
}
