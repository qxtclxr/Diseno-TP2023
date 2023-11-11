package tp.entidad;

import tp.entidad.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.*;


@Entity
@Table(name="hijoDeclarado")
public class HijoDeclarado {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idHijoDeclarado")
	private int idHijoDeclarado;
	
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
