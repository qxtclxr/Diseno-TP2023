package tp.dto;

import tp.entidad.AñoModelo;

public class AñoModeloDTO implements NoModificableDTO<AñoModelo>{
	
	private int año;
	private float valoracion;
	private ModeloDTO modelo;
	
	public int getAño() {
		return año;
	}
	public void setAño(int año) {
		this.año = año;
	}
	public float getValoracion() {
		return valoracion;
	}
	public void setValoracion(float valoracion) {
		this.valoracion = valoracion;
	}
	public ModeloDTO getModelo() {
		return modelo;
	}
	public void setModelo(ModeloDTO modelo) {
		this.modelo = modelo;
	}
	
	
}
