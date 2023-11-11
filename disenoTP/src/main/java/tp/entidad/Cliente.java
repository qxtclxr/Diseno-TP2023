package tp.entidad;

import java.time.LocalDate;

import java.time.LocalDateTime;
import java.util.List;
import tp.entidad.*;
import jakarta.persistence.*;

@Entity
@Table(name="cliente")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idCliente")
	private int idCliente;
	
	@Column(nullable = false, unique = true) 
	private String nroCliente; //puede usarse un naturalid
	
	@Column(nullable = false)
	private String nroDocumento;
	
	@Column(nullable = false)
	private TipoDocumento tipoDocumento;
	
	@Column(nullable = false)
	private String apellido;
	
	@Column(nullable = false)
	private String nombres;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TipoCliente tipoCliente;
	
	@Column(nullable = false)
	private String nroCuil;
	
	@Column(nullable = false)
	private LocalDateTime fechaNacimiento;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TipoIVA condicionIVA;
	
	@Column(nullable = false)
	private String correoElectronico;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private EstadoCivil estadoCivil;
	
	@Column(nullable = false)
	private String profesion;
	
	@Column(nullable = false)
	private LocalDateTime anioRegistro;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Sexo sexo;
	
	@Column(nullable = false)
	private LocalDateTime fechaModificacionEstado;
	
	
	//Relaciones
	
	@OneToMany(fetch= FetchType.LAZY, mappedBy="cliente",cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Poliza> polizas;
	
	@OneToOne
	@JoinColumn
	private Domicilio domicilio;
	
	public Cliente(){
		super();
	}
	
	
	
	
	
	
	
}
