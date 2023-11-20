package tp.dto;

import java.time.LocalDate;
import java.util.List;
import tp.entidad.TipoPoliza;

public class PolizaDTO {
	
	private float sumaAsegurada;
	private LocalDate fechaInicio;
	private LocalDate fechaFin;
	private TipoPoliza tipoPoliza;
	private List<HijoDeclaradoDTO> hijosDeclarados;
	private List<CuotaDTO> cuotas;
	private List<MedidaDeSeguridadDTO> medidas;
	private VehiculoDTO vehiculo;
	private RangoKMRealizadosDTO kmRealizados;
	private RangoCantSiniestrosDTO cantidadSiniestros;
	private ClienteDTO cliente;
	private LocalidadDTO localidad;
	private CoberturaDTO cobertura;
	private FactorCaracteristicoDTO factores;
	private float importeTotal;
	private float premio;
	private float descuento;
	
	public float getSumaAsegurada() {
		return sumaAsegurada;
	}
	public void setSumaAsegurada(float sumaAsegurada) {
		this.sumaAsegurada = sumaAsegurada;
	}
	public LocalDate getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public LocalDate getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}
	public TipoPoliza getTipoPoliza() {
		return tipoPoliza;
	}
	public void setTipoPoliza(TipoPoliza tipoPoliza) {
		this.tipoPoliza = tipoPoliza;
	}
	public List<HijoDeclaradoDTO> getHijosDeclarados() {
		return hijosDeclarados;
	}
	public void setHijosDeclarados(List<HijoDeclaradoDTO> hijosDeclarados) {
		this.hijosDeclarados = hijosDeclarados;
	}
	public List<CuotaDTO> getCuotas() {
		return cuotas;
	}
	public void setCuotas(List<CuotaDTO> cuotas) {
		this.cuotas = cuotas;
	}
	public VehiculoDTO getVehiculo() {
		return vehiculo;
	}
	public void setVehiculo(VehiculoDTO vehiculo) {
		this.vehiculo = vehiculo;
	}
	public RangoKMRealizadosDTO getKmRealizados() {
		return kmRealizados;
	}
	public void setKmRealizados(RangoKMRealizadosDTO kmRealizados) {
		this.kmRealizados = kmRealizados;
	}
	public RangoCantSiniestrosDTO getCantidadSiniestros() {
		return cantidadSiniestros;
	}
	public void setCantidadSiniestros(RangoCantSiniestrosDTO cantidadSiniestros) {
		this.cantidadSiniestros = cantidadSiniestros;
	}
	public ClienteDTO getCliente() {
		return cliente;
	}
	public void setCliente(ClienteDTO cliente) {
		this.cliente = cliente;
	}
	public LocalidadDTO getLocalidad() {
		return localidad;
	}
	public void setLocalidad(LocalidadDTO localidad) {
		this.localidad = localidad;
	}
	public CoberturaDTO getCobertura() {
		return cobertura;
	}
	public void setCobertura(CoberturaDTO cobertura) {
		this.cobertura = cobertura;
	}
	public float getImporteTotal() {
		return importeTotal;
	}
	public void setImporteTotal(float importeTotal) {
		this.importeTotal = importeTotal;
	}
	public List<MedidaDeSeguridadDTO> getMedidas() {
		return medidas;
	}
	public void setMedidas(List<MedidaDeSeguridadDTO> medidas) {
		this.medidas = medidas;
	}
	public float getPremio() {
		return premio;
	}
	public void setPremio(float premio) {
		this.premio = premio;
	}
	public float getDescuento() {
		return descuento;
	}
	public void setDescuento(float descuento) {
		this.descuento = descuento;
	}
	public FactorCaracteristicoDTO getFactores() {
		return factores;
	}
	public void setFactores(FactorCaracteristicoDTO factores) {
		this.factores = factores;
	}
}
