package tp.entidad;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.*;
import tp.entidad.*;

@Entity
@Table(name="cobertura")
public class Cobertura {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idCobertura")
	private long idCobertura;
	
	
	@Column(nullable = false)
	private String tipoCobertura;
	
	@Column
	private String descripcion;
	
	//relaciones
	
	@OneToOne
	@JoinColumn(name="idValorActualPorcentajeCobertura")
	private PorcentajeCobertura valorActualPorcentajeCobertura;
	
	@OneToMany(fetch= FetchType.LAZY, cascade = CascadeType.ALL)
	private List<PorcentajeCobertura> valoresPasadosPorcentajeCobertura;
		
	public Cobertura() {
		super();
	}

	@Override
	public int hashCode() {
		return Objects.hash(idCobertura);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cobertura other = (Cobertura) obj;
		return idCobertura == other.idCobertura;
	}

	@Override
	public String toString() {
		return "Cobertura [idCobertura=" + idCobertura + ", tipoCobertura=" + tipoCobertura + ", descripcion="
				+ descripcion + ", valorActualPorcentajeCobertura=" + valorActualPorcentajeCobertura
				+ ", valoresPasadosPorcentajeCobertura=" + valoresPasadosPorcentajeCobertura + "]";
	}
	//getters and setters

	public String getTipoCobertura() {
		return tipoCobertura;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public PorcentajeCobertura getValorActualPorcentajeCobertura() {
		return valorActualPorcentajeCobertura;
	}

	public List<PorcentajeCobertura> getValoresPasadosPorcentajeCobertura() {
		return valoresPasadosPorcentajeCobertura;
	}

	public void setTipoCobertura(String tipoCobertura) {
		this.tipoCobertura = tipoCobertura;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setValorActualPorcentajeCobertura(PorcentajeCobertura valorActualPorcentajeCobertura) {
		this.valorActualPorcentajeCobertura = valorActualPorcentajeCobertura;
	}

	public void setValoresPasadosPorcentajeCobertura(List<PorcentajeCobertura> valoresPasadosPorcentajeCobertura) {
		this.valoresPasadosPorcentajeCobertura = valoresPasadosPorcentajeCobertura;
	}

	public long getIdCobertura() {
		return idCobertura;
	}

	public void setIdCobertura(long idCobertura) {
		this.idCobertura = idCobertura;
	}
	
	
	
	

}
