package tp.entidad;

import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.*;

@Entity
@Table(name="modelo")
public class Modelo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idModelo")
	private long idModelo;
	
	@Column(nullable=false, unique=true)
	private String nombreModelo;
	
	@Column(nullable=false)
	private String descripcion;
	
	@Column(nullable=false)
	private LocalTime fabricadoDesde;
	
	@Column(nullable=false)
	private LocalTime fabricadoHasta;
	
	//ver si puede nullearse o no
	@Column 
	private float valor;
	
	//relaciones
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name="idMarca", foreignKey= @ForeignKey(name="FK_marca_en_modelo"))
	private Marca marca;
	
	@OneToOne
	@JoinColumn(name="idValorActualPorcentajeEstadisticaRobo")
	private PorcentajeEstadisticaRobo valorActualPorcentajeEstadisticaRobo;
	
	@OneToMany(fetch= FetchType.LAZY, cascade = CascadeType.ALL)
	private List<PorcentajeEstadisticaRobo> valoresPasadosPorcentajeEstadisticaRobo;
	
	
	
	public Modelo() {
		super();
	}



	@Override
	public int hashCode() {
		return Objects.hash(idModelo);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Modelo other = (Modelo) obj;
		return idModelo == other.idModelo;
	}



	@Override
	public String toString() {
		return "Modelo [idModelo=" + idModelo + ", nombre=" + nombreModelo + ", descripcion=" + descripcion
				+ ", fabricadoDesde=" + fabricadoDesde + ", fabricadoHasta=" + fabricadoHasta + ", marca=" + marca
				+ ", valorActualPorcentajeEstadisticaRobo=" + valorActualPorcentajeEstadisticaRobo
				+ ", valoresPasadosPorcentajeEstadisticaRobo=" + valoresPasadosPorcentajeEstadisticaRobo + "]";
	}
	//getters and setters


	public long getIdModelo() {
		return idModelo;
	}







	public String getDescripcion() {
		return descripcion;
	}



	public LocalTime getFabricadoDesde() {
		return fabricadoDesde;
	}



	public LocalTime getFabricadoHasta() {
		return fabricadoHasta;
	}



	public Marca getMarca() {
		return marca;
	}



	public PorcentajeEstadisticaRobo getValorActualPorcentajeEstadisticaRobo() {
		return valorActualPorcentajeEstadisticaRobo;
	}



	public List<PorcentajeEstadisticaRobo> getValoresPasadosPorcentajeEstadisticaRobo() {
		return valoresPasadosPorcentajeEstadisticaRobo;
	}



	public void setIdModelo(long idModelo) {
		this.idModelo = idModelo;
	}



	


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}



	public void setFabricadoDesde(LocalTime fabricadoDesde) {
		this.fabricadoDesde = fabricadoDesde;
	}



	public void setFabricadoHasta(LocalTime fabricadoHasta) {
		this.fabricadoHasta = fabricadoHasta;
	}



	public void setMarca(Marca marca) {
		this.marca = marca;
	}



	public void setValorActualPorcentajeEstadisticaRobo(PorcentajeEstadisticaRobo valorActualPorcentajeEstadisticaRobo) {
		this.valorActualPorcentajeEstadisticaRobo = valorActualPorcentajeEstadisticaRobo;
	}



	public void setValoresPasadosPorcentajeEstadisticaRobo(
			List<PorcentajeEstadisticaRobo> valoresPasadosPorcentajeEstadisticaRobo) {
		this.valoresPasadosPorcentajeEstadisticaRobo = valoresPasadosPorcentajeEstadisticaRobo;
	}



	public String getNombreModelo() {
		return nombreModelo;
	}



	public float getValor() {
		return valor;
	}



	public void setNombreModelo(String nombreModelo) {
		this.nombreModelo = nombreModelo;
	}



	public void setValor(float valor) {
		this.valor = valor;
	}
	
	
	
}
