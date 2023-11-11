package tp.entidad;

import java.time.LocalTime;

import jakarta.persistence.*;

@Entity
@Table(name="modelo")
public class Modelo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idModelo")
	private long idModelo;
	
	@Column(nullable=false)
	private String nombre;
	
	@Column(nullable=false)
	private String descripcion;
	
	@Column(nullable=false)
	private LocalTime fabricadoDesde;
	
	@Column(nullable=false)
	private LocalTime fabricadoHasta;
	
	//relaciones
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name="idMarca", foreignKey= @ForeignKey(name="FK_marca_en_modelo"))
	private Marca marca;
	
	
	private PorcentajeEstadisticaRobo valorActualPorcentajeEstadisticaRobo;
	
	private List<PorcentajeEstadisticaRobo> valoresPasadosPorcentajeEstadisticaRobo;
	
	
	
	public Modelo() {
		
	}
}
