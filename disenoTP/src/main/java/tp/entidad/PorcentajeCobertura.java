package tp.entidad;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.*;

@Entity
@Table(name="porcentajeCobertura")
public class PorcentajeCobertura {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idPorcentajeCobertura")
	private long idPorcentajeCobertura;
	
	@Column(nullable=false)
	private Float valorNumerico;
	
	@Column(nullable=false)
	private LocalDateTime fechaModificacion;
	
	
	//relaciones
	
	@ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="idCobertura",referencedColumnName="idCobertura" ,foreignKey= @ForeignKey(name="FK_cob_en_por_cobertura"))
	private Cobertura coberturaAsociada;
	
	@ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="idUsuario",referencedColumnName="idUsuario" ,foreignKey= @ForeignKey(name="FK_usuario_en_porcentaje_cobertura"))
	private Usuario modificadoPor;
	
	
	@PrePersist
	protected void onCreate() {
		fechaModificacion=LocalDateTime.now();
	}
	
	@PreUpdate
	protected void onUpdate() {
	    fechaModificacion = LocalDateTime.now();
	  }
	
	
	public PorcentajeCobertura() {
		super();
	}

	

	@Override
	public int hashCode() {
		return Objects.hash(idPorcentajeCobertura);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PorcentajeCobertura other = (PorcentajeCobertura) obj;
		return idPorcentajeCobertura == other.idPorcentajeCobertura;
	}

	@Override
	public String toString() {
		return "PorcentajeCobertura [idPorcentajeCobertura=" + idPorcentajeCobertura + ", valorNumerico="
				+ valorNumerico + ", fechaModificacion=" + fechaModificacion + ", modificadoPor=" + modificadoPor + "]";
	}
	//getters and setters

	public long getIdPorcentajeCobertura() {
		return idPorcentajeCobertura;
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

	public void setIdPorcentajeCobertura(long idPorcentajeCobertura) {
		this.idPorcentajeCobertura = idPorcentajeCobertura;
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

	public Cobertura getCoberturaAsociada() {
		return coberturaAsociada;
	}

	public void setCoberturaAsociada(Cobertura coberturaAsociada) {
		this.coberturaAsociada = coberturaAsociada;
	}
	
	
	
	

}
