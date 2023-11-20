package tp.entidad;

import java.time.LocalDate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import tp.entidad.*;
import jakarta.persistence.*;

@Entity
@Table(name="cliente",uniqueConstraints = {@UniqueConstraint(columnNames={"nroDocumento","sexo"})})
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idCliente")
	private long idCliente;
	
	
	@Column(nullable = false, unique = true) 
	private String nroCliente; //puede usarse un naturalid
	
	@Column(nullable = false)
	private String nroDocumento;
	
	@Column(nullable = false)
	private TipoDocumento tipoDocumento;
	
	@Column(nullable = false)
	private String apellido;
	
	@Column(nullable = false)
	private String nombres;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TipoCliente tipoCliente;
	
	@Column(nullable = false,unique=true)
	private String nroCuil;
	
	@Column(nullable = false)
	private LocalDate fechaNacimiento;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TipoIVA condicionIVA;
	
	@Column(nullable = false)
	private String correoElectronico;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private EstadoCivil estadoCivil;
	
	@Column(nullable = false)
	private String profesion;
	
	@Column(nullable = false)
	private LocalDateTime anioRegistro;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Sexo sexo;
	
	@Column(nullable = false)
	private LocalDateTime fechaModificacionEstado;
	///Ver si aca le ponemos nullable o no
	@Column
	private int cantSiniestrosCliente;
	
	//Relaciones
	
	@OneToMany(fetch= FetchType.LAZY, mappedBy="cliente",cascade = CascadeType.PERSIST, orphanRemoval = true)
	private List<Poliza> polizas;
	
	@OneToOne
	@JoinColumn(name="idDomicilio")
	private Domicilio domicilio;
	
	public Cliente(){
		super();
	}
	



	@Override
	public int hashCode() {
		return Objects.hash(idCliente, nroCliente);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return idCliente == other.idCliente && Objects.equals(nroCliente, other.nroCliente);
	}


	@Override
	public String toString() {
		return "Cliente [idCliente=" + idCliente + ", nroCliente=" + nroCliente + ", nroDocumento=" + nroDocumento
				+ ", tipoDocumento=" + tipoDocumento + ", apellido=" + apellido + ", nombres=" + nombres
				+ ", tipoCliente=" + tipoCliente + ", correoElectronico=" + correoElectronico
				+ ", fechaModificacionEstado=" + fechaModificacionEstado + "]";
	}


	//getters
	public long getIdCliente() {
		return idCliente;
	}

	public String getNroCliente() {
		return nroCliente;
	}

	public String getNroDocumento() {
		return nroDocumento;
	}

	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}

	public String getApellido() {
		return apellido;
	}

	public String getNombres() {
		return nombres;
	}

	public TipoCliente getTipoCliente() {
		return tipoCliente;
	}

	public String getNroCuil() {
		return nroCuil;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public TipoIVA getCondicionIVA() {
		return condicionIVA;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public EstadoCivil getEstadoCivil() {
		return estadoCivil;
	}

	public String getProfesion() {
		return profesion;
	}

	public LocalDateTime getAnioRegistro() {
		return anioRegistro;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public LocalDateTime getFechaModificacionEstado() {
		return fechaModificacionEstado;
	}

	public List<Poliza> getPolizas() {
		return polizas;
	}

	public Domicilio getDomicilio() {
		return domicilio;
	}
	//setters

	public void setIdCliente(long idCliente) {
		this.idCliente = idCliente;
	}

	public void setNroCliente(String nroCliente) {
		this.nroCliente = nroCliente;
	}

	public void setNroDocumento(String nroDocumento) {
		this.nroDocumento = nroDocumento;
	}

	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public void setTipoCliente(TipoCliente tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	public void setNroCuil(String nroCuil) {
		this.nroCuil = nroCuil;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public void setCondicionIVA(TipoIVA condicionIVA) {
		this.condicionIVA = condicionIVA;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public void setEstadoCivil(EstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public void setProfesion(String profesion) {
		this.profesion = profesion;
	}

	public void setAnioRegistro(LocalDateTime anioRegistro) {
		this.anioRegistro = anioRegistro;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public void setFechaModificacionEstado(LocalDateTime fechaModificacionEstado) {
		this.fechaModificacionEstado = fechaModificacionEstado;
	}

	public void setPolizas(List<Poliza> polizas) {
		this.polizas = polizas;
	}

	public void setDomicilio(Domicilio domicilio) {
		this.domicilio = domicilio;
	}




	public int getCantSiniestrosCliente() {
		return cantSiniestrosCliente;
	}




	public void setCantSiniestrosCliente(int cantSiniestrosCliente) {
		this.cantSiniestrosCliente = cantSiniestrosCliente;
	}
	
	
	
	
	
	
	
	
	
	
}
