package tp.dto;

import java.util.Date;

public class VehiculoDTO {
	private String motor;
	private String chasis;
	private String patente;
	private AnioModeloDTO modelo;
	
	public String getMotor() {
		return motor;
	}
	public void setMotor(String motor) {
		this.motor = motor;
	}
	public String getChasis() {
		return chasis;
	}
	public void setChasis(String chasis) {
		this.chasis = chasis;
	}
	public String getPatente() {
		return patente;
	}
	public void setPatente(String patente) {
		this.patente = patente;
	}
	public AnioModeloDTO getModelo() {
		return modelo;
	}
	public void setModelo(AnioModeloDTO modelo) {
		this.modelo = modelo;
	}
	
}
