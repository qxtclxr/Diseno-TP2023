package tp.entidad;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import tp.entidad.*;
import jakarta.persistence.*;


@Entity
@Table(name="poliza")
public class Poliza {
	/*
	 * ver la unicidad de patente motor y chasis
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idPoliza")
	private long idPoliza;
	
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
	@JoinColumn(name = "idCliente", referencedColumnName="idCliente" ,foreignKey= @ForeignKey(name ="POLIZA_CLIENTE_FK"))
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
	
	//Este puede traer problemas
	@ManyToMany(fetch= FetchType.EAGER,cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private List<PorcentajeMedidaDeSeguridad> porcMedidaSeguridad;
	
	@OneToMany(fetch= FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ModificacionPoliza> modificaciones;
	
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name="porCobertura", referencedColumnName="idPorcentajeCobertura", foreignKey= @ForeignKey(name="FK_Por_cobertura_Poliza"))
	private PorcentajeCobertura cobertura;
	
	@OneToMany(fetch= FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<HijoDeclarado> hijosDeclarados;
	
	//ver Como embeberlo
	@Embedded
	private Vehiculo vehiculoAsegurado;
	
	@OneToMany(fetch= FetchType.LAZY, mappedBy="polizaAsociada",cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Cuota> cuotasAsociadas;
	
	
	
	public Poliza() {
		super();
	}



	@Override
	public int hashCode() {
		return Objects.hash(idPoliza);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Poliza other = (Poliza) obj;
		return idPoliza == other.idPoliza;
	}



	@Override
	public String toString() {
		return "Poliza [idPoliza=" + idPoliza + ", nroPoliza=" + nroPoliza + ", sumaAsegurada=" + sumaAsegurada
				+ ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", estado=" + estado + ", premio="
				+ premio + ", tipoPoliza=" + tipoPoliza + ", fechaEmision=" + fechaEmision + ", cantidadDeSiniestros="
				+ cantidadDeSiniestros + ", cantKMRealizados=" + cantKMRealizados + ", cliente=" + cliente
				+ ", domicilioDeRiesgo=" + domicilioDeRiesgo + ", descuentoPorU=" + descuentoPorU
				+ ", rangoCantSiniestros=" + rangoCantSiniestros + ", rangoKMRealizados=" + rangoKMRealizados
				+ ", derechosDeEmision=" + derechosDeEmision + ", rangoAjustePorHijo=" + rangoAjustePorHijo
				+ ", respuestasSeguridad=" + respuestasSeguridad + ", modificaciones=" + modificaciones + ", cobertura="
				+ cobertura + ", hijosDeclarados=" + hijosDeclarados + ", vehiculoAsegurado=" + vehiculoAsegurado
				+ ", cuotasAsociadas=" + cuotasAsociadas + "]";
	}
	
	//getters and setters


	public long getIdPoliza() {
		return idPoliza;
	}



	public String getNroPoliza() {
		return nroPoliza;
	}



	public Float getSumaAsegurada() {
		return sumaAsegurada;
	}



	public LocalDateTime getFechaInicio() {
		return fechaInicio;
	}



	public LocalDateTime getFechaFin() {
		return fechaFin;
	}



	public EstadoPoliza getEstado() {
		return estado;
	}



	public Float getPremio() {
		return premio;
	}



	public TipoPoliza getTipoPoliza() {
		return tipoPoliza;
	}



	public LocalDateTime getFechaEmision() {
		return fechaEmision;
	}



	public int getCantidadDeSiniestros() {
		return cantidadDeSiniestros;
	}



	public long getCantKMRealizados() {
		return cantKMRealizados;
	}



	public Cliente getCliente() {
		return cliente;
	}



	public Localidad getDomicilioDeRiesgo() {
		return domicilioDeRiesgo;
	}



	public DescuentoPorUnidad getDescuentoPorU() {
		return descuentoPorU;
	}



	public RangoCantSiniestros getRangoCantSiniestros() {
		return rangoCantSiniestros;
	}



	public RangoKMRealizados getRangoKMRealizados() {
		return rangoKMRealizados;
	}



	public DerechosDeEmision getDerechosDeEmision() {
		return derechosDeEmision;
	}



	public RangoAjustePorHijo getRangoAjustePorHijo() {
		return rangoAjustePorHijo;
	}



	public List<RespuestaSeguridad> getRespuestasSeguridad() {
		return respuestasSeguridad;
	}



	public List<ModificacionPoliza> getModificaciones() {
		return modificaciones;
	}



	



	public List<HijoDeclarado> getHijosDeclarados() {
		return hijosDeclarados;
	}



	public Vehiculo getVehiculoAsegurado() {
		return vehiculoAsegurado;
	}



	public List<Cuota> getCuotasAsociadas() {
		return cuotasAsociadas;
	}



	public void setIdPoliza(long idPoliza) {
		this.idPoliza = idPoliza;
	}



	public void setNroPoliza(String nroPoliza) {
		this.nroPoliza = nroPoliza;
	}



	public void setSumaAsegurada(Float sumaAsegurada) {
		this.sumaAsegurada = sumaAsegurada;
	}



	public void setFechaInicio(LocalDateTime fechaInicio) {
		this.fechaInicio = fechaInicio;
	}



	public void setFechaFin(LocalDateTime fechaFin) {
		this.fechaFin = fechaFin;
	}



	public void setEstado(EstadoPoliza estado) {
		this.estado = estado;
	}



	public void setPremio(Float premio) {
		this.premio = premio;
	}



	public void setTipoPoliza(TipoPoliza tipoPoliza) {
		this.tipoPoliza = tipoPoliza;
	}



	public void setFechaEmision(LocalDateTime fechaEmision) {
		this.fechaEmision = fechaEmision;
	}



	public void setCantidadDeSiniestros(int cantidadDeSiniestros) {
		this.cantidadDeSiniestros = cantidadDeSiniestros;
	}



	public void setCantKMRealizados(long cantKMRealizados) {
		this.cantKMRealizados = cantKMRealizados;
	}



	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}



	public void setDomicilioDeRiesgo(Localidad domicilioDeRiesgo) {
		this.domicilioDeRiesgo = domicilioDeRiesgo;
	}



	public void setDescuentoPorU(DescuentoPorUnidad descuentoPorU) {
		this.descuentoPorU = descuentoPorU;
	}



	public void setRangoCantSiniestros(RangoCantSiniestros rangoCantSiniestros) {
		this.rangoCantSiniestros = rangoCantSiniestros;
	}



	public void setRangoKMRealizados(RangoKMRealizados rangoKMRealizados) {
		this.rangoKMRealizados = rangoKMRealizados;
	}



	public void setDerechosDeEmision(DerechosDeEmision derechosDeEmision) {
		this.derechosDeEmision = derechosDeEmision;
	}



	public void setRangoAjustePorHijo(RangoAjustePorHijo rangoAjustePorHijo) {
		this.rangoAjustePorHijo = rangoAjustePorHijo;
	}



	public void setRespuestasSeguridad(List<RespuestaSeguridad> respuestasSeguridad) {
		this.respuestasSeguridad = respuestasSeguridad;
	}



	public void setModificaciones(List<ModificacionPoliza> modificaciones) {
		this.modificaciones = modificaciones;
	}



	



	public PorcentajeCobertura getCobertura() {
		return cobertura;
	}



	public void setCobertura(PorcentajeCobertura cobertura) {
		this.cobertura = cobertura;
	}



	public void setHijosDeclarados(List<HijoDeclarado> hijosDeclarados) {
		this.hijosDeclarados = hijosDeclarados;
	}



	public void setVehiculoAsegurado(Vehiculo vehiculoAsegurado) {
		this.vehiculoAsegurado = vehiculoAsegurado;
	}



	public void setCuotasAsociadas(List<Cuota> cuotasAsociadas) {
		this.cuotasAsociadas = cuotasAsociadas;
	}
	
	
	
	
}
