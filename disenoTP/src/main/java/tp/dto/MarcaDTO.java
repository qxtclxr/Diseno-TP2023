package tp.dto;

import tp.entidad.Marca;

public class MarcaDTO extends NoModificableDTO<Marca> {
	private String nombre;
	
	@Override
	public String getText() {
		return this.nombre;
	}
	
	
}
