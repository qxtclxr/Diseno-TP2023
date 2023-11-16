package tp.entidad;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.*;
import tp.entidad.*;

@Entity
@Table(name="rangoAjustePorHijo")
public class RangoAjustePorHijo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idRangoAjustePorHijo")
	private long idRangoAjustePorHijo;
	
	
	@Column(nullable = false)
	private int desdeCantHijos;
	
	@Column(nullable = false)
	private int hastaCantHijos;
	
	//relaciones
	
	@OneToOne
	@JoinColumn(name="idValorActualPorcentajeCantHijos")
	private PorcentajeCantHijos valorActualPorcentajeCantHijos;
	
	@OneToMany(fetch= FetchType.LAZY, cascade = CascadeType.ALL)
	private List<PorcentajeCantHijos> valoresPasadosPorcentajeCantHijos;
	
	public RangoAjustePorHijo() {
		super();
	}

	@Override
	public int hashCode() {
		return Objects.hash(desdeCantHijos, hastaCantHijos, idRangoAjustePorHijo, valorActualPorcentajeCantHijos,
				valoresPasadosPorcentajeCantHijos);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RangoAjustePorHijo other = (RangoAjustePorHijo) obj;
		return desdeCantHijos == other.desdeCantHijos && hastaCantHijos == other.hastaCantHijos
				&& idRangoAjustePorHijo == other.idRangoAjustePorHijo
				&& Objects.equals(valorActualPorcentajeCantHijos, other.valorActualPorcentajeCantHijos)
				&& Objects.equals(valoresPasadosPorcentajeCantHijos, other.valoresPasadosPorcentajeCantHijos);
	}

	@Override
	public String toString() {
		return "RangoAjustePorHijo [idRangoAjustePorHijo=" + idRangoAjustePorHijo + ", desdeCantHijos=" + desdeCantHijos
				+ ", hastaCantHijos=" + hastaCantHijos + ", valorActualPorcentajeCantHijos="
				+ valorActualPorcentajeCantHijos + ", valoresPasadosPorcentajeCantHijos="
				+ valoresPasadosPorcentajeCantHijos + "]";
	}
	//setters and getters

	public long getIdRangoAjustePorHijo() {
		return idRangoAjustePorHijo;
	}

	public int getDesdeCantHijos() {
		return desdeCantHijos;
	}

	public int getHastaCantHijos() {
		return hastaCantHijos;
	}

	public PorcentajeCantHijos getValorActualPorcentajeCantHijos() {
		return valorActualPorcentajeCantHijos;
	}

	public List<PorcentajeCantHijos> getValoresPasadosPorcentajeCantHijos() {
		return valoresPasadosPorcentajeCantHijos;
	}

	public void setIdRangoAjustePorHijo(long idRangoAjustePorHijo) {
		this.idRangoAjustePorHijo = idRangoAjustePorHijo;
	}

	public void setDesdeCantHijos(int desdeCantHijos) {
		this.desdeCantHijos = desdeCantHijos;
	}

	public void setHastaCantHijos(int hastaCantHijos) {
		this.hastaCantHijos = hastaCantHijos;
	}

	public void setValorActualPorcentajeCantHijos(PorcentajeCantHijos valorActualPorcentajeCantHijos) {
		this.valorActualPorcentajeCantHijos = valorActualPorcentajeCantHijos;
	}

	public void setValoresPasadosPorcentajeCantHijos(List<PorcentajeCantHijos> valoresPasadosPorcentajeCantHijos) {
		this.valoresPasadosPorcentajeCantHijos = valoresPasadosPorcentajeCantHijos;
	}
	
	

}
