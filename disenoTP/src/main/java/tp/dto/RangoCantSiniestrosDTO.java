package tp.dto;

import tp.entidad.RangoCantSiniestros;

public class RangoCantSiniestrosDTO extends NoModificableDTO<RangoCantSiniestros> {
	
	private String nombre;

	@Override
	public String getText() {
		return this.nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
