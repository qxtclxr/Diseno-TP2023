package tp.entidad;


import jakarta.persistence.*;

@Entity
@Table(name="domicilio")
public class Domicilio {
	//atributos
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idDomicilio")
	private int idDomicilio;
	
	@Column(nullable = false)
	private String calle;
	
	@Column(nullable = false)
	private String numero;
	
	@Column
	private String piso;
	
	@Column
	private String dpto;
	
	@Column(nullable = false)
	private String codigoPostal;
	
	//relaciones
	
	@OneToOne(mappedBy="domicilio")
	private Cliente cliente;
	
	
	public Domicilio() {
		
	}
}
