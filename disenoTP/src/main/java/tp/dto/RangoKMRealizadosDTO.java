package tp.dto;

import tp.entidad.RangoKMRealizados;

public class RangoKMRealizadosDTO extends NoModificableDTO<RangoKMRealizados>{
	private String nombre;

	@Override
	public String getText() {
		return nombre;
	}
}
