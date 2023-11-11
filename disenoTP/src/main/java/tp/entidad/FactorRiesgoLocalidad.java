package tp.entidad;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name="factorRiesgoLocalidad")
public class FactorRiesgoLocalidad {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idFactorRiesgoLocalidad")
	private int idFactorRiesgoLocalidad;
	
	@Column(nullable=false)
	private Float valorNumerico;
	
	@Column(nullable=false)
	private LocalDateTime fechaModificacion;
	
	
	//relaciones
	
	@ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="idUsuario", foreignKey= @ForeignKey(name="FK_usuario_en_factor_riesgo_localidad"))
	private Usuario modificadoPor;
	
	@PrePersist
	protected void onCreate() {
		fechaModificacion=LocalDateTime.now();
	}
	
	@PreUpdate
	protected void onUpdate() {
	    fechaModificacion = LocalDateTime.now();
	  }
	
	
	public FactorRiesgoLocalidad() {
		
	}

}
