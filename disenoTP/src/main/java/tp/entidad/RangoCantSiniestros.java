package tp.entidad;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.*;
import tp.entidad.*;

@Entity
@Table(name="rangoCantSiniestros")
public class RangoCantSiniestros implements FactorCaracteristico{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idRangoCantSiniestros")
	private long idRangoCantSiniestros;
	
	
	@Column(nullable = false,unique=true)
	private int desdeCant;
	
	@Column(unique=true,nullable=false)
	private String concepto;
	
	//relaciones
	
	@OneToOne
	@JoinColumn(name="idValorActualPorcentajeCantSiniestros")
	private PorcentajeCantSiniestros valorActualPorcentajeCantSiniestros;
	
	@OneToMany(fetch= FetchType.LAZY,mappedBy="rangoAsociado", cascade = CascadeType.ALL)
	private List<PorcentajeCantSiniestros> valoresPasadosPorcentajeCantSiniestros;
	
	public RangoCantSiniestros() {
		super();
	}
	
	@Override
	public float getPorcentaje() {
		return this.valorActualPorcentajeCantSiniestros.getValorNumerico();
	}
	
	

	@Override
	public String toString() {
		return "RangoCantSiniestros [idRangoCantSiniestros=" + idRangoCantSiniestros + ", desdeCant=" + desdeCant
				+ ", valorActualPorcentajeCantSiniestros=" + valorActualPorcentajeCantSiniestros
				+ ", valoresPasadosPorcentajeCantSiniestros=" + valoresPasadosPorcentajeCantSiniestros + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(idRangoCantSiniestros);
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
		return idRangoCantSiniestros == other.idRangoCantSiniestros;
	}

	//setters and getters
	public long getIdRangoCantSiniestros() {
		return idRangoCantSiniestros;
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
	
	public void setValorActualPorcentajeCantSiniestros(PorcentajeCantSiniestros valorActualPorcentajeCantSiniestros) {
		this.valorActualPorcentajeCantSiniestros = valorActualPorcentajeCantSiniestros;
	}
	public void setValoresPasadosPorcentajeCantSiniestros(
			List<PorcentajeCantSiniestros> valoresPasadosPorcentajeCantSiniestros) {
		this.valoresPasadosPorcentajeCantSiniestros = valoresPasadosPorcentajeCantSiniestros;
	}

	public int getDesdeCant() {
		return desdeCant;
	}

	public void setDesdeCant(int desdeCant) {
		this.desdeCant = desdeCant;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}
		
}
