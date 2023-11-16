package tp.dto;

import tp.entidad.Localidad;

public class LocalidadDTO extends NoModificableDTO<Localidad> {
	
	private String nombre;
	private ProvinciaDTO provincia;
	
	@Override
	public String getText() {
		return this.nombre;
	}

	public ProvinciaDTO getProvincia() {
		return provincia;
	}

	public void setProvincia(ProvinciaDTO provincia) {
		this.provincia = provincia;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
