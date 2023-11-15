package tp.entidad;

import java.util.Objects;

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
	
	public Usuario() {
		super();
	}

	@Override
	public int hashCode() {
		return Objects.hash(apellido, idUsuario, nombre, rolActual, tipoDocumento);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(apellido, other.apellido) && idUsuario == other.idUsuario
				&& Objects.equals(nombre, other.nombre) && Objects.equals(rolActual, other.rolActual)
				&& tipoDocumento == other.tipoDocumento;
	}

	@Override
	public String toString() {
		return "Usuario [idUsuario=" + idUsuario + ", nombre=" + nombre + ", apellido=" + apellido + ", tipoDocumento="
				+ tipoDocumento + ", rolActual=" + rolActual + "]";
	}
	//setters and getters
	public long getIdUsuario() {
		return idUsuario;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}

	public Rol getRolActual() {
		return rolActual;
	}

	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public void setRolActual(Rol rolActual) {
		this.rolActual = rolActual;
	}
	
	
	
}
