package tp.entidad;


import java.time.LocalDate;
import tp.entidad.*;

import jakarta.persistence.*;

@Entity
@Table(name="hijoDeclaradoModificacion")
public class HijoDeclaradoModificacion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idHijoDeclaradoModificacion")
	private int idHijoDeclaradoModificacion;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Sexo sexo;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private EstadoCivil estadoCivil;
	
	@Column(nullable = true)
	private String documento;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = true)
	private TipoDocumento tipoDocumento;
	
	@Column(nullable=false)
	private LocalDate fechaNacimiento;
	
}
