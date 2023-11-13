package tp.dto;

import tp.entidad.RangoKMRealizados;

public class AjusteKmRealizadosDTO extends NoModificableDTO<RangoKMRealizados>{
	private String nombre;

	@Override
	public String getText() {
		return nombre;
	}
	
}
