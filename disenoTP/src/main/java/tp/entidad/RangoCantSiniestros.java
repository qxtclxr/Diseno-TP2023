package tp.entidad;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.*;
import tp.entidad.*;

@Entity
@Table(name="rangoCantSiniestros")
public class RangoCantSiniestros {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idRangoCantSiniestros")
	private long idRangoCantSiniestros;
	
	
	@Column(nullable = false)
	private int desdeCantSiniestros;
	
	@Column(nullable = false)
	private int hastaCantSiniestros;
	
	//relaciones
	
	@OneToOne
	@JoinColumn(name="idValorActualPorcentajeCantSiniestros")
	private PorcentajeCantSiniestros valorActualPorcentajeCantSiniestros;
	
	@OneToMany(fetch= FetchType.LAZY, cascade = CascadeType.ALL)
	private List<PorcentajeCantSiniestros> valoresPasadosPorcentajeCantSiniestros;
	public RangoCantSiniestros() {
		super();
	}
	@Override
	public int hashCode() {
		return Objects.hash(desdeCantSiniestros, hastaCantSiniestros, idRangoCantSiniestros,
				valorActualPorcentajeCantSiniestros, valoresPasadosPorcentajeCantSiniestros);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RangoCantSiniestros other = (RangoCantSiniestros) obj;
		return desdeCantSiniestros == other.desdeCantSiniestros && hastaCantSiniestros == other.hastaCantSiniestros
				&& idRangoCantSiniestros == other.idRangoCantSiniestros
				&& Objects.equals(valorActualPorcentajeCantSiniestros, other.valorActualPorcentajeCantSiniestros)
				&& Objects.equals(valoresPasadosPorcentajeCantSiniestros, other.valoresPasadosPorcentajeCantSiniestros);
	}
	@Override
	public String toString() {
		return "RangoCantSiniestros [idRangoCantSiniestros=" + idRangoCantSiniestros + ", desdeCantSiniestros="
				+ desdeCantSiniestros + ", hastaCantSiniestros=" + hastaCantSiniestros
				+ ", valorActualPorcentajeCantSiniestros=" + valorActualPorcentajeCantSiniestros
				+ ", valoresPasadosPorcentajeCantSiniestros=" + valoresPasadosPorcentajeCantSiniestros + "]";
	}
	//setters and getters
	public long getIdRangoCantSiniestros() {
		return idRangoCantSiniestros;
	}
	public int getDesdeCantSiniestros() {
		return desdeCantSiniestros;
	}
	public int getHastaCantSiniestros() {
		return hastaCantSiniestros;
	}
	public PorcentajeCantSiniestros getValorActualPorcentajeCantSiniestros() {
		return valorActualPorcentajeCantSiniestros;
	}
	public List<PorcentajeCantSiniestros> getValoresPasadosPorcentajeCantSiniestros() {
		return valoresPasadosPorcentajeCantSiniestros;
	}
	public void setIdRangoCantSiniestros(long idRangoCantSiniestros) {
		this.idRangoCantSiniestros = idRangoCantSiniestros;
	}
	public void setDesdeCantSiniestros(int desdeCantSiniestros) {
		this.desdeCantSiniestros = desdeCantSiniestros;
	}
	public void setHastaCantSiniestros(int hastaCantSiniestros) {
		this.hastaCantSiniestros = hastaCantSiniestros;
	}
	public void setValorActualPorcentajeCantSiniestros(PorcentajeCantSiniestros valorActualPorcentajeCantSiniestros) {
		this.valorActualPorcentajeCantSiniestros = valorActualPorcentajeCantSiniestros;
	}
	public void setValoresPasadosPorcentajeCantSiniestros(
			List<PorcentajeCantSiniestros> valoresPasadosPorcentajeCantSiniestros) {
		this.valoresPasadosPorcentajeCantSiniestros = valoresPasadosPorcentajeCantSiniestros;
	}
	
}
