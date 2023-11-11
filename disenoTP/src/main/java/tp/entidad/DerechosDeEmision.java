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
	
	
	private ValorDerechosDeEmision valorActualDerechosDeEmision;
	
	private List<ValorDerechosDeEmision> valoresPasadosDerechosDeEmision;
		
	
}
