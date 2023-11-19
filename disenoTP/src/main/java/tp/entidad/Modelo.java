package tp.entidad;

import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.*;

@Entity
@Table(name="modelo")
public class Modelo{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idModelo")
	private long idModelo;
	
	@Column(nullable=false, unique=true)
	private String nombreModelo;
	
	@Column(nullable=false)
	private String descripcion;
	

	
	//relaciones
	
	@OneToMany(fetch= FetchType.LAZY, mappedBy="tieneModelo",cascade = CascadeType.ALL)
	private List<AnioModelo> aniosFabricacion;
	
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.MERGE)
	@JoinColumn(name="idMarca", referencedColumnName="idMarca",foreignKey= @ForeignKey(name="FK_marca_en_modelo"))
	private Marca marca;
	
	
	
	public Modelo() {
		super();
	}
	


	@Override
	public int hashCode() {
		return Objects.hash(idModelo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Modelo other = (Modelo) obj;
		return idModelo == other.idModelo;
	}



	@Override
	public String toString() {
		return "Modelo [idModelo=" + idModelo + ", nombreModelo=" + nombreModelo + ", descripcion=" + descripcion
				+ ", aniosFabricacion=" + aniosFabricacion + ", marca=" + marca + "]";
	}



	public long getIdModelo() {
		return idModelo;
	}







	public String getDescripcion() {
		return descripcion;
	}



	public Marca getMarca() {
		return marca;
	}



	

	public void setIdModelo(long idModelo) {
		this.idModelo = idModelo;
	}



	


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public void setMarca(Marca marca) {
		this.marca = marca;
	}



	

	public String getNombreModelo() {
		return nombreModelo;
	}



	
	public void setNombreModelo(String nombreModelo) {
		this.nombreModelo = nombreModelo;
	}



	public List<AnioModelo> getAniosFabricacion() {
		return aniosFabricacion;
	}



	public void setAniosFabricacion(List<AnioModelo> aniosFabricacion) {
		this.aniosFabricacion = aniosFabricacion;
	}

	


	



	
	
	
}
