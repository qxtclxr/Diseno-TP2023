package tp.logica;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import tp.dao.LocalizacionDAO;
import tp.dto.LocalidadDTO;
import tp.dto.PaisDTO;
import tp.dto.ProvinciaDTO;
import tp.entidad.Localidad;
import tp.entidad.Pais;
import tp.entidad.Provincia;

public class GestorLocalizacion {
	//Puede que el traer localidad te traiga provincia y pais con ella, depende la implementacion.
	public Localidad getLocalidad(LocalidadDTO dto) {
		LocalizacionDAO dao = new LocalizacionDAO();
		Localidad objeto = dao.getLocalidadById(dto.getId()).orElseThrow(/*TODO*/);
		return objeto;
	}
	
	public Provincia getProvincia(ProvinciaDTO dto) {
		LocalizacionDAO dao = new LocalizacionDAO();
		Provincia objeto = dao.getProvinciaById(dto.getId()).orElseThrow(/*TODO*/);
		return objeto;
	}
	
	public Pais getPais(PaisDTO dto) {
		LocalizacionDAO dao = new LocalizacionDAO();
		Pais objeto = dao.getPaisById(dto.getId()).orElseThrow(/*TODO*/);
		return objeto;
	}
	
	public LocalidadDTO getLocalidadDTO(Localidad entidad) {
		LocalidadDTO dto = new LocalidadDTO();
		dto.setId(entidad.getId());
		dto.setNombre(entidad.getNombre());
		dto.setProvincia(this.getProvinciaDTO(entidad.getProvincia()));
	}
	
	public ProvinciaDTO getProvinciaDTO(Provincia entidad) {
		ProvinciaDTO dto = new ProvinciaDTO();
		dto.setId(entidad.getId());
		dto.setNombre(entidad.getNombre());
		dto.setPais(this.getPaisDTO(entidad.getPais()));
	}
	
	public PaisDTO getPaisDTO(Pais entidad) {
		PaisDTO dto = new PaisDTO();
		dto.setId(entidad.getId());
		dto.setNombre(entidad.getNombre());
		return dto;
	}
	
	public List<ProvinciaDTO> getProvinciasByPais(PaisDTO paisDto){
		Pais pais = this.getPais(paisDto);
		List<Provincia> provincias = this.getProvinciasByPais(pais);
		List<ProvinciaDTO> provinciasDto = provincias.stream().
				map(entidad -> this.getProvinciaDTO(entidad)).
				collect(Collectors.toList());
		return provinciasDto;
	}
	
	public List<Provincia> getProvinciasByPais(Pais pais) {
		LocalizacionDAO dao = new LocalizacionDAO();
		List<Provincia> provincias = dao.getProvinciasByPais(pais);
		return provincias;
		
	}
	
	public List<LocalidadDTO> getLocalidadesByProvincia(ProvinciaDTO provinciaDto){
		Provincia provincia = this.getProvincia(provinciaDto);
		List<Localidad> localidades = this.getLocalidadesByProvincia(provincia);
		List<LocalidadDTO> localidadesDto = localidades.stream().
				map(entidad -> this.getLocalidadDTO(entidad)).
				collect(Collectors.toList());
		return localidadesDto;
	}
	
	public List<Localidad> getLocalidadesByProvincia(Provincia provincia){
		LocalizacionDAO dao = new LocalizacionDAO();
		List<Localidad> localidades = dao.getLocalidadesByProvincia(provincia);
		return localidades;
	}
	
	public boolean datosObligatoriosPresentes(LocalidadDTO dto) {
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
}
