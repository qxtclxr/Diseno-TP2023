package tp.entidad;

import java.util.List;


import java.util.Objects;

import jakarta.persistence.*;
import tp.entidad.*;

@Entity
@Table(name="AjusteHijos")
public class AjusteHijos {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idRangoAjusteHijos")
	private long idRangoAjusteHijos;
	
	

	
	//relaciones
	
	@OneToOne
	@JoinColumn(name="idValorActualPorcAjusteHijos")
	private PorcentajeAjusteHijos valorActualPorcentajeCantHijos;
	
	@OneToMany(fetch= FetchType.LAZY,mappedBy="ajusteAsociado", cascade = CascadeType.ALL)
	private List<PorcentajeAjusteHijos> valoresPasadosAjusteHijos;
	
	public AjusteHijos() {
		super();
	}

	

	

	
	

	@Override
	public int hashCode() {
		return Objects.hash(idRangoAjusteHijos);
	}






	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AjusteHijos other = (AjusteHijos) obj;
		return idRangoAjusteHijos == other.idRangoAjusteHijos;
	}






	@Override
	public String toString() {
		return "AjusteHijos [idRangoAjusteHijos=" + idRangoAjusteHijos + ", valorActualPorcentajeCantHijos="
				+ valorActualPorcentajeCantHijos + ", valoresPasadosAjusteHijos=" + valoresPasadosAjusteHijos + "]";
	}


	//setters and getters
	





	public long getIdRangoAjusteHijos() {
		return idRangoAjusteHijos;
	}








	public PorcentajeAjusteHijos getValorActualPorcentajeCantHijos() {
		return valorActualPorcentajeCantHijos;
	}








	public List<PorcentajeAjusteHijos> getValoresPasadosAjusteHijos() {
		return valoresPasadosAjusteHijos;
	}








	public void setIdRangoAjusteHijos(long idRangoAjusteHijos) {
		this.idRangoAjusteHijos = idRangoAjusteHijos;
	}








	public void setValorActualPorcentajeCantHijos(PorcentajeAjusteHijos valorActualPorcentajeCantHijos) {
		this.valorActualPorcentajeCantHijos = valorActualPorcentajeCantHijos;
	}








	public void setValoresPasadosAjusteHijos(List<PorcentajeAjusteHijos> valoresPasadosAjusteHijos) {
		this.valoresPasadosAjusteHijos = valoresPasadosAjusteHijos;
	}

	




	
	
	

}
