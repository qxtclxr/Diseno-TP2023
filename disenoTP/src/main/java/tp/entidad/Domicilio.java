package tp.entidad;


import java.util.Objects;

import org.hibernate.query.sqm.FetchClauseType;
import tp.entidad.*;

import jakarta.persistence.*;

@Entity
@Table(name="domicilio")
public class Domicilio {
	//atributos
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idDomicilio")
	private long idDomicilio;
	
	@Column(nullable = false)
	private String calle;
	
	@Column(nullable = false)
	private String numero;
	
	@Column
	private String piso;
	
	@Column
	private String dpto;
	
	@Column(nullable = false)
	private String codigoPostal;
	
	//relaciones
	
	@OneToOne(mappedBy="domicilio")
	private Cliente cliente;
	
	@ManyToOne(fetch= FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="idLocalidad", referencedColumnName="idLocalidad",foreignKey= @ForeignKey(name="FK_localidad_en_domicilio"))
	private Localidad localidad;
	//podria faltar un cascade
	
	
	public Domicilio() {
		
	}


	@Override
	public String toString() {
		return "Domicilio [idDomicilio=" + idDomicilio + ", calle=" + calle + ", numero=" + numero + ", piso=" + piso
				+ ", dpto=" + dpto + ", codigoPostal=" + codigoPostal + ", cliente=" + cliente + ", localidad="
				+ localidad + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(idDomicilio);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Domicilio other = (Domicilio) obj;
		return idDomicilio == other.idDomicilio;
	}
	//getters and setters

	public long getIdDomicilio() {
		return idDomicilio;
	}


	public String getCalle() {
		return calle;
	}


	public String getNumero() {
		return numero;
	}


	public String getPiso() {
		return piso;
	}


	public String getDpto() {
		return dpto;
	}


	public String getCodigoPostal() {
		return codigoPostal;
	}


	public Cliente getCliente() {
		return cliente;
	}


	public Localidad getLocalidad() {
		return localidad;
	}


	public void setIdDomicilio(long idDomicilio) {
		this.idDomicilio = idDomicilio;
	}


	public void setCalle(String calle) {
		this.calle = calle;
	}


	public void setNumero(String numero) {
		this.numero = numero;
	}


	public void setPiso(String piso) {
		this.piso = piso;
	}


	public void setDpto(String dpto) {
		this.dpto = dpto;
	}


	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}
	
	
}
