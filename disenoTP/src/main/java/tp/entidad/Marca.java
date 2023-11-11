package tp.entidad;

import jakarta.persistence.*;

@Entity
@Table(name="marca")
public class Marca {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idMarca")
	private int idMarca;
	
	@Column(nullable=false)
	private String nombre;
}
