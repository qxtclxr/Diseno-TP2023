package tp.entidad;

import java.time.LocalDateTime;
import java.util.List;

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
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private EstadoPoliza estado;
	
	@Column(nullable = false)
	private Float premio;/// Este hay que chequearlo
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TipoPoliza tipoPoliza;/// Este hay que chequearlo
	
	@Column(nullable = false)
	private LocalDateTime fechaEmision;
	
	@Column(nullable=false)
	private int cantidadDeSiniestros;
	//ver
	
	
	@Column(nullable=false)
	private long cantKMRealizados;
	//ver
	
	
	
	
	//Relaciones
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_cliente", foreignKey= @ForeignKey(name ="POLIZA_CLIENTE_FK"))
	private Cliente cliente;
	
	//relaciones // Factores de calculo
	
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name="idDomicilioDeRiesgo", foreignKey= @ForeignKey(name="FK_domicilio_de_riesgo_en_poliza"))
	private Localidad domicilioDeRiesgo;
	//ver si esta bien este cascade como en caso pago
	
	
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name="idDescuentoPorUnidad", foreignKey= @ForeignKey(name="FK_desc_unidad_en_poliza"))
	private DescuentoPorUnidad descuentoPorU;
	
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name="idRangoCantSiniestros", foreignKey= @ForeignKey(name="FK_rango_cant_siniestros_en_poliza"))
	private RangoCantSiniestros rangoCantSiniestros;
	
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name="idRangoKMRealizados", foreignKey= @ForeignKey(name="FK_rango_km_realizados_en_poliza"))
	private RangoKMRealizados rangoKMRealizados;
	
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name="idDerechosDeEmision", foreignKey= @ForeignKey(name="FK_derechos_de_emision_en_poliza"))
	private DerechosDeEmision derechosDeEmision;
	
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name="idRangoAjustePorHijo", foreignKey= @ForeignKey(name="FK_rango_ajuste_por_hijo_en_poliza"))
	private RangoAjustePorHijo rangoAjustePorHijo;
	
	//Medidas y respuestas de seguridad
	//ver el eager de los factores
	
	@OneToMany(fetch= FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<RespuestaSeguridad> respuestasSeguridad;
	
	@OneToMany(fetch= FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ModificacionPoliza> modificaciones;
	
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name="idCobertura", foreignKey= @ForeignKey(name="FK_cobertura_en_poliza"))
	private Cobertura cobertura;
	
	@OneToMany(fetch= FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<HijoDeclarado> hijosDeclarados;
	
	//ver Como embeberlo
	private Vehiculo vehiculoAsegurado;
	
	@OneToMany(fetch= FetchType.LAZY, mappedBy="polizaAsociada",cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Cuota> cuotasAsociadas;
	
	
	
	public Poliza() {
		
	}
	
}
