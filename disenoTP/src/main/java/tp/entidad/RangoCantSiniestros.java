package tp.entidad;

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
	
	
	private PorcentajeCantSiniestros valorActualPorcentajeCantSiniestros;
	
	private List<PorcentajeCantSiniestros> valoresPasadosPorcentajeCantSiniestros;
	
}
