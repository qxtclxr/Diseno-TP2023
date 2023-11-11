package tp.entidad;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name="rol")
public class Rol {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idRol")
	private int idRol;
	
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
	
	
}
