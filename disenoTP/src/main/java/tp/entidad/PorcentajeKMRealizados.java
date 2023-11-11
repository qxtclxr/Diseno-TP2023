package tp.entidad;

import java.time.LocalDateTime;


import jakarta.persistence.*;

@Entity
@Table(name="porcentajeKMRealizados")
public class PorcentajeKMRealizados {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idPorcentajeKMRealizados")
	private int idPorcentajeKMRealizados;
	
	@Column(nullable=false)
	private float valorNumerico;
	
	@Column(nullable=false)
	private LocalDateTime fechaModificacion;
	
	
	//relaciones
	
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
		
	}
}
