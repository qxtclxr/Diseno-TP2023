package tp.entidad;

import java.time.LocalDateTime;
import java.util.List;

import tp.entidad.*;

import jakarta.persistence.*;

@Entity
@Table(name="cuota")
public class Cuota {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idCuota")
	private int idCuota;
	
	@Column(nullable = false)
	private float premio;
	
	@Column(nullable = false)
	private LocalDateTime fechaVencimiento;
	
	//ver si estos estan bien
	@Column(nullable = false)
	private float prima;
	
	@Column(nullable = false)
	private float derechoEmision;
	
	@Column(nullable = false)
	private float descuentos;
	///
	@Column(nullable = false)
	private float importeTotal;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private EstadoCuota estado;
	
	@Column(nullable = false)
	private float interesAsociadoPorcentual;
	
	//relaciones
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "id_pago", foreignKey= @ForeignKey(name ="PAGO_EN_CUOTA_FK"))
	private Pago pagoAsociado;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_poliza", foreignKey= @ForeignKey(name ="POLIZA_EN_CUOTA_FK"))
	private Poliza polizaAsociada;
	
	
	
}
