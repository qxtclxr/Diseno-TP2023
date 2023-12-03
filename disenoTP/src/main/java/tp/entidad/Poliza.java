package tp.entidad;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import tp.entidad.*;
import jakarta.persistence.*;


@Entity
@Table(name="poliza")
public class Poliza {
	/*
	 *Cambie:
	Cobertura
	MedidaDeSeguridad
	AjusteCantHijos
	RangoCantSiniestros
	RangoKMRRealizados
	DescPorUnidad
	FactorRiesgo
	EstadisticaRobo
	ValorVehiculo
	
	
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
	private LocalDate fechaInicio;
	
	@Column(nullable = false)
	private LocalDate fechaFin;
	
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
	private float descuento;

	@Column(nullable=false)
	private float importeTotal;
	
	
	//Relaciones
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idCliente", referencedColumnName="idCliente" ,foreignKey= @ForeignKey(name ="POLIZA_CLIENTE_FK"))
	private Cliente cliente;
	
	
	//relaciones // Factores de calculo
	
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.MERGE)
	@JoinColumn(name="idFactorRiesgo", referencedColumnName="idFactorRiesgoLocalidad",foreignKey= @ForeignKey(name="FK_factor_riesgo_en_poliza"))
	private PorcentajeRiesgoLocalidad factorRiesgoLoc;
	//ver si esta bien este cascade como en caso pago
	
	
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.MERGE)
	@JoinColumn(name="idPorcentajeDescPorUnidad",referencedColumnName="idPorcentajeDescPorUnidad", foreignKey= @ForeignKey(name="FK_porc_unidad_en_poliza"))
	private PorcentajeDescPorUnidad porcDescuentoPorU;
	
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.MERGE)
	@JoinColumn(name="idPorcCantSin",referencedColumnName="idPorcCantSin", foreignKey= @ForeignKey(name="FK_por_siniestros_en_poliza"))
	private PorcentajeCantSiniestros porcCantSiniestros;
	
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.MERGE)
	@JoinColumn(name="idRangoKMRealizados",referencedColumnName="idPorcentajeKMRealizados", foreignKey= @ForeignKey(name="FK_porc_km_realizados_en_poliza"))
	private PorcentajeKMRealizados porcKMRealizados;
	
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.MERGE)
	@JoinColumn(name="idValorDerechosDeEmision",referencedColumnName="idValorDerechosDeEmision", foreignKey= @ForeignKey(name="FK_valor_derechos_en_poliza"))
	private ValorDerechosDeEmision valorDerechosDeEmision;
	
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.MERGE)
	@JoinColumn(name="idPorcAjusteHijos", referencedColumnName="idPorcentajeAjusteHijos",foreignKey= @ForeignKey(name="FK_porc_hijo_en_poliza"))
	private PorcentajeAjusteHijos porcAjustePorHijo;
	
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.MERGE)
	@JoinColumn(name="idPorcEstadRobo", referencedColumnName="idPorcEstadRobo",foreignKey= @ForeignKey(name="FK_porc_robo_en_poliza"))
	private PorcentajeEstadisticaRobo porcEstRobo;
	
	//ver el eager de los factores
	
	//Este puede traer problemas
	@ManyToMany(fetch= FetchType.EAGER,cascade = {CascadeType.MERGE})
	private List<PorcentajeMedidaDeSeguridad> porcMedidaSeguridad;
	
	@OneToMany(fetch= FetchType.LAZY, mappedBy="idModificacionPoliza",cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ModificacionPoliza> modificaciones;
	
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.MERGE)
	@JoinColumn(name="porCobertura", referencedColumnName="idPorcentajeCobertura", foreignKey= @ForeignKey(name="FK_Por_cobertura_Poliza"))
	private PorcentajeCobertura porcCobertura;
	
	@OneToMany(fetch= FetchType.LAZY,mappedBy="idHijoDeclarado", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<HijoDeclarado> hijosDeclarados;
	
	//ver Como embeberlo
	@Embedded
	private Vehiculo vehiculoAsegurado;
	
	@OneToMany(fetch= FetchType.LAZY, mappedBy="polizaAsociada",cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Cuota> cuotasAsociadas;
	
	@ManyToOne(fetch=FetchType.LAZY,cascade = CascadeType.MERGE)
	@JoinColumn(name="productor", referencedColumnName="idUsuario", foreignKey= @ForeignKey(name="FK_productor_poliza") )
	private Usuario productorAsociado;
	
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



	
	
	//getters and setters


	



	public long getIdPoliza() {
		return idPoliza;
	}


/*
	@Override
	public String toString() {
		return "Poliza [idPoliza=" + idPoliza + ", nroPoliza=" + nroPoliza + ", sumaAsegurada=" + sumaAsegurada
				+ ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", estado=" + estado + ", premio="
				+ premio + ", tipoPoliza=" + tipoPoliza + ", fechaEmision=" + fechaEmision + ", importeTotal="
				+ importeTotal + ", cliente=" + cliente + ", factorRiesgoLoc=" + factorRiesgoLoc
				+ ", porcDescuentoPorU=" + porcDescuentoPorU + ", porcCantSiniestros=" + porcCantSiniestros
				+ ", porcKMRealizados=" + porcKMRealizados + ", valorDerechosDeEmision=" + valorDerechosDeEmision
				+ ", porcAjustePorHijo=" + porcAjustePorHijo + ", porcEstRobo=" + porcEstRobo + ", porcMedidaSeguridad="
				+ porcMedidaSeguridad + ", modificaciones=" + modificaciones + ", porcCobertura=" + porcCobertura
				+ ", hijosDeclarados=" + hijosDeclarados + ", vehiculoAsegurado=" + vehiculoAsegurado
				+ ", cuotasAsociadas=" + cuotasAsociadas + ", productorAsociado=" + productorAsociado + "]";
	}*/



