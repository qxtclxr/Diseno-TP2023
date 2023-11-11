package tp.entidad;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@Table(name="porcentajeCobertura")
public class PorcentajeCobertura {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idPorcentajeCobertura")
	private int idPorcentajeCobertura;
	
	@Column(nullable=false)
	private Float valorNumerico;
	
	@Column(nullable=false)
	private LocalDateTime fechaModificacion;
	
	
	//relaciones
	
	@ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="idUsuario", foreignKey= @ForeignKey(name="FK_usuario_en_porcentaje_cobertura"))
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
		
	}
	

}
