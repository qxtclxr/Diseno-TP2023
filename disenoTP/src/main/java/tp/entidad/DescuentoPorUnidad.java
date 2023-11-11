package tp.entidad;

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
	
	private PorcentajeDescPorUnidad valorActualDescPorUnidad;
	
	private List<PorcentajeDescPorUnidad> valoresPasadosDescPorUnidad;
	


}
