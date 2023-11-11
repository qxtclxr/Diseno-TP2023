package tp.entidad;

import jakarta.persistence.*;

@Entity
@Table(name="provincia")
public class Provincia {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idProvincia")
	private int idProvincia;
	
	
	@Column(nullable = false)
	private String nombre;
	
	
	//relaciones
	
	@ManyToOne(fetch= FetchType.EAGER)
	@JoinColumn(name="idPais", foreignKey= @ForeignKey(name="FK_pais_en_provincia"))
	private Pais pais;
	
	
}
