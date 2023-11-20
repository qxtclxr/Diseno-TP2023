package tp.entidad;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.*;

@Entity
@Table(name="modificacionPoliza")
public class ModificacionPoliza {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idModificacionPoliza")
	private long idModificacionPoliza;
	
	@Column
	private LocalDate anioVehiculo;
	
	@Column
	private String patente;
	
	@Column
	private String motor;
	
	@Column
	private String chasis;
	
	@Column(nullable=false)
	private LocalDateTime fechaModificacion;
	
	//relaciones
	
	
	@ManyToMany(fetch= FetchType.LAZY,cascade = {CascadeType.PERSIST, CascadeType.ALL})
	private List<PorcentajeMedidaDeSeguridad> porcMedidasModificadas;
	
	//Relaciones 
	
	@ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="porKMModificado", referencedColumnName="idPorcentajeKMRealizados", foreignKey= @ForeignKey(name="FK_Por_KM_mod"))
	private PorcentajeKMRealizados porcentajeKMModificado;
	
	@ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="porCantSinModificado", referencedColumnName="idPorcCantSin", foreignKey= @ForeignKey(name="FK_Por_cant_sin_mod"))
	private PorcentajeCantSiniestros porcentajeModificado;
	
	@ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="porCoberturaModificada", referencedColumnName="idPorcentajeCobertura", foreignKey= @ForeignKey(name="FK_Por_cobertura_mod"))
	private PorcentajeCobertura porcCoberturaModificada;
	
	@OneToMany(fetch= FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<HijoDeclaradoModificacion> hijosDeclaradosModificados;
	
	@ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="idUsuario", referencedColumnName="idUsuario",foreignKey= @ForeignKey(name="FK_usuario_en_modificacion"))
	private Usuario modificadoPor;
	
	
	@PrePersist
	protected void onCreate() {
		fechaModificacion=LocalDateTime.now();
	}
	
	@PreUpdate
	protected void onUpdate() {
	    fechaModificacion = LocalDateTime.now();
	  }
	public ModificacionPoliza() {
		super();
	}

	@Override
	public int hashCode() {
		return Objects.hash(idModificacionPoliza);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ModificacionPoliza other = (ModificacionPoliza) obj;
		return idModificacionPoliza == other.idModificacionPoliza;
	}

	
	//getters and setters

	

	@Override
	public String toString() {
		return "ModificacionPoliza [idModificacionPoliza=" + idModificacionPoliza + ", anioVehiculo=" + anioVehiculo
				+ ", patente=" + patente + ", motor=" + motor + ", chasis=" + chasis + ", fechaModificacion="
				+ fechaModificacion + ", porcMedidasModificadas=" + porcMedidasModificadas + ", porcentajeKMModificado="
				+ porcentajeKMModificado + ", porcentajeModificado=" + porcentajeModificado
				+ ", porcCoberturaModificada=" + porcCoberturaModificada + ", hijosDeclaradosModificados="
				+ hijosDeclaradosModificados + ", modificadoPor=" + modificadoPor + "]";
	}

	public long getIdModificacionPoliza() {
		return idModificacionPoliza;
	}

	public LocalDate getAnioVehiculo() {
		return anioVehiculo;
	}

	public String getPatente() {
		return patente;
	}

	public String getMotor() {
		return motor;
	}

	public String getChasis() {
		return chasis;
	}

	public LocalDateTime getFechaModificacion() {
		return fechaModificacion;
	}

	

	public List<HijoDeclaradoModificacion> getHijosDeclaradosModificados() {
		return hijosDeclaradosModificados;
	}

	public Usuario getModificadoPor() {
		return modificadoPor;
	}

	public void setIdModificacionPoliza(long idModificacionPoliza) {
		this.idModificacionPoliza = idModificacionPoliza;
	}

	public void setAnioVehiculo(LocalDate anioVehiculo) {
		this.anioVehiculo = anioVehiculo;
	}

	public void setPatente(String patente) {
		this.patente = patente;
	}

	public void setMotor(String motor) {
		this.motor = motor;
	}

	public void setChasis(String chasis) {
		this.chasis = chasis;
	}

	public void setFechaModificacion(LocalDateTime fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	

	

	public PorcentajeCobertura getPorcCoberturaModificada() {
		return porcCoberturaModificada;
	}

	public void setPorcCoberturaModificada(PorcentajeCobertura porcCoberturaModificada) {
		this.porcCoberturaModificada = porcCoberturaModificada;
	}

	public void setHijosDeclaradosModificados(List<HijoDeclaradoModificacion> hijosDeclaradosModificados) {
		this.hijosDeclaradosModificados = hijosDeclaradosModificados;
	}

	public void setModificadoPor(Usuario modificadoPor) {
		this.modificadoPor = modificadoPor;
	}

	public List<PorcentajeMedidaDeSeguridad> getPorcMedidasModificadas() {
		return porcMedidasModificadas;
	}

	public PorcentajeKMRealizados getPorcentajeKMModificado() {
		return porcentajeKMModificado;
	}

	public PorcentajeCantSiniestros getPorcentajeModificado() {
		return porcentajeModificado;
	}

	public void setPorcMedidasModificadas(List<PorcentajeMedidaDeSeguridad> porcMedidasModificadas) {
		this.porcMedidasModificadas = porcMedidasModificadas;
	}

	public void setPorcentajeKMModificado(PorcentajeKMRealizados porcentajeKMModificado) {
		this.porcentajeKMModificado = porcentajeKMModificado;
	}

	public void setPorcentajeModificado(PorcentajeCantSiniestros porcentajeModificado) {
		this.porcentajeModificado = porcentajeModificado;
	}
	
	
}
