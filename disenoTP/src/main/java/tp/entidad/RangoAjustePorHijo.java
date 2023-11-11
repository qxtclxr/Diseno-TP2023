package tp.entidad;

import jakarta.persistence.*;
import tp.entidad.*;

@Entity
@Table(name="rangoAjustePorHijo")
public class RangoAjustePorHijo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idRangoAjustePorHijo")
	private int idRangoAjustePorHijo;
	
	
	@Column(nullable = false)
	private int desdeCantHijos;
	
	@Column(nullable = false)
	private int hastaCantHijos;
	
	//relaciones
	
	
	private PorcentajeCantHijos valorActualPorcentajeCantHijos;
	
	private List<PorcentajeCantHijos> valoresPasadosPorcentajeCantHijos;
	

}
