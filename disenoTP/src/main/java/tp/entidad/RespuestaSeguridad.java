package tp.entidad;


import java.util.Objects;

import jakarta.persistence.*;
import tp.entidad.*;

@Deprecated
@Entity
@Table(name="respuestaSeguridad")
public class RespuestaSeguridad {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idRespuestaDeSeguridad")
	private long idRespuestaDeSeguridad;
	/*
	
	@Column(nullable = false)
	private Boolean valorRespuesta;
	
	//ver el cascade de aca
	@ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="idMedidaDeSeguridad", foreignKey= @ForeignKey(name="FK_medida_de_seguridad_en_respuesta"))
	private MedidaDeSeguridad medida;
	
	public RespuestaSeguridad() {
		super();
	}

	@Override
	public int hashCode() {
		return Objects.hash(idRespuestaDeSeguridad, medida, valorRespuesta);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RespuestaSeguridad other = (RespuestaSeguridad) obj;
		return idRespuestaDeSeguridad == other.idRespuestaDeSeguridad && Objects.equals(medida, other.medida)
				&& Objects.equals(valorRespuesta, other.valorRespuesta);
	}

	@Override
	public String toString() {
		return "RespuestaSeguridad [idRespuestaDeSeguridad=" + idRespuestaDeSeguridad + ", valorRespuesta="
				+ valorRespuesta + ", medida=" + medida + "]";
	}
	//setters and getters
	public long getIdRespuestaDeSeguridad() {
		return idRespuestaDeSeguridad;
	}

	public Boolean getValorRespuesta() {
		return valorRespuesta;
	}

	public MedidaDeSeguridad getMedida() {
		return medida;
	}

	public void setIdRespuestaDeSeguridad(long idRespuestaDeSeguridad) {
		this.idRespuestaDeSeguridad = idRespuestaDeSeguridad;
	}

	public void setValorRespuesta(Boolean valorRespuesta) {
		this.valorRespuesta = valorRespuesta;
	}

	public void setMedida(MedidaDeSeguridad medida) {
		this.medida = medida;
	}
	*/
	
	
	
	
}
