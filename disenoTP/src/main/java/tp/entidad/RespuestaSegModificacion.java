package tp.entidad;

import jakarta.persistence.*;

@Entity
@Table(name="respuestaSegModificacion")
public class RespuestaSegModificacion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idRespuestaSegModificacion")
	private int idRespuestaSegModificacion;
	
	@Column(nullable=false)
	private String idMedida;
	
	@Column(nullable = false)
	private Boolean valorRespuesta;
	
	
}
