package tp.entidad;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name="porcentajeMedidaDeSeguridad")
public class PorcentajeMedidaDeSeguridad {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idPorcentajeMedidaDeSeguridad")
	private int idPorcentajeMedidaDeSeguridad;
	
	@Column(nullable=false)
	private Float valorNumerico;
	
	@Column(nullable=false)
	private LocalDateTime fechaModificacion;
	
	
	//relaciones
	
	@ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="idUsuario", foreignKey= @ForeignKey(name="FK_usuario_en_porcentaje_medida_de_seguridad"))
	private Usuario modificadoPor;
	
	@PrePersist
	protected void onCreate() {
		fechaModificacion=LocalDateTime.now();
	}
	
	@PreUpdate
	protected void onUpdate() {
	    fechaModificacion = LocalDateTime.now();
	  }
	
	
	public PorcentajeMedidaDeSeguridad() {
		
	}
}
