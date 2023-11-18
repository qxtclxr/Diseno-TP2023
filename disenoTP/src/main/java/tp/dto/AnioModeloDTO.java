package tp.dto;

import tp.entidad.AnioModelo;

public class AnioModeloDTO extends NoModificableDTO<AnioModelo>{
	
	private int anio;
	private float valoracion;
	private ModeloDTO modelo;
	
	@Override
	public String getText() {
		return String.valueOf(anio);
	}
	
	public int getAnio() {
		return anio;
	}
	public void setAnio(int anio) {
		this.anio = anio;
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
