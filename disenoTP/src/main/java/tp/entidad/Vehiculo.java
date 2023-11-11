package tp.entidad;

import java.time.LocalDate;

import jakarta.persistence.*;
@Embeddable
public class Vehiculo {
	
	@Column(nullable=false)
	private LocalDate anioVehiculo;
	
	@Column(nullable=false)
	private String Motor;
	
	@Column(nullable=false)
	private String chasis;
	
	@Column(nullable=false)
	private String patente;
	
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name="idModelo", foreignKey= @ForeignKey(name="FK_vehiculo_en_modelo"))
	private Modelo tieneModelo;
}
