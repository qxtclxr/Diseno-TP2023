package tp.entidad;
import java.util.List;

import jakarta.persistence.*;
import tp.entidad.*;

@Entity
@Table(name="derechosDeEmision")
public class DerechosDeEmision {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idDerechosDeEmision")
	private int idDerechosDeEmision;
	
	
	//relaciones
	
	@OneToOne
	@JoinColumn(name="idValorDerechosDeEmision")
	private ValorDerechosDeEmision valorActualDerechosDeEmision;
	
	@OneToMany(fetch= FetchType.LAZY, cascade = CascadeType.ALL)
	private List<ValorDerechosDeEmision> valoresPasadosDerechosDeEmision;
		
	
}
