package tp.entidad;

import java.util.List;

import jakarta.persistence.*;
import tp.entidad.*;

@Entity
@Table(name="cobertura")
public class Cobertura {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idCobertura")
	private int idCobertura;
	
	
	@Column(nullable = false)
	private String tipoCobertura;
	
	@Column
	private String descripcion;
	
	//relaciones
	
	@OneToOne
	@JoinColumn(name="idValorActualPorcentajeCobertura")
	private PorcentajeCobertura valorActualPorcentajeCobertura;
	
	@OneToMany(fetch= FetchType.LAZY, cascade = CascadeType.ALL)
	private List<PorcentajeCobertura> valoresPasadosPorcentajeCobertura;
		
	
	
	

}
