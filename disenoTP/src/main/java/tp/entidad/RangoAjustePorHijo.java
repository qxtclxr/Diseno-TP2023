package tp.entidad;

import java.util.List;

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
	
	@OneToOne
	@JoinColumn(name="idValorActualPorcentajeCantHijos")
	private PorcentajeCantHijos valorActualPorcentajeCantHijos;
	
	@OneToMany(fetch= FetchType.LAZY, cascade = CascadeType.ALL)
	private List<PorcentajeCantHijos> valoresPasadosPorcentajeCantHijos;
	

}
