package tp.dto;

import tp.entidad.Marca;

public class MarcaDTO extends NoModificableDTO<Marca> {
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
