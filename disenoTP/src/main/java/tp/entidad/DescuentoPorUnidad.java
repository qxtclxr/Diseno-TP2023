package tp.entidad;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.*;

@Entity
@Table(name="descuentoPorUnidad")
public class DescuentoPorUnidad {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idDescuentoPorUnidad")
	private long idDescuentoPorUnidad;
	
	@Column(nullable = false)
	private String concepto;
	
	
	//relaciones
	
	@OneToOne
	@JoinColumn(name="idValorActualDescPorUnidad")
	private PorcentajeDescPorUnidad valorActualDescPorUnidad;
	
	@OneToMany(fetch= FetchType.LAZY,mappedBy="descAsociado", cascade = CascadeType.ALL)
	private List<PorcentajeDescPorUnidad> valoresPasadosDescPorUnidad;
	
	public DescuentoPorUnidad() {
		super();
	}

	@Override
	public int hashCode() {
		return Objects.hash(idDescuentoPorUnidad);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DescuentoPorUnidad other = (DescuentoPorUnidad) obj;
		return idDescuentoPorUnidad == other.idDescuentoPorUnidad;
	}

	

	@Override
	public String toString() {
		return "DescuentoPorUnidad [idDescuentoPorUnidad=" + idDescuentoPorUnidad + ", concepto=" + concepto
				+ ", valorActualDescPorUnidad=" + valorActualDescPorUnidad + ", valoresPasadosDescPorUnidad="
				+ valoresPasadosDescPorUnidad + "]";
	}

	public long getIdDescuentoPorUnidad() {
		return idDescuentoPorUnidad;
	}

	

	public PorcentajeDescPorUnidad getValorActualDescPorUnidad() {
		return valorActualDescPorUnidad;
	}

	public List<PorcentajeDescPorUnidad> getValoresPasadosDescPorUnidad() {
		return valoresPasadosDescPorUnidad;
	}

	public void setIdDescuentoPorUnidad(long idDescuentoPorUnidad) {
		this.idDescuentoPorUnidad = idDescuentoPorUnidad;
	}



	public void setValorActualDescPorUnidad(PorcentajeDescPorUnidad valorActualDescPorUnidad) {
		this.valorActualDescPorUnidad = valorActualDescPorUnidad;
	}

	public void setValoresPasadosDescPorUnidad(List<PorcentajeDescPorUnidad> valoresPasadosDescPorUnidad) {
		this.valoresPasadosDescPorUnidad = valoresPasadosDescPorUnidad;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}
	
	
	
	
	
	

}
