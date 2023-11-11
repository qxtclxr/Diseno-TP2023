package tp.entidad;

import jakarta.persistence.*;

@Entity
@Table(name="pais")
public class Pais {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idPais")
	private int idPais;
	
	@Column(nullable = false)
	private String nombre;
	

}