	public String getNroPoliza() {
		return nroPoliza;
	}



	public Float getSumaAsegurada() {
		return sumaAsegurada;
	}



	public LocalDate getFechaInicio() {
		return fechaInicio;
	}


	public LocalDate getFechaFin() {
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



	



	public float getImporteTotal() {
		return importeTotal;
	}



	public Usuario getProductorAsociado() {
		return productorAsociado;
	}



	public void setImporteTotal(float importeTotal) {
		this.importeTotal = importeTotal;
	}



	public void setProductorAsociado(Usuario productorAsociado) {
		this.productorAsociado = productorAsociado;
	}



	public Cliente getCliente() {
		return cliente;
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



	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}


	public void setFechaFin(LocalDate fechaFin) {
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



	



	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}



	

	public void setModificaciones(List<ModificacionPoliza> modificaciones) {
		this.modificaciones = modificaciones;
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



	public PorcentajeRiesgoLocalidad getFactorRiesgoLoc() {
		return factorRiesgoLoc;
	}



	public PorcentajeDescPorUnidad getPorcDescuentoPorU() {
		return porcDescuentoPorU;
	}



	public PorcentajeCantSiniestros getPorcCantSiniestros() {
		return porcCantSiniestros;
	}



	public PorcentajeKMRealizados getPorcKMRealizados() {
		return porcKMRealizados;
	}



	public ValorDerechosDeEmision getValorDerechosDeEmision() {
		return valorDerechosDeEmision;
	}



	public PorcentajeAjusteHijos getPorcAjustePorHijo() {
		return porcAjustePorHijo;
	}



	public PorcentajeEstadisticaRobo getPorcEstRobo() {
		return porcEstRobo;
	}



	public List<PorcentajeMedidaDeSeguridad> getPorcMedidaSeguridad() {
		return porcMedidaSeguridad;
	}



	public PorcentajeCobertura getPorcCobertura() {
		return porcCobertura;
	}



	public void setFactorRiesgoLoc(PorcentajeRiesgoLocalidad factorRiesgoLoc) {
		this.factorRiesgoLoc = factorRiesgoLoc;
	}



	public void setPorcDescuentoPorU(PorcentajeDescPorUnidad porcDescuentoPorU) {
		this.porcDescuentoPorU = porcDescuentoPorU;
	}



	public void setPorcCantSiniestros(PorcentajeCantSiniestros porcCantSiniestros) {
		this.porcCantSiniestros = porcCantSiniestros;
	}



	public void setPorcKMRealizados(PorcentajeKMRealizados porcKMRealizados) {
		this.porcKMRealizados = porcKMRealizados;
	}



	public void setValorDerechosDeEmision(ValorDerechosDeEmision valorDerechosDeEmision) {
		this.valorDerechosDeEmision = valorDerechosDeEmision;
	}



	public void setPorcAjustePorHijo(PorcentajeAjusteHijos porcAjustePorHijo) {
		this.porcAjustePorHijo = porcAjustePorHijo;
	}



	public void setPorcEstRobo(PorcentajeEstadisticaRobo porcEstRobo) {
		this.porcEstRobo = porcEstRobo;
	}



	public void setPorcMedidaSeguridad(List<PorcentajeMedidaDeSeguridad> porcMedidaSeguridad) {
		this.porcMedidaSeguridad = porcMedidaSeguridad;
	}



	public void setPorcCobertura(PorcentajeCobertura porcCobertura) {
		this.porcCobertura = porcCobertura;
	}



	public float getDescuento() {
		return descuento;
	}



	public void setDescuento(float descuento) {
		this.descuento = descuento;
	}
	
	
	
	
}
