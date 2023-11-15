package tp.entidad;

import java.util.Objects;

import jakarta.persistence.*;

@Entity
@Table(name="permiso")
public class Permiso {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idPermiso")
	private long idPermiso;
	
	@Column(nullable=false)
	private String permisoAsignado;
	
	@Column(nullable=false)
	private Boolean estado;
	
	public Permiso() {
		super();
	}

	@Override
	public int hashCode() {
		return Objects.hash(idPermiso);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Permiso other = (Permiso) obj;
		return idPermiso == other.idPermiso;
	}

	@Override
	public String toString() {
		return "Permiso [idPermiso=" + idPermiso + ", permisoAsignado=" + permisoAsignado + ", estado=" + estado + "]";
	}
	
	//getters and setters
	public long getIdPermiso() {
		return idPermiso;
	}

	public String getPermisoAsignado() {
		return permisoAsignado;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setIdPermiso(long idPermiso) {
		this.idPermiso = idPermiso;
	}

	public void setPermisoAsignado(String permisoAsignado) {
		this.permisoAsignado = permisoAsignado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}
	
	
	

}
