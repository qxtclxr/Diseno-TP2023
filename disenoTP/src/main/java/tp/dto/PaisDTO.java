package tp.dto;

import tp.entidad.Pais;

public class PaisDTO extends NoModificableDTO<Pais> {
	
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
