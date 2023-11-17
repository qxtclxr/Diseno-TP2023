package tp.entidad;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.*;

@Entity
@Table(name="porcentajeEstadisticaRobo")
public class PorcentajeEstadisticaRobo {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idPorcEstadRobo")
	private long idPorcentajeEstadisticaRobo;
	
	@Column(nullable=false)
	private Float valorNumerico;
	
	@Column(nullable=false)
	private LocalDateTime fechaModificacion;
	
	
	//relaciones
	
	@ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="idAnioModelo",referencedColumnName="idAnioModelo" ,foreignKey= @ForeignKey(name="FK_anio_porcentajeRobo"))
	private AnioModelo anioModeloAsociado;
	
	@ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="idUsuario",referencedColumnName="idUsuario",foreignKey= @ForeignKey(name="FK_usuario_en_porcentaje_estadistica_robo"))
	private Usuario modificadoPor;
	
	@PrePersist
	protected void onCreate() {
		fechaModificacion=LocalDateTime.now();
	}
	
	@PreUpdate
	protected void onUpdate() {
	    fechaModificacion = LocalDateTime.now();
	  }
	
	
	public PorcentajeEstadisticaRobo() {
		super();
	}

	

	@Override
	public int hashCode() {
		return Objects.hash(idPorcentajeEstadisticaRobo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PorcentajeEstadisticaRobo other = (PorcentajeEstadisticaRobo) obj;
		return idPorcentajeEstadisticaRobo == other.idPorcentajeEstadisticaRobo;
	}

	@Override
	public String toString() {
		return "PorcentajeEstadisticaRobo [idPorcentajeEstadisticaRobo=" + idPorcentajeEstadisticaRobo
				+ ", valorNumerico=" + valorNumerico + ", fechaModificacion=" + fechaModificacion + ", modificadoPor="
				+ modificadoPor + "]";
	}
	//getters and setters

	public long getIdPorcentajeEstadisticaRobo() {
		return idPorcentajeEstadisticaRobo;
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

	public void setIdPorcentajeEstadisticaRobo(long idPorcentajeEstadisticaRobo) {
		this.idPorcentajeEstadisticaRobo = idPorcentajeEstadisticaRobo;
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

	public AnioModelo getAnioModeloAsociado() {
		return anioModeloAsociado;
	}

	public void setAnioModeloAsociado(AnioModelo anioModeloAsociado) {
		this.anioModeloAsociado = anioModeloAsociado;
	}
	
	
}
