package tp.dto;

import tp.entidad.Domicilio;

public class DomicilioDTO{
	private Long id;
	private String calle;
	private String numero;
	private String piso;
	private String dpto;
	private String codigoPostal;
	private LocalidadDTO localidad;
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append(calle);
		str.append(" "+numero);
		if(piso!=null)
			str.append(" Piso " + piso);
		if(dpto!=null)
			str.append(" Dpto. " + dpto);
		return str.toString();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCalle() {
		return calle;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getPiso() {
		return piso;
	}
	public void setPiso(String piso) {
		this.piso = piso;
	}
	public String getDpto() {
		return dpto;
	}
	public void setDpto(String dpto) {
		this.dpto = dpto;
	}
	public String getCodigoPostal() {
		return codigoPostal;
	}
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	public LocalidadDTO getLocalidad() {
		return localidad;
	}
	public void setLocalidad(LocalidadDTO localidad) {
		this.localidad = localidad;
	}
	
	
}
