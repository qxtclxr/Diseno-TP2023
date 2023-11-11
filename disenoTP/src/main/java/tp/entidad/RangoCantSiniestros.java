package tp.entidad;

import java.util.List;

import jakarta.persistence.*;
import tp.entidad.*;

@Entity
@Table(name="rangoCantSiniestros")
public class RangoCantSiniestros {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idRangoCantSiniestros")
	private int idRangoCantSiniestros;
	
	
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
	
}
