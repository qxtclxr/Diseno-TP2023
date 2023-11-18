package tp.entidad;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.*;
@Entity
@Table(name="anioModelo")
public class AnioModelo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idAnioModelo")
	private long idAnioModelo;
	
	//ver los nullable de estas columnas
	@Column
	private float valorVehiculo;
	
	@Column(nullable=false)
	private int anio;
	
	//relaciones
	
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name="idModelo",referencedColumnName="idModelo" ,foreignKey= @ForeignKey(name="FK_modelo_anio"))
	private Modelo tieneModelo;
	
	@OneToOne
	@JoinColumn(name="idPorcEstadRob")
	private PorcentajeEstadisticaRobo valorActualPorcentajeEstadisticaRobo;
	
	@OneToMany(fetch= FetchType.LAZY,mappedBy="anioModeloAsociado", cascade = CascadeType.ALL)
	private List<PorcentajeEstadisticaRobo> valoresPasadosPorcentajeEstadisticaRobo;

	public AnioModelo() {
		super();
	}
	@Override
	public int hashCode() {
		return Objects.hash(idAnioModelo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AnioModelo other = (AnioModelo) obj;
		return idAnioModelo == other.idAnioModelo;
	}

	@Override
	public String toString() {
		return "AnioModelo [idAnioModelo=" + idAnioModelo + ", valorVehiculo=" + valorVehiculo + ", anio=" + anio
				+ ", tieneModelo=" + tieneModelo + ", valorActualPorcentajeEstadisticaRobo="
				+ valorActualPorcentajeEstadisticaRobo + ", valoresPasadosPorcentajeEstadisticaRobo="
				+ valoresPasadosPorcentajeEstadisticaRobo + "]";
	}

	public long getIdAnioModelo() {
		return idAnioModelo;
	}

	public float getValorVehiculo() {
		return valorVehiculo;
	}

	public int getAnio() {
		return anio;
	}

	public Modelo getTieneModelo() {
		return tieneModelo;
	}

	public PorcentajeEstadisticaRobo getValorActualPorcentajeEstadisticaRobo() {
		return valorActualPorcentajeEstadisticaRobo;
	}

	public List<PorcentajeEstadisticaRobo> getValoresPasadosPorcentajeEstadisticaRobo() {
		return valoresPasadosPorcentajeEstadisticaRobo;
	}

	public void setIdAnioModelo(long idAnioModelo) {
		this.idAnioModelo = idAnioModelo;
	}

	public void setValorVehiculo(float valorVehiculo) {
		this.valorVehiculo = valorVehiculo;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	public void setTieneModelo(Modelo tieneModelo) {
		this.tieneModelo = tieneModelo;
	}

	public void setValorActualPorcentajeEstadisticaRobo(PorcentajeEstadisticaRobo valorActualPorcentajeEstadisticaRobo) {
		this.valorActualPorcentajeEstadisticaRobo = valorActualPorcentajeEstadisticaRobo;
	}

	public void setValoresPasadosPorcentajeEstadisticaRobo(
			List<PorcentajeEstadisticaRobo> valoresPasadosPorcentajeEstadisticaRobo) {
		this.valoresPasadosPorcentajeEstadisticaRobo = valoresPasadosPorcentajeEstadisticaRobo;
	}
	
	
	
}
