package tp.dto;

import tp.entidad.Modelo;

public class ModeloDTO extends NoModificableDTO<Modelo> {
	private String nombre;
	private MarcaDTO marca;
	
	@Override
	public String getText() {
		return this.nombre;
	}
	
	
}
