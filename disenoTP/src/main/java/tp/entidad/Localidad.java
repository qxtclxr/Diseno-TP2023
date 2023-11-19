package tp.entidad;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.*;
import tp.entidad.*;

@Entity
@Table(name="localidad")
public class Localidad implements FactorCaracteristico{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idLocalidad")
	private long idLocalidad;
	
	@Column(nullable = false)
	private String nombre;
	
	
	//relaciones
	
	@ManyToOne(fetch= FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name="idProvincia", referencedColumnName="idProvincia",foreignKey= @ForeignKey(name="FK_provincia_en_localidad"))
	private Provincia provincia;
	
	@OneToOne
	@JoinColumn(name="idValorActualFactorRiesgo")
	private FactorRiesgoLocalidad valorActualFactorRiesgo;
	
	@OneToMany(fetch= FetchType.LAZY,mappedBy="localidadAsociada", cascade = CascadeType.ALL)
	private List<FactorRiesgoLocalidad> valoresPasadosFactorRiesgo;
	
	
	public Localidad() {
		super();
	}

	
	public float getPorcentaje() {
		return this.valorActualFactorRiesgo.getValorNumerico();
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(idLocalidad);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Localidad other = (Localidad) obj;
		return idLocalidad == other.idLocalidad;
	}


	@Override
	public String toString() {
		return "Localidad [idLocalidad=" + idLocalidad + ", nombre=" + nombre + ", provincia=" + provincia
				+ ", valorActualFactorRiesgo=" + valorActualFactorRiesgo + ", valoresPasadosFactorRiesgo="
				+ valoresPasadosFactorRiesgo + "]";
	}

	//getters and setters
	public long getIdLocalidad() {
		return idLocalidad;
	}


	public String getNombre() {
		return nombre;
	}


	public Provincia getProvincia() {
		return provincia;
	}


	public FactorRiesgoLocalidad getValorActualFactorRiesgo() {
		return valorActualFactorRiesgo;
	}


	public List<FactorRiesgoLocalidad> getValoresPasadosFactorRiesgo() {
		return valoresPasadosFactorRiesgo;
	}


	public void setIdLocalidad(long idLocalidad) {
		this.idLocalidad = idLocalidad;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}


	public void setValorActualFactorRiesgo(FactorRiesgoLocalidad valorActualFactorRiesgo) {
		this.valorActualFactorRiesgo = valorActualFactorRiesgo;
	}


	public void setValoresPasadosFactorRiesgo(List<FactorRiesgoLocalidad> valoresPasadosFactorRiesgo) {
		this.valoresPasadosFactorRiesgo = valoresPasadosFactorRiesgo;
	}
	
	
	
	
}
