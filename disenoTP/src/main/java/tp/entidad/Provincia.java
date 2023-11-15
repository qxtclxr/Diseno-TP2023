package tp.entidad;

import java.util.Objects;

import jakarta.persistence.*;

@Entity
@Table(name="provincia")
public class Provincia {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idProvincia")
	private long idProvincia;
	
	
	@Column(nullable = false)
	private String nombre;
	
	
	//relaciones
	
	@ManyToOne(fetch= FetchType.EAGER)
	@JoinColumn(name="idPais", foreignKey= @ForeignKey(name="FK_pais_en_provincia"))
	private Pais pais;
	
	public Provincia() {
		super();
	}

	@Override
	public int hashCode() {
		return Objects.hash(idProvincia, nombre, pais);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Provincia other = (Provincia) obj;
		return idProvincia == other.idProvincia && Objects.equals(nombre, other.nombre)
				&& Objects.equals(pais, other.pais);
	}

	@Override
	public String toString() {
		return "Provincia [idProvincia=" + idProvincia + ", nombre=" + nombre + ", pais=" + pais + "]";
	}
	//getters and setters

	public long getIdProvincia() {
		return idProvincia;
	}

	public String getNombre() {
		return nombre;
	}

	public Pais getPais() {
		return pais;
	}

	public void setIdProvincia(long idProvincia) {
		this.idProvincia = idProvincia;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}
	
	
}
