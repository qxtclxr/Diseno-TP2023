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
	
	@Column(nullable=false,unique=true)
	private String nickname;
	
	@Column(nullable=false)
	private String contrasenia;
	
	@Column(nullable=false)
	private String nombre;
	
	@Column(nullable=false)
	private String apellido;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	private TipoDocumento tipoDocumento;
	
	
	
	
	//relaciones
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name="idRol",referencedColumnName="idRol", foreignKey= @ForeignKey(name="FK_id_rol_en_usuario"))
	private Rol rolActual;
	
	@ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="sucursal",referencedColumnName="idSucursal", foreignKey= @ForeignKey(name="FK_surcusal_en_user"))
	private Sucursal sucursalAsociada;
	
	public Usuario() {
		super();
	}

	

	@Override
	public int hashCode() {
		return Objects.hash(idUsuario);
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
		return idUsuario == other.idUsuario;
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

	public String getNickname() {
		return nickname;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}



	public Sucursal getSucursalAsociada() {
		return sucursalAsociada;
	}



	public void setSucursalAsociada(Sucursal sucursalAsociada) {
		this.sucursalAsociada = sucursalAsociada;
	}
	
	
	
	
	
}
