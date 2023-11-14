package tp.dto;

import tp.entidad.Localidad;

public class LocalidadDTO extends NoModificableDTO<Localidad> {
	
	private String nombre;
	private ProvinciaDTO provincia;
	
	@Override
	public String getText() {
		return this.nombre;
	}
}
