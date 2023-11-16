package tp.dto;

import tp.entidad.Cobertura;

public class CoberturaDTO extends NoModificableDTO<Cobertura> {
	
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
