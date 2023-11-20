package tp.entidad;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.*;

@Entity
@Table(name="porcentajeAjusteHijos")
public class PorcentajeAjusteHijos {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idPorcentajeAjusteHijos")
	private long idPorcentajeAjusteHijos;
	
	@Column(nullable=false)
	private Float valorNumerico;
	
	@Column(nullable=false)
	private LocalDateTime fechaModificacion;
	
	//relaciones
	
	@ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="idUsuario",referencedColumnName="idUsuario" ,foreignKey= @ForeignKey(name="FK_usuario_en_porcentaje_cant_hijos"))
	private Usuario modificadoPor;
	
	@ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="idRangoAjusteHijos",referencedColumnName="idRangoAjusteHijos" ,foreignKey= @ForeignKey(name="FK_ajuste_en_por_hijos"))
	private AjusteHijos ajusteAsociado;
	
	@PrePersist
	protected void onCreate() {
		fechaModificacion=LocalDateTime.now();
	}
	
	@PreUpdate
	protected void onUpdate() {
	    fechaModificacion = LocalDateTime.now();
	  }
	
	
	public PorcentajeAjusteHijos() {
		super();
	}
	

	

	@Override
	public int hashCode() {
		return Objects.hash(idPorcentajeAjusteHijos);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PorcentajeAjusteHijos other = (PorcentajeAjusteHijos) obj;
		return idPorcentajeAjusteHijos == other.idPorcentajeAjusteHijos;
	}
	

	@Override
	public String toString() {
		return "PorcentajeAjusteHijos [idPorcentajeAjusteHijos=" + idPorcentajeAjusteHijos + ", valorNumerico="
				+ valorNumerico + ", fechaModificacion=" + fechaModificacion + ", modificadoPor=" + modificadoPor
				+ ", ajusteAsociado=" + ajusteAsociado + "]";
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

	

	public void setValorNumerico(Float valorNumerico) {
		this.valorNumerico = valorNumerico;
	}

	public void setFechaModificacion(LocalDateTime fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public void setModificadoPor(Usuario modificadoPor) {
		this.modificadoPor = modificadoPor;
	}

	public long getIdPorcentajeAjusteHijos() {
		return idPorcentajeAjusteHijos;
	}

	public AjusteHijos getAjusteAsociado() {
		return ajusteAsociado;
	}

	public void setIdPorcentajeAjusteHijos(long idPorcentajeAjusteHijos) {
		this.idPorcentajeAjusteHijos = idPorcentajeAjusteHijos;
	}

	public void setAjusteAsociado(AjusteHijos ajusteAsociado) {
		this.ajusteAsociado = ajusteAsociado;
	}
	
	
	
}
