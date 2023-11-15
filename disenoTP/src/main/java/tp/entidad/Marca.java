package tp.entidad;

import java.util.Objects;

import jakarta.persistence.*;

@Entity
@Table(name="marca")
public class Marca {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idMarca")
	private long idMarca;
	
	@Column(nullable=false)
	private String nombre;
	
	public Marca() {
		super();
	}

	@Override
	public String toString() {
		return "Marca [idMarca=" + idMarca + ", nombre=" + nombre + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(idMarca);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Marca other = (Marca) obj;
		return idMarca == other.idMarca;
	}
	//getters and setters

	public long getIdMarca() {
		return idMarca;
	}

	public String getNombre() {
		return nombre;
	}

	public void setIdMarca(long idMarca) {
		this.idMarca = idMarca;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}
