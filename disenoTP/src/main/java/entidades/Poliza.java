package entidades;

import java.time.LocalDateTime;

import jakarta.persistence.*;


@Entity
@Table(name="Poliza")

public class Poliza {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idPoliza")
	private int idPoliza;
	
	@Column(nullable = false, unique = true)
	private String nroPoliza; ///este puede ser un natural id
	
	@Column(nullable = false)
	private Float sumaAsegurada;

	@Column(nullable = false)
	private LocalDateTime fechaInicio;
	
	@Column(nullable = false)
	private LocalDateTime fechaFin;
	
	@Column(nullable = false)
	private EstadoPoliza estado;
	
	@Column(nullable = false)
	private Float premio;/// Este hay que chequearlo
	
	@Column(nullable = false)
	private TipoPoliza tipoPoliza;/// Este hay que chequearlo
	
	@Column(nullable = false)
	private LocalDateTime fechaEmision;
	
	
	public Poliza() {
		
	}
	
}
