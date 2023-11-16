package tp.dto;

import tp.entidad.RangoKMRealizados;

public class RangoKMRealizadosDTO extends NoModificableDTO<RangoKMRealizados>{
	private String nombre;

	@Override
	public String getText() {
		return nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
