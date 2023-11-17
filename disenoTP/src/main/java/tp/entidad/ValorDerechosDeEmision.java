package tp.entidad;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.*;

@Entity
@Table(name="valorDerechosDeEmision")
public class ValorDerechosDeEmision {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idValorDerechosDeEmision")
	private long idValorDerechosDeEmision;
	
	@Column(nullable=false)
	private Float valorNumerico;
	
	@Column(nullable=false)
	private LocalDateTime fechaModificacion;
	
	
	//relaciones
	@ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="idDerechosDeEmision",referencedColumnName="idDerechosDeEmision" ,foreignKey= @ForeignKey(name="FK_der_en_v_der"))
	private DerechosDeEmision derechosAsociados;
	
	@ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="idUsuario",referencedColumnName="idUsuario", foreignKey= @ForeignKey(name="FK_usuario_en_derechos_de_emision"))
	private Usuario modificadoPor;
	
	@PrePersist
	protected void onCreate() {
		fechaModificacion=LocalDateTime.now();
	}
	
	@PreUpdate
	protected void onUpdate() {
	    fechaModificacion = LocalDateTime.now();
	  }
	
	
	public ValorDerechosDeEmision() {
		super();
	}

	

	@Override
	public int hashCode() {
		return Objects.hash(idValorDerechosDeEmision);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ValorDerechosDeEmision other = (ValorDerechosDeEmision) obj;
		return idValorDerechosDeEmision == other.idValorDerechosDeEmision;
	}

	@Override
	public String toString() {
		return "ValorDerechosDeEmision [idValorDerechosDeEmision=" + idValorDerechosDeEmision + ", valorNumerico="
				+ valorNumerico + ", fechaModificacion=" + fechaModificacion + ", modificadoPor=" + modificadoPor + "]";
	}
	//setters and getters

	public long getIdValorDerechosDeEmision() {
		return idValorDerechosDeEmision;
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

	public void setIdValorDerechosDeEmision(long idValorDerechosDeEmision) {
		this.idValorDerechosDeEmision = idValorDerechosDeEmision;
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

	public DerechosDeEmision getDerechosAsociados() {
		return derechosAsociados;
	}

	public void setDerechosAsociados(DerechosDeEmision derechosAsociados) {
		this.derechosAsociados = derechosAsociados;
	}
	
	
	
}
