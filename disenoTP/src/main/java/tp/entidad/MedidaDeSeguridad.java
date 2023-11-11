package tp.entidad;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name="medidaDeSeguridad")
public class MedidaDeSeguridad {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idMedidaDeSeguridad")
	private int idMedidaDeSeguridad;
	
	private String pregunta;
	
	//relaciones
	
	@OneToOne
	@JoinColumn(name="idValorActualPorcentajeMedidaDeSeguridad")
	private PorcentajeMedidaDeSeguridad valorActualPorcentajeMedidaDeSeguridad;
	
	@OneToMany(fetch= FetchType.LAZY, cascade = CascadeType.ALL)
	private List<PorcentajeMedidaDeSeguridad> valoresPasadosPorcentajeMedidaDeSeguridad;
	
}
