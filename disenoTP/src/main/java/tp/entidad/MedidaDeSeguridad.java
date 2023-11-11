package tp.entidad;

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
	
	
	private PorcentajeMedidaDeSeguridad valorActualPorcentajeMedidaDeSeguridad;
	
	private List<PorcentajeMedidaDeSeguridad> valoresPasadosPorcentajeMedidaDeSeguridad;
	
}
