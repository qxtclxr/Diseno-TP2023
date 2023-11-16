package tp.entidad;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.*;

@Entity
@Table(name="pago")
public class Pago {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idPago")
	private long idPago;
	
	@Column(nullable = false, unique = true) 
	private String numeroRecibo;
	
	@Column(nullable = false)
	private float importeTotal;
	
	//Relaciones
	
	@OneToMany(fetch= FetchType.LAZY, mappedBy="pagoAsociado",cascade = CascadeType.ALL)
	private List<Cuota> cuotasPagadas;
	
	public Pago() {
		super();
	}

	@Override
	public int hashCode() {
		return Objects.hash(idPago);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pago other = (Pago) obj;
		return idPago == other.idPago;
	}

	@Override
	public String toString() {
		return "Pago [idPago=" + idPago + ", numeroRecibo=" + numeroRecibo + ", importeTotal=" + importeTotal
				+ ", cuotasPagadas=" + cuotasPagadas + "]";
	}
	//getters and setters
	public long getIdPago() {
		return idPago;
	}

	public String getNumeroRecibo() {
		return numeroRecibo;
	}

	public float getImporteTotal() {
		return importeTotal;
	}

	public List<Cuota> getCuotasPagadas() {
		return cuotasPagadas;
	}

	public void setIdPago(long idPago) {
		this.idPago = idPago;
	}

	public void setNumeroRecibo(String numeroRecibo) {
		this.numeroRecibo = numeroRecibo;
	}

	public void setImporteTotal(float importeTotal) {
		this.importeTotal = importeTotal;
	}

	public void setCuotasPagadas(List<Cuota> cuotasPagadas) {
		this.cuotasPagadas = cuotasPagadas;
	}
	
	
	
}
