package tp.entidad;

import java.util.Objects;

import jakarta.persistence.*;

@Entity
@Table(name="respuestaSegModificacion")
public class RespuestaSegModificacion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idRespuestaSegModificacion")
	private long idRespuestaSegModificacion;
	/*
	@Column(nullable=false)
	private String idMedida;
	
	@Column(nullable = false)
	private Boolean valorRespuesta;
	
	public RespuestaSegModificacion() {
		super();
	}

	

	@Override
	public int hashCode() {
		return Objects.hash(idRespuestaSegModificacion);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RespuestaSegModificacion other = (RespuestaSegModificacion) obj;
		return idRespuestaSegModificacion == other.idRespuestaSegModificacion;
	}



	@Override
	public String toString() {
		return "RespuestaSegModificacion [idRespuestaSegModificacion=" + idRespuestaSegModificacion + ", idMedida="
				+ idMedida + ", valorRespuesta=" + valorRespuesta + "]";
	}
	//setters and getters

	public long getIdRespuestaSegModificacion() {
		return idRespuestaSegModificacion;
	}

	public String getIdMedida() {
		return idMedida;
	}

	public Boolean getValorRespuesta() {
		return valorRespuesta;
	}

	public void setIdRespuestaSegModificacion(long idRespuestaSegModificacion) {
		this.idRespuestaSegModificacion = idRespuestaSegModificacion;
	}

	public void setIdMedida(String idMedida) {
		this.idMedida = idMedida;
	}

	public void setValorRespuesta(Boolean valorRespuesta) {
		this.valorRespuesta = valorRespuesta;
	}
	*/
	
	
}
