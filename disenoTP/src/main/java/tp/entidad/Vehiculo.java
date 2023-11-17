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

	@Override
	public int hashCode() {
		return Objects.hash(Motor, anioVehiculo, chasis, patente, tieneModelo);
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
		return Objects.equals(Motor, other.Motor) && Objects.equals(anioVehiculo, other.anioVehiculo)
				&& Objects.equals(chasis, other.chasis) && Objects.equals(patente, other.patente)
				&& Objects.equals(tieneModelo, other.tieneModelo);
	}

	@Override
	public String toString() {
		return "Vehiculo [anioVehiculo=" + anioVehiculo + ", Motor=" + Motor + ", chasis=" + chasis + ", patente="
				+ patente + ", tieneModelo=" + tieneModelo + "]";
	}
	//setters and getters

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

	public Modelo getTieneModelo() {
		return tieneModelo;
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

	public void setTieneModelo(Modelo tieneModelo) {
		this.tieneModelo = tieneModelo;
	}
	
	
	
	
	
}
