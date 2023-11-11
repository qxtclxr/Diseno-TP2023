package tp.entidad;

import jakarta.persistence.*;

@Entity
@Table(name="permiso")
public class Permiso {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idPermiso")
	private int idPermiso;
	
	@Column(nullable=false)
	private String permisoAsignado;
	
	@Column(nullable=false)
	private Boolean estado;
	
	
	

}
