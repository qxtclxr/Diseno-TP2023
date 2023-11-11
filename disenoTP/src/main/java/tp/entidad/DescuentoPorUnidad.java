package tp.entidad;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name="descuentoPorUnidad")
public class DescuentoPorUnidad {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idDescuentoPorUnidad")
	private int idDescuentoPorUnidad;
	
	@Column(nullable = false)
	private int desdeCantUnidades;
	
	@Column(nullable = false)
	private int hastaCantidadUnidades;
	
	//relaciones
	
	@OneToOne
	@JoinColumn(name="idValorActualDescPorUnidad")
	private PorcentajeDescPorUnidad valorActualDescPorUnidad;
	
	@OneToMany(fetch= FetchType.LAZY, cascade = CascadeType.ALL)
	private List<PorcentajeDescPorUnidad> valoresPasadosDescPorUnidad;
	


}
