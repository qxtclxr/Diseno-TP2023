package tp.entidad;

import java.util.List;

import jakarta.persistence.*;
@Entity
@Table(name="anioModelo")
public class AnioModelo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idAnioModelo")
	private long idAnioModelo;
	
	//ver los nullable de estas columnas
	@Column
	private float valorVehiculo;
	
	@Column(nullable=false)
	private int anio;
	
	//relaciones
	
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name="idModelo",referencedColumnName="idModelo" ,foreignKey= @ForeignKey(name="FK_modelo_anio"))
	private Modelo tieneModelo;
	
	@OneToOne
	@JoinColumn(name="idValorActualPorcentajeEstadisticaRobo")
	private PorcentajeEstadisticaRobo valorActualPorcentajeEstadisticaRobo;
	
	@OneToMany(fetch= FetchType.LAZY,mappedBy="anioModeloAsociado", cascade = CascadeType.ALL)
	private List<PorcentajeEstadisticaRobo> valoresPasadosPorcentajeEstadisticaRobo;
	
	
}
