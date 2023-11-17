package tp.entidad;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.*;

@Entity
@Table(name="porcentajeKMRealizados")
public class PorcentajeKMRealizados {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idPorcentajeKMRealizados")
	private long idPorcentajeKMRealizados;
	
	@Column(nullable=false)
	private float valorNumerico;
	
	@Column(nullable=false)
	private LocalDateTime fechaModificacion;
	
	
	//relaciones
	@ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="idRangoKMRealizados",referencedColumnName="idRangoKMRealizados" ,foreignKey= @ForeignKey(name="FK_rango_en_por_km"))
	private RangoKMRealizados rangoAsociado;
	
	@ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="idUsuario", foreignKey= @ForeignKey(name="FK_usuario_en_porcentaje_KM_realizados"))
	private Usuario modificadoPor;
	
	@PrePersist
	protected void onCreate() {
		fechaModificacion=LocalDateTime.now();
	}
	
	@PreUpdate
	protected void onUpdate() {
	    fechaModificacion = LocalDateTime.now();
	  }
	
	
	public PorcentajeKMRealizados() {
		super();
	}

	@Override
	public int hashCode() {
		return Objects.hash(fechaModificacion, idPorcentajeKMRealizados, valorNumerico);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PorcentajeKMRealizados other = (PorcentajeKMRealizados) obj;
		return Objects.equals(fechaModificacion, other.fechaModificacion)
				&& idPorcentajeKMRealizados == other.idPorcentajeKMRealizados
				&& Float.floatToIntBits(valorNumerico) == Float.floatToIntBits(other.valorNumerico);
	}

	@Override
	public String toString() {
		return "PorcentajeKMRealizados [idPorcentajeKMRealizados=" + idPorcentajeKMRealizados + ", valorNumerico="
				+ valorNumerico + ", fechaModificacion=" + fechaModificacion + ", modificadoPor=" + modificadoPor + "]";
	}
	//getters and setters

	public long getIdPorcentajeKMRealizados() {
		return idPorcentajeKMRealizados;
	}

	public float getValorNumerico() {
		return valorNumerico;
	}

	public LocalDateTime getFechaModificacion() {
		return fechaModificacion;
	}

	public Usuario getModificadoPor() {
		return modificadoPor;
	}

	public void setIdPorcentajeKMRealizados(long idPorcentajeKMRealizados) {
		this.idPorcentajeKMRealizados = idPorcentajeKMRealizados;
	}

	public void setValorNumerico(float valorNumerico) {
		this.valorNumerico = valorNumerico;
	}

	public void setFechaModificacion(LocalDateTime fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public void setModificadoPor(Usuario modificadoPor) {
		this.modificadoPor = modificadoPor;
	}
	
}
