package tp.entidad;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.*;

@Entity
@Table(name="porcentajeMedidaDeSeguridad")
public class PorcentajeMedidaDeSeguridad {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idPorcMedidaDeSeg")
	private long idPorcentajeMedidaDeSeguridad;
	
	@Column(nullable=false)
	private Float valorNumerico;
	
	@Column(nullable=false)
	private LocalDateTime fechaModificacion;
	
	
	//relaciones
	
	@ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="idUsuario",referencedColumnName="idUsuario", foreignKey= @ForeignKey(name="FK_usuario_en_porcentaje_medida_de_seguridad"))
	private Usuario modificadoPor;
	
	@ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="idMedidaDeSeguridad",referencedColumnName="idMedidaDeSeguridad" ,foreignKey= @ForeignKey(name="FK_medida_porc_medida"))
	private MedidaDeSeguridad medidaAsociada;
	
	
	
	
	@PrePersist
	protected void onCreate() {
		fechaModificacion=LocalDateTime.now();
	}
	
	@PreUpdate
	protected void onUpdate() {
	    fechaModificacion = LocalDateTime.now();
	  }
	
	
	public PorcentajeMedidaDeSeguridad() {
		super();
	}

	

	@Override
	public int hashCode() {
		return Objects.hash(idPorcentajeMedidaDeSeguridad);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PorcentajeMedidaDeSeguridad other = (PorcentajeMedidaDeSeguridad) obj;
		return idPorcentajeMedidaDeSeguridad == other.idPorcentajeMedidaDeSeguridad;
	}

	@Override
	public String toString() {
		return "PorcentajeMedidaDeSeguridad [idPorcentajeMedidaDeSeguridad=" + idPorcentajeMedidaDeSeguridad
				+ ", valorNumerico=" + valorNumerico + ", fechaModificacion=" + fechaModificacion + ", modificadoPor="
				+ modificadoPor + "]";
	}
	//getters and setters

	public long getIdPorcentajeMedidaDeSeguridad() {
		return idPorcentajeMedidaDeSeguridad;
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

	public void setIdPorcentajeMedidaDeSeguridad(long idPorcentajeMedidaDeSeguridad) {
		this.idPorcentajeMedidaDeSeguridad = idPorcentajeMedidaDeSeguridad;
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

	public MedidaDeSeguridad getMedidaAsociada() {
		return medidaAsociada;
	}

	public void setMedidaAsociada(MedidaDeSeguridad medidaAsociada) {
		this.medidaAsociada = medidaAsociada;
	}
	
}
