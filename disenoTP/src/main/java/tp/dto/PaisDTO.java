package tp.dto;

import tp.entidad.Pais;

public class PaisDTO extends NoModificableDTO<Pais> {
	
	private String nombre;

	@Override
	public String getText() {
		return this.nombre;
	}
}
