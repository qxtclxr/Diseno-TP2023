package tp.entidad;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.*;
import tp.entidad.*;

@Entity
@Table(name="rangoKMRealizados")
public class RangoKMRealizados {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idRangoKMRealizados")
	private long idRangoKMRealizados;
	
	
	@Column(nullable = false)
	private long desdeKMRealizados;
	
	@Column(nullable = false)
	private long hastaKMRealizados;
	
	//relaciones
	@OneToOne
	@JoinColumn(name="idValorActualPorcentajeKMRealizados")
	private PorcentajeKMRealizados valorActualPorcentajeKMRealizados;
	
	
	@OneToMany(fetch= FetchType.LAZY, cascade = CascadeType.ALL)
	private List<PorcentajeKMRealizados> valoresPasadosPorcentajeKMRealizados;
	
	public RangoKMRealizados() {
		super();
	}

	@Override
	public int hashCode() {
		return Objects.hash(desdeKMRealizados, hastaKMRealizados, idRangoKMRealizados,
				valorActualPorcentajeKMRealizados, valoresPasadosPorcentajeKMRealizados);
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
		return desdeKMRealizados == other.desdeKMRealizados && hastaKMRealizados == other.hastaKMRealizados
				&& idRangoKMRealizados == other.idRangoKMRealizados
				&& Objects.equals(valorActualPorcentajeKMRealizados, other.valorActualPorcentajeKMRealizados)
				&& Objects.equals(valoresPasadosPorcentajeKMRealizados, other.valoresPasadosPorcentajeKMRealizados);
	}

	@Override
	public String toString() {
		return "RangoKMRealizados [idRangoKMRealizados=" + idRangoKMRealizados + ", desdeKMRealizados="
				+ desdeKMRealizados + ", hastaKMRealizados=" + hastaKMRealizados
				+ ", valorActualPorcentajeKMRealizados=" + valorActualPorcentajeKMRealizados
				+ ", valoresPasadosPorcentajeKMRealizados=" + valoresPasadosPorcentajeKMRealizados + "]";
	}
	//setters and getters

	public long getIdRangoKMRealizados() {
		return idRangoKMRealizados;
	}

	public long getDesdeKMRealizados() {
		return desdeKMRealizados;
	}

	public long getHastaKMRealizados() {
		return hastaKMRealizados;
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

	public void setDesdeKMRealizados(long desdeKMRealizados) {
		this.desdeKMRealizados = desdeKMRealizados;
	}

	public void setHastaKMRealizados(long hastaKMRealizados) {
		this.hastaKMRealizados = hastaKMRealizados;
	}

	public void setValorActualPorcentajeKMRealizados(PorcentajeKMRealizados valorActualPorcentajeKMRealizados) {
		this.valorActualPorcentajeKMRealizados = valorActualPorcentajeKMRealizados;
	}

	public void setValoresPasadosPorcentajeKMRealizados(List<PorcentajeKMRealizados> valoresPasadosPorcentajeKMRealizados) {
		this.valoresPasadosPorcentajeKMRealizados = valoresPasadosPorcentajeKMRealizados;
	}
	
	
	
}
