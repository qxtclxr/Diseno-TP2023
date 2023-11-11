package tp.entidad;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name="pago")
public class Pago {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idPago")
	private int idPago;
	
	@Column(nullable = false, unique = true) 
	private String numeroRecibo;
	
	@Column(nullable = false)
	private float importeTotal;
	
	//Relaciones
	
	@OneToMany(fetch= FetchType.LAZY, mappedBy="pagoAsociado",cascade = CascadeType.ALL)
	private List<Cuota> cuotasPagadas;
	
	
	
}
