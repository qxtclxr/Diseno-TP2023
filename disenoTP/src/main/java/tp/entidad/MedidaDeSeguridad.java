package tp.entidad;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.*;

@Entity
@Table(name="medidaDeSeguridad")
public class MedidaDeSeguridad {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idMedidaDeSeguridad")
	private int idMedidaDeSeguridad;
	
	@Column(nullable=false,unique=true)
	private String pregunta;
	
	//relaciones
	
	@OneToOne
	@JoinColumn(name="idValorActualPorcentajeMedidaDeSeguridad")
	private PorcentajeMedidaDeSeguridad valorActualPorcMedidaDeSeg;
	
	@OneToMany(fetch= FetchType.LAZY, cascade = CascadeType.ALL)
	private List<PorcentajeMedidaDeSeguridad> valoresPasadosPorcMedidaDeSeg;

	public MedidaDeSeguridad() {
		super();
	}

	@Override
	public int hashCode() {
		return Objects.hash(idMedidaDeSeguridad);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MedidaDeSeguridad other = (MedidaDeSeguridad) obj;
		return idMedidaDeSeguridad == other.idMedidaDeSeguridad;
	}

	@Override
	public String toString() {
		return "MedidaDeSeguridad [idMedidaDeSeguridad=" + idMedidaDeSeguridad + ", pregunta=" + pregunta
				+ ", valorActualPorcMedidaDeSeg=" + valorActualPorcMedidaDeSeg + ", valoresPasadosPorcMedidaDeSeg="
				+ valoresPasadosPorcMedidaDeSeg + "]";
	}
	//getters and setters

	public int getIdMedidaDeSeguridad() {
		return idMedidaDeSeguridad;
	}

	public String getPregunta() {
		return pregunta;
	}

	public PorcentajeMedidaDeSeguridad getValorActualPorcMedidaDeSeg() {
		return valorActualPorcMedidaDeSeg;
	}

	public List<PorcentajeMedidaDeSeguridad> getValoresPasadosPorcMedidaDeSeg() {
		return valoresPasadosPorcMedidaDeSeg;
	}

	public void setIdMedidaDeSeguridad(int idMedidaDeSeguridad) {
		this.idMedidaDeSeguridad = idMedidaDeSeguridad;
	}

	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}

	public void setValorActualPorcMedidaDeSeg(PorcentajeMedidaDeSeguridad valorActualPorcMedidaDeSeg) {
		this.valorActualPorcMedidaDeSeg = valorActualPorcMedidaDeSeg;
	}

	public void setValoresPasadosPorcMedidaDeSeg(List<PorcentajeMedidaDeSeguridad> valoresPasadosPorcMedidaDeSeg) {
		this.valoresPasadosPorcMedidaDeSeg = valoresPasadosPorcMedidaDeSeg;
	}
	
	
}
