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
	
	@OneToMany(fetch= FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<RespuestaSegModificacion> respuestasModificadas;
	
	//Relaciones simplificadas
	@Column
	private int idRangoKMrealizados;
	@Column
	private int idRangoCantSiniestros;
	
	@Column
	private int idCobertura;
	
	@OneToMany(fetch= FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<HijoDeclaradoModificacion> hijosDeclaradosModificados;
	
	@ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="idUsuario", foreignKey= @ForeignKey(name="FK_usuario_en_modificacion"))
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

	@Override
	public String toString() {
		return "ModificacionPoliza [idModificacionPoliza=" + idModificacionPoliza + ", anioVehiculo=" + anioVehiculo
				+ ", patente=" + patente + ", motor=" + motor + ", chasis=" + chasis + ", fechaModificacion="
				+ fechaModificacion + ", respuestasModificadas=" + respuestasModificadas + ", idRangoKMrealizados="
				+ idRangoKMrealizados + ", idRangoCantSiniestros=" + idRangoCantSiniestros + ", idCobertura="
				+ idCobertura + ", hijosDeclaradosModificados=" + hijosDeclaradosModificados + ", modificadoPor="
				+ modificadoPor + "]";
	}
	//getters and setters

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

	public List<RespuestaSegModificacion> getRespuestasModificadas() {
		return respuestasModificadas;
	}

	public int getIdRangoKMrealizados() {
		return idRangoKMrealizados;
	}

	public int getIdRangoCantSiniestros() {
		return idRangoCantSiniestros;
	}

	public int getIdCobertura() {
		return idCobertura;
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

	public void setRespuestasModificadas(List<RespuestaSegModificacion> respuestasModificadas) {
		this.respuestasModificadas = respuestasModificadas;
	}

	public void setIdRangoKMrealizados(int idRangoKMrealizados) {
		this.idRangoKMrealizados = idRangoKMrealizados;
	}

	public void setIdRangoCantSiniestros(int idRangoCantSiniestros) {
		this.idRangoCantSiniestros = idRangoCantSiniestros;
	}

	public void setIdCobertura(int idCobertura) {
		this.idCobertura = idCobertura;
	}

	public void setHijosDeclaradosModificados(List<HijoDeclaradoModificacion> hijosDeclaradosModificados) {
		this.hijosDeclaradosModificados = hijosDeclaradosModificados;
	}

	public void setModificadoPor(Usuario modificadoPor) {
		this.modificadoPor = modificadoPor;
	}
	
}
