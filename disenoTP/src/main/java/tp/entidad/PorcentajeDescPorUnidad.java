package tp.entidad;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.*;

@Entity
@Table(name="porcentajeDescPorUnidad")
public class PorcentajeDescPorUnidad {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idPorcentajeDescPorUnidad")
	private long idPorcentajeDescPorUnidad;
	
	@Column(nullable=false)
	private Float valorNumerico;
	
	@Column(nullable=false)
	private LocalDateTime fechaModificacion;
	
	
	//relaciones
	
	@ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="idUsuario", foreignKey= @ForeignKey(name="FK_usuario_en_porcentaje_desc_por_unidad"))
	private Usuario modificadoPor;
	
	@PrePersist
	protected void onCreate() {
		fechaModificacion=LocalDateTime.now();
	}
	
	@PreUpdate
	protected void onUpdate() {
	    fechaModificacion = LocalDateTime.now();
	  }
	
	
	public PorcentajeDescPorUnidad() {
		super();
	}

	@Override
	public int hashCode() {
		return Objects.hash(fechaModificacion, idPorcentajeDescPorUnidad, valorNumerico);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PorcentajeDescPorUnidad other = (PorcentajeDescPorUnidad) obj;
		return Objects.equals(fechaModificacion, other.fechaModificacion)
				&& idPorcentajeDescPorUnidad == other.idPorcentajeDescPorUnidad
				&& Objects.equals(valorNumerico, other.valorNumerico);
	}

	@Override
	public String toString() {
		return "PorcentajeDescPorUnidad [idPorcentajeDescPorUnidad=" + idPorcentajeDescPorUnidad + ", valorNumerico="
				+ valorNumerico + ", fechaModificacion=" + fechaModificacion + ", modificadoPor=" + modificadoPor + "]";
	}
	//getters and setters

	public long getIdPorcentajeDescPorUnidad() {
		return idPorcentajeDescPorUnidad;
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

	public void setIdPorcentajeDescPorUnidad(long idPorcentajeDescPorUnidad) {
		this.idPorcentajeDescPorUnidad = idPorcentajeDescPorUnidad;
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
