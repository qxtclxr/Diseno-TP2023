package tp.entidad;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name="valorDerechosDeEmision")
public class ValorDerechosDeEmision {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idValorDerechosDeEmision")
	private int idValorDerechosDeEmision;
	
	@Column(nullable=false)
	private Float valorNumerico;
	
	@Column(nullable=false)
	private LocalDateTime fechaModificacion;
	
	
	//relaciones
	
	@ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="idUsuario", foreignKey= @ForeignKey(name="FK_usuario_en_derechos_de_emision"))
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
		
	}
}
