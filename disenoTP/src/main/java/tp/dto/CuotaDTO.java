package tp.dto;

import java.time.LocalDateTime;

import tp.entidad.EstadoCuota;

public class CuotaDTO {
	//private int id; //Esto quiza sirva despues
	private LocalDateTime fechaVencimiento;
	private float importeTotal;
	private EstadoCuota estado;
	private float interesAsociado = 0F;
	
	public float getInteresAsociado() {
		return interesAsociado;
	}
	public void setInteresAsociado(float interesAsociado) {
		this.interesAsociado = interesAsociado;
	}
	public LocalDateTime getFechaVencimiento() {
		return fechaVencimiento;
	}
	public void setFechaVencimiento(LocalDateTime fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	public float getImporteTotal() {
		return importeTotal;
	}
	public void setImporteTotal(float importeTotal) {
		this.importeTotal = importeTotal;
	}
	public EstadoCuota getEstado() {
		return estado;
	}
	public void setEstado(EstadoCuota estado) {
		this.estado = estado;
	}
}
