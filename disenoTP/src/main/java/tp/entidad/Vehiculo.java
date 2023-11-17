package tp.entidad;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.*;
@Embeddable
public class Vehiculo {
	/*
	 * Ver unicidad de motor chasis patente
	 */
	
	@Column(nullable=false)
	private LocalDate anioVehiculo;
	
	@Column(nullable=false)
	private String Motor;
	
	@Column(nullable=false)
	private String chasis;
	
	@Column(nullable=false)
	private String patente;
	
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name="idAnioModelo",referencedColumnName="idAnioModelo", foreignKey= @ForeignKey(name="FK_anio_vehiculo"))
	private AnioModelo anioModelo;
	
	public Vehiculo() {
		super();
	}

	

	
	//setters and getters

	@Override
	public int hashCode() {
		return Objects.hash(Motor, chasis, patente);
	}




	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vehiculo other = (Vehiculo) obj;
		return Objects.equals(Motor, other.Motor) && Objects.equals(chasis, other.chasis)
				&& Objects.equals(patente, other.patente);
	}




	@Override
	public String toString() {
		return "Vehiculo [anioVehiculo=" + anioVehiculo + ", Motor=" + Motor + ", chasis=" + chasis + ", patente="
				+ patente + ", anioModelo=" + anioModelo + "]";
	}




	public LocalDate getAnioVehiculo() {
		return anioVehiculo;
	}

	public String getMotor() {
		return Motor;
	}

	public String getChasis() {
		return chasis;
	}

	public String getPatente() {
		return patente;
	}

	

	public void setAnioVehiculo(LocalDate anioVehiculo) {
		this.anioVehiculo = anioVehiculo;
	}

	public void setMotor(String motor) {
		Motor = motor;
	}

	public void setChasis(String chasis) {
		this.chasis = chasis;
	}

	public void setPatente(String patente) {
		this.patente = patente;
	}




	public AnioModelo getAnioModelo() {
		return anioModelo;
	}




	public void setAnioModelo(AnioModelo anioModelo) {
		this.anioModelo = anioModelo;
	}

	
	
	
	
	
	
}
