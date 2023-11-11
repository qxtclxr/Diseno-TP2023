package tp.entidad;

import java.util.List;

import jakarta.persistence.*;
import tp.entidad.*;

@Entity
@Table(name="rangoKMRealizados")
public class RangoKMRealizados {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idRangoKMRealizados")
	private int idRangoKMRealizados;
	
	
	@Column(nullable = false)
	private long desdeKMRealizados;
	
	@Column(nullable = false)
	private long hastaKMRealizados;
	
	//relaciones
	private PorcentajeKMRealizados valorActualPorcentajeKMRealizados;
	
	private List<PorcentajeKMRealizados> valoresPasadosPorcentajeKMRealizados;
	
	
}
