package tp.entidad;

import jakarta.persistence.*;

@Entity
@Table(name="usuario")
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idUsuario")
	private long idUsuario;
	
	@Column(nullable=false)
	private String nombre;
	
	@Column(nullable=false)
	private String apellido;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	private TipoDocumento tipoDocumento;
	
	
	//relaciones
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name="idRol", foreignKey= @ForeignKey(name="FK_id_rol_en_usuario"))
	private Rol rolActual;
	
	
}
