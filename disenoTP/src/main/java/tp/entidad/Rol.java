package tp.entidad;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.*;

@Entity
@Table(name="rol")
public class Rol {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idRol")
	private long idRol;
	
	@Column(nullable=false)
	private LocalDate fechaAlta;
	
	@Column(nullable=true)
	private LocalDate fechaBaja;
	
	@Column(nullable=false)
	private String nombreRol;
	
	//relaciones
	
	@OneToMany(fetch= FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Permiso> permisos;
	//ver esta relacion si no deberia ser de n a m
	
	@PrePersist
	protected void onCreate() {
		fechaAlta=LocalDate.now();
	}
	
	public Rol() {
		super();
	}

	@Override
	public int hashCode() {
		return Objects.hash(fechaAlta, fechaBaja, idRol, nombreRol, permisos);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rol other = (Rol) obj;
		return Objects.equals(fechaAlta, other.fechaAlta) && Objects.equals(fechaBaja, other.fechaBaja)
				&& idRol == other.idRol && Objects.equals(nombreRol, other.nombreRol)
				&& Objects.equals(permisos, other.permisos);
	}

	@Override
	public String toString() {
		return "Rol [idRol=" + idRol + ", fechaAlta=" + fechaAlta + ", fechaBaja=" + fechaBaja + ", nombreRol="
				+ nombreRol + ", permisos=" + permisos + "]";
	}
	//setters and getters

	public long getIdRol() {
		return idRol;
	}

	public LocalDate getFechaAlta() {
		return fechaAlta;
	}

	public LocalDate getFechaBaja() {
		return fechaBaja;
	}

	public String getNombreRol() {
		return nombreRol;
	}

	public List<Permiso> getPermisos() {
		return permisos;
	}

	public void setIdRol(long idRol) {
		this.idRol = idRol;
	}

	public void setFechaAlta(LocalDate fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public void setFechaBaja(LocalDate fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

	public void setNombreRol(String nombreRol) {
		this.nombreRol = nombreRol;
	}

	public void setPermisos(List<Permiso> permisos) {
		this.permisos = permisos;
	}
	
	
	
}
