package tp.dto;

import java.time.LocalDate;
import tp.entidad.EstadoCivil;
import tp.entidad.Sexo;

public class HijoDeclaradoDTO {
	private LocalDate fechaNacimiento;
	private Sexo sexo;
	private EstadoCivil estadoCivil;
	public static int MIN_EDAD = 18;
	public static int MAX_EDAD = 30;
	
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(LocalDate fechaNacimiento) {
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
