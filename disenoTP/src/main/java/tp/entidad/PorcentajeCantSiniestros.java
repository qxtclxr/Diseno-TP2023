package tp.entidad;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.*;

@Entity
@Table(name="porcentajeCantSiniestros")
public class PorcentajeCantSiniestros {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idPorcCantSin")
	private long idPorcCantSin;
	
	@Column(nullable=false)
	private Float valorNumerico;
	
	@Column(nullable=false)
	private LocalDateTime fechaModificacion;
	
	
	//relaciones
	
	@ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="idRangoCantSiniestros",referencedColumnName="idRangoCantSiniestros" ,foreignKey= @ForeignKey(name="FK_rango_en_cant_siniestros"))
	private RangoCantSiniestros rangoAsociado;
	
	@ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="idUsuario", foreignKey= @ForeignKey(name="FK_usuario_en_porcentaje_cant_siniestros"))
	private Usuario modificadoPor;
	
	@PrePersist
	protected void onCreate() {
		fechaModificacion=LocalDateTime.now();
	}
	
	@PreUpdate
	protected void onUpdate() {
	    fechaModificacion = LocalDateTime.now();
	  }
	
	
	public PorcentajeCantSiniestros() {
		super();
	}

	@Override
	public int hashCode() {
		return Objects.hash(fechaModificacion, idPorcentajeCantSiniestros, valorNumerico);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PorcentajeCantSiniestros other = (PorcentajeCantSiniestros) obj;
		return Objects.equals(fechaModificacion, other.fechaModificacion)
				&& idPorcentajeCantSiniestros == other.idPorcentajeCantSiniestros
				&& Objects.equals(valorNumerico, other.valorNumerico);
	}

	@Override
	public String toString() {
		return "PorcentajeCantSiniestros [idPorcentajeCantSiniestros=" + idPorcentajeCantSiniestros + ", valorNumerico="
				+ valorNumerico + ", fechaModificacion=" + fechaModificacion + ", modificadoPor=" + modificadoPor + "]";
	}
	//getters and setters

	public long getIdPorcentajeCantSiniestros() {
		return idPorcentajeCantSiniestros;
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

	public void setIdPorcentajeCantSiniestros(long idPorcentajeCantSiniestros) {
		this.idPorcentajeCantSiniestros = idPorcentajeCantSiniestros;
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
