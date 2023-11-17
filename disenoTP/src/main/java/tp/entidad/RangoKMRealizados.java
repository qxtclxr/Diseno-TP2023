package tp.entidad;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.*;

@Entity
@Table(name="rangoKMRealizados")
public class RangoKMRealizados implements FactorCaracteristico{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idRangoKMRealizados")
	private long idRangoKMRealizados;
	
	@Column(nullable = false)
	private String concepto;
	
	//relaciones
	@OneToOne
	@JoinColumn(name="idValorActualPorcentajeKMRealizados")
	private PorcentajeKMRealizados valorActualPorcentajeKMRealizados;
	
	
	@OneToMany(fetch= FetchType.LAZY,mappedBy="rangoAsociado" ,cascade = CascadeType.ALL)
	private List<PorcentajeKMRealizados> valoresPasadosPorcentajeKMRealizados;
	
	public RangoKMRealizados() {
		super();
	}

	public float getPorcentaje() {
		return this.valorActualPorcentajeKMRealizados.getValorNumerico();
	}
	
	//setters and getters

	@Override
	public String toString() {
		return "RangoKMRealizados [idRangoKMRealizados=" + idRangoKMRealizados + ", concepto=" + concepto
				+ ", valorActualPorcentajeKMRealizados=" + valorActualPorcentajeKMRealizados
				+ ", valoresPasadosPorcentajeKMRealizados=" + valoresPasadosPorcentajeKMRealizados + "]";
	}




	


	@Override
	public int hashCode() {
		return Objects.hash(idRangoKMRealizados);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RangoKMRealizados other = (RangoKMRealizados) obj;
		return idRangoKMRealizados == other.idRangoKMRealizados;
	}

	public long getIdRangoKMRealizados() {
		return idRangoKMRealizados;
	}

	

	public PorcentajeKMRealizados getValorActualPorcentajeKMRealizados() {
		return valorActualPorcentajeKMRealizados;
	}

	public List<PorcentajeKMRealizados> getValoresPasadosPorcentajeKMRealizados() {
		return valoresPasadosPorcentajeKMRealizados;
	}

	public void setIdRangoKMRealizados(long idRangoKMRealizados) {
		this.idRangoKMRealizados = idRangoKMRealizados;
	}

	

	public String getConcepto() {
		return concepto;
	}




	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}




	public void setValorActualPorcentajeKMRealizados(PorcentajeKMRealizados valorActualPorcentajeKMRealizados) {
		this.valorActualPorcentajeKMRealizados = valorActualPorcentajeKMRealizados;
	}

	public void setValoresPasadosPorcentajeKMRealizados(List<PorcentajeKMRealizados> valoresPasadosPorcentajeKMRealizados) {
		this.valoresPasadosPorcentajeKMRealizados = valoresPasadosPorcentajeKMRealizados;
	}
	
	
	
	
}
