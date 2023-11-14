package tp.dto;

import tp.entidad.Provincia;

public class ProvinciaDTO extends NoModificableDTO<Provincia> {
	
	private String nombre;
	private PaisDTO pais;
	
	@Override
	public String getText() {
		return this.nombre;
	}
	
	
}
