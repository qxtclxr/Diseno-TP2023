package tp.logica;

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
		Localidad objeto = new Localidad();// = dao.getLocalidadById(dto.getId()).orElseThrow(/*TODO*/);
		return objeto;
	}
	
	public Provincia getProvincia(ProvinciaDTO dto) {
		LocalizacionDAO dao = new LocalizacionDAO();
		Provincia objeto = new Provincia();// = dao.getProvinciaById(dto.getId()).orElseThrow(/*TODO*/);
		return objeto;
	}
	
	public Pais getPais(PaisDTO dto) {
		LocalizacionDAO dao = new LocalizacionDAO();
		Pais objeto = new Pais();// = dao.getPaisById(dto.getId()).orElseThrow(/*TODO*/);
		return objeto;
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
