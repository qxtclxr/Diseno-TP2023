package tp.entidad;


import jakarta.persistence.*;
import tp.entidad.*;

@Entity
@Table(name="respuestaSeguridad")
public class RespuestaSeguridad {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idRespuestaDeSeguridad")
	private int idRespuestaDeSeguridad;
	
	
	@Column(nullable = false)
	private Boolean valorRespuesta;
	
	//ver el cascade de aca
	@ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="idMedidaDeSeguridad", foreignKey= @ForeignKey(name="FK_medida_de_seguridad_en_respuesta"))
	private MedidaDeSeguridad medida;
	
	
	
}
