package tp.dto;

import java.time.LocalDateTime;
import java.util.List;
import tp.entidad.TipoPoliza;

public class PolizaDTO {
	
	private float sumaAsegurada;
	private LocalDateTime fechaInicio;
	private LocalDateTime fechaFin;
	private TipoPoliza tipoPoliza;
	private List<HijoDeclaradoDTO> hijosDeclarados;
	private List<CuotaDTO> cuotas;
	private List<RespuestaSeguridadDTO> respuestasSeguridad;
	private VehiculoDTO vehiculo;
	private AjusteKmRealizadosDTO ajusteKmRealizados;
	private CantidadSiniestrosDTO cantidadSiniestros;
	private ClienteDTO cliente;
	private LocalidadDTO localidad;
	private CoberturaDTO cobertura;
	
	public float getSumaAsegurada() {
		return sumaAsegurada;
	}
	public void setSumaAsegurada(float sumaAsegurada) {
		this.sumaAsegurada = sumaAsegurada;
	}
	public LocalDateTime getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(LocalDateTime fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public LocalDateTime getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(LocalDateTime fechaFin) {
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
	public List<RespuestaSeguridadDTO> getRespuestasSeguridad() {
		return respuestasSeguridad;
	}
	public void setRespuestasSeguridad(List<RespuestaSeguridadDTO> respuestasSeguridad) {
		this.respuestasSeguridad = respuestasSeguridad;
	}
	public VehiculoDTO getVehiculo() {
		return vehiculo;
	}
	public void setVehiculo(VehiculoDTO vehiculo) {
		this.vehiculo = vehiculo;
	}
	public AjusteKmRealizadosDTO getAjusteKmRealizados() {
		return ajusteKmRealizados;
	}
	public void setAjusteKmRealizados(AjusteKmRealizadosDTO ajusteKmRealizados) {
		this.ajusteKmRealizados = ajusteKmRealizados;
	}
	public CantidadSiniestrosDTO getCantidadSiniestros() {
		return cantidadSiniestros;
	}
	public void setCantidadSiniestros(CantidadSiniestrosDTO cantidadSiniestros) {
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
	
	
}
