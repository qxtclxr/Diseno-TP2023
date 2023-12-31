package tp.entidad;

import java.util.Objects;

import jakarta.persistence.*;

@Entity
@Table(name="pais")
public class Pais {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idPais")
	private long idPais;
	
	@Column(nullable=false)
	private String codPais;
	
	@Column(nullable = false, unique=true)
	private String nombre;
	
	@Column(nullable=false)
	private long secuenciaCliente;
	
	public Pais() {
		super();
	}

	@Override
	public int hashCode() {
		return Objects.hash(idPais);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pais other = (Pais) obj;
		return idPais == other.idPais;
	}

	@Override
	public String toString() {
		return "Pais [idPais=" + idPais + ", nombre=" + nombre + "]";
	}
	//getters and setters

	public long getIdPais() {
		return idPais;
	}

	public String getNombre() {
		return nombre;
	}

	public void setIdPais(long idPais) {
		this.idPais = idPais;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCodPais() {
		return codPais;
	}

	public void setCodPais(String codPais) {
		this.codPais = codPais;
	}

	public long getSecuenciaCliente() {
		return secuenciaCliente;
	}

	public void setSecuenciaCliente(long secuenciaCliente) {
		this.secuenciaCliente = secuenciaCliente;
	}
	
	

}
