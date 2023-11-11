package tp.entidad;

import java.util.List;

import jakarta.persistence.*;
import tp.entidad.*;

@Entity
@Table(name="localidad")
public class Localidad {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idLocalidad")
	private int idLocalidad;
	
	@Column(nullable = false)
	private String nombre;
	
	
	//relaciones
	
	@ManyToOne(fetch= FetchType.EAGER)
	@JoinColumn(name="idProvincia", foreignKey= @ForeignKey(name="FK_provincia_en_localidad"))
	private Provincia provincia;
	
	@OneToOne
	@JoinColumn(name="idValorActualFactorRiesgo")
	private FactorRiesgoLocalidad valorActualFactorRiesgo;
	
	@OneToMany(fetch= FetchType.LAZY, cascade = CascadeType.ALL)
	private List<FactorRiesgoLocalidad> valoresPasadosFactorRiesgo;
	
	
	public Localidad() {
		
	}
	
}
