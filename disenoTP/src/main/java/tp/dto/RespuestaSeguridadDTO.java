package tp.dto;

public class RespuestaSeguridadDTO {
	private boolean respuesta;
	private MedidaDeSeguridadDTO medida;
	
	public boolean getRespuesta() {
		return respuesta;
	}
	public void setRespuesta(boolean respuesta) {
		this.respuesta = respuesta;
	}
	public MedidaDeSeguridadDTO getMedida() {
		return medida;
	}
	public void setMedida(MedidaDeSeguridadDTO medida) {
		this.medida = medida;
	}
	
}
