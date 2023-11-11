package tp.entidad;

import jakarta.persistence.*;
import tp.entidad.*;

@Entity
@Table(name="cobertura")
public class Cobertura {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idCobertura")
	private int idCobertura;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TipoCobertura nombre;
	
	@Column
	private String descripcion;
	
	//relaciones
	
	
	private PorcentajeCobertura valorActualPorcentajeCobertura;
	
	private List<PorcentajeCobertura> valoresPasadosPorcentajeCobertura;
		
	
	
	

}
