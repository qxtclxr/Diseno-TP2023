package tp.entidad;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.*;

@Entity
@Table(name="factorRiesgoLocalidad")
public class PorcentajeRiesgoLocalidad {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idFactorRiesgoLocalidad")
	private long idFactorRiesgoLocalidad;
	
	@Column(nullable=false)
	private Float valorNumerico;
	
	@Column(nullable=false)
	private LocalDateTime fechaModificacion;
	
	
	//relaciones
	@ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="idLocalidad",referencedColumnName="idLocalidad" ,foreignKey= @ForeignKey(name="FK_loc_en_f_riesgo"))
	private Localidad localidadAsociada;
	
	@ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="idUsuario",referencedColumnName="idUsuario" ,foreignKey= @ForeignKey(name="FK_usuario_en_factor_riesgo_localidad"))
	private Usuario modificadoPor;
	
	@PrePersist
	protected void onCreate() {
		fechaModificacion=LocalDateTime.now();
	}
	
	@PreUpdate
	protected void onUpdate() {
	    fechaModificacion = LocalDateTime.now();
	  }
	
	
	public PorcentajeRiesgoLocalidad() {
		super();
	}

	@Override
	public int hashCode() {
		return Objects.hash(idFactorRiesgoLocalidad);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PorcentajeRiesgoLocalidad other = (PorcentajeRiesgoLocalidad) obj;
		return idFactorRiesgoLocalidad == other.idFactorRiesgoLocalidad;
	}

	@Override
	public String toString() {
		return "PorcentajeRiesgoLocalidad [idFactorRiesgoLocalidad=" + idFactorRiesgoLocalidad + ", valorNumerico="
				+ valorNumerico + ", fechaModificacion=" + fechaModificacion + ", modificadoPor=" + modificadoPor + "]";
	}
	
	//getters and setters
	
	public long getIdFactorRiesgoLocalidad() {
		return idFactorRiesgoLocalidad;
	}

	public Localidad getLocalidadAsociada() {
		return localidadAsociada;
	}

	public void setLocalidadAsociada(Localidad localidadAsociada) {
		this.localidadAsociada = localidadAsociada;
	}

	public Float getValorNumerico() {
		return valorNumerico;
	}

	public LocalDateTime getFechaModificacion() {
		return fechaModificacion;
	}

	public Usuario getModificadoPor() {
		return modificadoPor;
	}

	public void setIdFactorRiesgoLocalidad(long idFactorRiesgoLocalidad) {
		this.idFactorRiesgoLocalidad = idFactorRiesgoLocalidad;
	}

	public void setValorNumerico(Float valorNumerico) {
		this.valorNumerico = valorNumerico;
	}

	public void setFechaModificacion(LocalDateTime fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public void setModificadoPor(Usuario modificadoPor) {
		this.modificadoPor = modificadoPor;
	}
	
	
	

}
