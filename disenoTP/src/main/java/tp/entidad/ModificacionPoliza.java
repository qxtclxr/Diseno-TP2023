package tp.entidad;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name="modificacionPoliza")
public class ModificacionPoliza {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idModificacionPoliza")
	private int idModificacionPoliza;
	
	@Column
	private LocalDate anioVehiculo;
	
	@Column
	private String patente;
	
	@Column
	private String motor;
	
	@Column
	private String chasis;
	
	@Column(nullable=false)
	private LocalDateTime fechaModificacion;
	
	//relaciones
	
	@OneToMany(fetch= FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<RespuestaSegModificacion> respuestasModificadas;
	
	//Relaciones simplificadas
	@Column
	private int idRangoKMrealizados;
	@Column
	private int idRangoCantSiniestros;
	
	@Column
	private int idCobertura;
	
	@OneToMany(fetch= FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<HijoDeclaradoModificacion> hijosDeclaradosModificados;
	
	@ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="idUsuario", foreignKey= @ForeignKey(name="FK_usuario_en_modificacion"))
	private Usuario modificadoPor;
	
	
	@PrePersist
	protected void onCreate() {
		fechaModificacion=LocalDateTime.now();
	}
	
	@PreUpdate
	protected void onUpdate() {
	    fechaModificacion = LocalDateTime.now();
	  }
	public ModificacionPoliza() {
		
	}
}
