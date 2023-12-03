package tp.dto;

import java.util.Map;
import javafx.util.Pair;

public class FactorCaracteristicoDTO {
	//Los pares <Long,Float> almacenan <idFactor,valorFactor>
	private Pair<Long,Float> porcentajeHijos;
	private Pair<Long,Float> porcentajeSiniestros;
	private Pair<Long,Float> porcentajeCobertura;
	private Pair<Long,Float> porcentajeEstadisticaRobo;
	private Pair<Long,Float> porcentajeRiesgoLocalidad;
	private Pair<Long,Float> porcentajeKm;
	//Este es un Map porque almacena cero, uno o mas pares.
	private Map<Long,Float> porcentajeMedida;
	private Pair<Long,Float> descuentoPorUnidad;
	private Pair<Long,Float> derechosDeEmision;
	private float bonificacionTipoPoliza;
	
	public Pair<Long, Float> getPorcentajeHijos() {
		return porcentajeHijos;
	}
	public void setPorcentajeHijos(Pair<Long, Float> porcentajeHijos) {
		this.porcentajeHijos = porcentajeHijos;
	}
	public Pair<Long, Float> getPorcentajeSiniestros() {
		return porcentajeSiniestros;
	}
	public void setPorcentajeSiniestros(Pair<Long, Float> porcentajeSiniestros) {
		this.porcentajeSiniestros = porcentajeSiniestros;
	}
	public Pair<Long, Float> getPorcentajeCobertura() {
		return porcentajeCobertura;
	}
	public void setPorcentajeCobertura(Pair<Long, Float> porcentajeCobertura) {
		this.porcentajeCobertura = porcentajeCobertura;
	}
	public Pair<Long, Float> getPorcentajeEstadisticaRobo() {
		return porcentajeEstadisticaRobo;
	}
	public void setPorcentajeEstadisticaRobo(Pair<Long, Float> porcentajeEstadisticaRobo) {
		this.porcentajeEstadisticaRobo = porcentajeEstadisticaRobo;
	}
	public Pair<Long, Float> getPorcentajeRiesgoLocalidad() {
		return porcentajeRiesgoLocalidad;
	}
	public void setPorcentajeRiesgoLocalidad(Pair<Long, Float> porcentajeRiesgoLocalidad) {
		this.porcentajeRiesgoLocalidad = porcentajeRiesgoLocalidad;
	}
	public Pair<Long, Float> getPorcentajeKm() {
		return porcentajeKm;
	}
	public void setPorcentajeKm(Pair<Long, Float> porcentajeKm) {
		this.porcentajeKm = porcentajeKm;
	}
	public Map<Long, Float> getPorcentajeMedida() {
		return porcentajeMedida;
	}
	public void setPorcentajeMedida(Map<Long, Float> porcentajeMedida) {
		this.porcentajeMedida = porcentajeMedida;
	}
	public Pair<Long, Float> getDescuentoPorUnidad() {
		return descuentoPorUnidad;
	}
	public void setDescuentoPorUnidad(Pair<Long, Float> descuentoPorUnidad) {
		this.descuentoPorUnidad = descuentoPorUnidad;
	}
	public Pair<Long, Float> getDerechosDeEmision() {
		return derechosDeEmision;
	}
	public void setDerechosDeEmision(Pair<Long, Float> derechosDeEmision) {
		this.derechosDeEmision = derechosDeEmision;
	}
	public float getBonificacionTipoPoliza() {
		return bonificacionTipoPoliza;
	}
	public void setBonificacionTipoPoliza(float bonificacionTipoPoliza) {
		this.bonificacionTipoPoliza = bonificacionTipoPoliza;
	}
}
