package tp.dto;

import tp.entidad.MedidaDeSeguridad;

public class MedidaDeSeguridadDTO extends NoModificableDTO<MedidaDeSeguridad> {
	
	private String pregunta;
	
	public String getText() {
		return this.pregunta;
	}
}
