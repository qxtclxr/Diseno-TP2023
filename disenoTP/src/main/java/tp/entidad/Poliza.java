package tp.entidad;

import java.time.LocalDateTime;
import tp.entidad.*;
import jakarta.persistence.*;


@Entity
@Table(name="poliza")

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
	
	
	//Relaciones
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_cliente", foreignKey= @ForeignKey(name ="POLIZA_CLIENTE_FK"))
	private Cliente cliente;
	
	public Poliza() {
		
	}
	
}
