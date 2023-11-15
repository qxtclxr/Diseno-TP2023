package tp.dto;

import java.time.LocalDateTime;
import tp.entidad.EstadoCivil;
import tp.entidad.Sexo;

public class HijoDeclaradoDTO {
	private LocalDateTime fechaNacimiento;
	private Sexo sexo;
	private EstadoCivil estadoCivil;
	
	public LocalDateTime getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(LocalDateTime fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public Sexo getSexo() {
		return sexo;
	}
	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}
	public EstadoCivil getEstadoCivil() {
		return estadoCivil;
	}
	public void setEstadoCivil(EstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil;
	}
}
