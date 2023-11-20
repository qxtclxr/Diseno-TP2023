package tp.entidad;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import tp.entidad.*;

import jakarta.persistence.*;

@Entity
@Table(name="cuota")
public class Cuota {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idCuota")
	private long idCuota;
	
	@Column(nullable = false)
	private float premio;
	
	@Column(nullable = false)
	private LocalDate fechaVencimiento;
	
	//ver si estos estan bien
	@Column(nullable = false)
	private float prima;
	
	@Column(nullable = false)
	private float derechoEmision;
	
	@Column(nullable = false)
	private float descuentos;
	///
	@Column(nullable = false)
	private float importeTotal;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private EstadoCuota estado;
	
	@Column(nullable = false)
	private float interesAsociadoPorcentual;
	
	//relaciones
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "id_pago", referencedColumnName="idPago",foreignKey= @ForeignKey(name ="PAGO_EN_CUOTA_FK"))
	private Pago pagoAsociado;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_poliza",referencedColumnName="idPoliza" ,foreignKey= @ForeignKey(name ="POLIZA_EN_CUOTA_FK"))
	private Poliza polizaAsociada;
	
	public Cuota() {
		super();
	}
	
	
	
	
	@Override
	public String toString() {
		return "Cuota [idCuota=" + idCuota + ", premio=" + premio + ", fechaVencimiento=" + fechaVencimiento
				+ ", prima=" + prima + ", derechoEmision=" + derechoEmision + ", descuentos=" + descuentos
				+ ", importeTotal=" + importeTotal + ", estado=" + estado + ", interesAsociadoPorcentual="
				+ interesAsociadoPorcentual + ", pagoAsociado=" + pagoAsociado + ", polizaAsociada=" + polizaAsociada
				+ "]";
	}
	



	@Override
	public int hashCode() {
		return Objects.hash(idCuota);
	}




	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cuota other = (Cuota) obj;
		return idCuota == other.idCuota;
	}

	


	//getters and setters
	public long getIdCuota() {
		return idCuota;
	}

	public float getPremio() {
		return premio;
	}

	public LocalDate getFechaVencimiento() {
		return fechaVencimiento;
	}

	public float getPrima() {
		return prima;
	}

	public float getDerechoEmision() {
		return derechoEmision;
	}

	public float getDescuentos() {
		return descuentos;
	}

	public float getImporteTotal() {
		return importeTotal;
	}

	public EstadoCuota getEstado() {
		return estado;
	}

	public float getInteresAsociadoPorcentual() {
		return interesAsociadoPorcentual;
	}

	public Pago getPagoAsociado() {
		return pagoAsociado;
	}

	public Poliza getPolizaAsociada() {
		return polizaAsociada;
	}

	public void setIdCuota(long idCuota) {
		this.idCuota = idCuota;
	}

	public void setPremio(float premio) {
		this.premio = premio;
	}

	public void setFechaVencimiento(LocalDate fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public void setPrima(float prima) {
		this.prima = prima;
	}

	public void setDerechoEmision(float derechoEmision) {
		this.derechoEmision = derechoEmision;
	}

	public void setDescuentos(float descuentos) {
		this.descuentos = descuentos;
	}

	public void setImporteTotal(float importeTotal) {
		this.importeTotal = importeTotal;
	}

	public void setEstado(EstadoCuota estado) {
		this.estado = estado;
	}

	public void setInteresAsociadoPorcentual(float interesAsociadoPorcentual) {
		this.interesAsociadoPorcentual = interesAsociadoPorcentual;
	}

	public void setPagoAsociado(Pago pagoAsociado) {
		this.pagoAsociado = pagoAsociado;
	}

	public void setPolizaAsociada(Poliza polizaAsociada) {
		this.polizaAsociada = polizaAsociada;
	}
	
	
	
	
}
