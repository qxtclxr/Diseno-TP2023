package tp.dto;

import tp.entidad.*;
import java.util.List;

public class FactorCaracteristicoDTO {
	private PorcentajeAjusteHijos porcentajeHijos;
	private PorcentajeCantSiniestros porcentajeSiniestros;
	private PorcentajeCobertura porcentajeCobertura;
	private PorcentajeEstadisticaRobo porcentajeEstadisticaRobo;
	private PorcentajeRiesgoLocalidad porcentajeRiesgoLocalidad;
	private PorcentajeKMRealizados porcentajeKm;
	private List<PorcentajeMedidaDeSeguridad> porcentajeMedida;
	private PorcentajeDescPorUnidad descuentoPorUnidad;
	private ValorDerechosDeEmision derechosDeEmision;
	private float bonificacionTipoPoliza;
	public PorcentajeAjusteHijos getPorcentajeHijos() {
		return porcentajeHijos;
	}
	public void setPorcentajeHijos(PorcentajeAjusteHijos porcentajeHijos) {
		this.porcentajeHijos = porcentajeHijos;
	}
	public PorcentajeCantSiniestros getPorcentajeSiniestros() {
		return porcentajeSiniestros;
	}
	public void setPorcentajeSiniestros(PorcentajeCantSiniestros porcentajeSiniestros) {
		this.porcentajeSiniestros = porcentajeSiniestros;
	}
	public PorcentajeCobertura getPorcentajeCobertura() {
		return porcentajeCobertura;
	}
	public void setPorcentajeCobertura(PorcentajeCobertura porcentajeCobertura) {
		this.porcentajeCobertura = porcentajeCobertura;
	}
	public PorcentajeEstadisticaRobo getPorcentajeEstadisticaRobo() {
		return porcentajeEstadisticaRobo;
	}
	public void setPorcentajeEstadisticaRobo(PorcentajeEstadisticaRobo porcentajeEstadisticaRobo) {
		this.porcentajeEstadisticaRobo = porcentajeEstadisticaRobo;
	}
	public PorcentajeRiesgoLocalidad getPorcentajeRiesgoLocalidad() {
		return porcentajeRiesgoLocalidad;
	}
	public void setPorcentajeRiesgoLocalidad(PorcentajeRiesgoLocalidad porcentajeRiesgoLocalidad) {
		this.porcentajeRiesgoLocalidad = porcentajeRiesgoLocalidad;
	}
	public PorcentajeKMRealizados getPorcentajeKm() {
		return porcentajeKm;
	}
	public void setPorcentajeKm(PorcentajeKMRealizados porcentajeKm) {
		this.porcentajeKm = porcentajeKm;
	}
	public List<PorcentajeMedidaDeSeguridad> getPorcentajeMedida() {
		return porcentajeMedida;
	}
	public void setPorcentajeMedida(List<PorcentajeMedidaDeSeguridad> porcentajeMedida) {
		this.porcentajeMedida = porcentajeMedida;
	}
	public PorcentajeDescPorUnidad getDescuentoPorUnidad() {
		return descuentoPorUnidad;
	}
	public void setDescuentoPorUnidad(PorcentajeDescPorUnidad descuentoPorUnidad) {
		this.descuentoPorUnidad = descuentoPorUnidad;
	}
	public float getBonificacionTipoPoliza() {
		return bonificacionTipoPoliza;
	}
	public void setBonificacionTipoPoliza(float bonificacionTipoPoliza) {
		this.bonificacionTipoPoliza = bonificacionTipoPoliza;
	}
	public ValorDerechosDeEmision getDerechosDeEmision() {
		return derechosDeEmision;
	}
	public void setDerechosDeEmision(ValorDerechosDeEmision derechosDeEmision) {
		this.derechosDeEmision = derechosDeEmision;
	}
	
	
	
}
