package tp.entidad;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.*;
import tp.entidad.*;

@Entity
@Table(name="derechosDeEmision")
public class DerechosDeEmision {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idDerechosDeEmision")
	private long idDerechosDeEmision;
	
	
	//relaciones
	
	@OneToOne
	@JoinColumn(name="idValorDerechosDeEmision")
	private ValorDerechosDeEmision valorActualDerechosDeEmision;
	
	@OneToMany(fetch= FetchType.LAZY,mappedBy="derechosAsociados", cascade = CascadeType.ALL)
	private List<ValorDerechosDeEmision> valoresPasadosDerechosDeEmision;
		
	public DerechosDeEmision() {
		super();
	}

	@Override
	public int hashCode() {
		return Objects.hash(idDerechosDeEmision);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DerechosDeEmision other = (DerechosDeEmision) obj;
		return idDerechosDeEmision == other.idDerechosDeEmision;
	}

	@Override
	public String toString() {
		return "DerechosDeEmision [idDerechosDeEmision=" + idDerechosDeEmision + ", valorActualDerechosDeEmision="
				+ valorActualDerechosDeEmision + ", valoresPasadosDerechosDeEmision=" + valoresPasadosDerechosDeEmision
				+ "]";
	}

	//getters and setters
	public long getIdDerechosDeEmision() {
		return idDerechosDeEmision;
	}

	public ValorDerechosDeEmision getValorActualDerechosDeEmision() {
		return valorActualDerechosDeEmision;
	}

	public List<ValorDerechosDeEmision> getValoresPasadosDerechosDeEmision() {
		return valoresPasadosDerechosDeEmision;
	}

	public void setIdDerechosDeEmision(long idDerechosDeEmision) {
		this.idDerechosDeEmision = idDerechosDeEmision;
	}

	public void setValorActualDerechosDeEmision(ValorDerechosDeEmision valorActualDerechosDeEmision) {
		this.valorActualDerechosDeEmision = valorActualDerechosDeEmision;
	}

	public void setValoresPasadosDerechosDeEmision(List<ValorDerechosDeEmision> valoresPasadosDerechosDeEmision) {
		this.valoresPasadosDerechosDeEmision = valoresPasadosDerechosDeEmision;
	}

	
	
	
}
