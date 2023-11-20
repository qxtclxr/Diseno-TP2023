package tp.entidad;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.*;
@Embeddable
public class Vehiculo {
	/*
	 * Ver unicidad de motor chasis patente
	 */
	/*
	@Column(nullable=true)
	private Integer anioVehiculo;
	*/
	@Column(nullable=false)
	private String motor;
	
	@Column(nullable=false)
	private String chasis;
	
	@Column(nullable=false)
	private String patente;
	
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.MERGE)
	@JoinColumn(name="idAnioModelo",referencedColumnName="idAnioModelo", foreignKey= @ForeignKey(name="FK_anio_vehiculo"))
	private AnioModelo anioModelo;
	
	public Vehiculo() {
		super();
	}

	

	
	//setters and getters

	




	




	@Override
	public int hashCode() {
		return Objects.hash(chasis, motor, patente);
	}


/*

	@Override
	public String toString() {
		return "Vehiculo [anioVehiculo=" + anioVehiculo + ", motor=" + motor + ", chasis=" + chasis + ", patente="
				+ patente + ", anioModelo=" + anioModelo + "]";
	}


*/

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vehiculo other = (Vehiculo) obj;
		return Objects.equals(chasis, other.chasis) && Objects.equals(motor, other.motor)
				&& Objects.equals(patente, other.patente);
	}



/*
	public int getAnioVehiculo() {
		return anioVehiculo;
	}*/

	

	public String getChasis() {
		return chasis;
	}

	public String getPatente() {
		return patente;
	}

	
/*
	public void setAnioVehiculo(int anioVehiculo) {
		this.anioVehiculo = anioVehiculo;
	}
*/
	

	public String getMotor() {
		return motor;
	}




	public void setMotor(String motor) {
		this.motor = motor;
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
