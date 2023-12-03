package tp.entidad;


import java.time.LocalDate;
import java.util.Objects;

import tp.entidad.*;

import jakarta.persistence.*;

@Entity
@Table(name="hijoDeclaradoModificacion")
public class HijoDeclaradoModificacion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idHijoDeclaradoModificacion")
	private long idHijoDeclaradoModificacion;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Sexo sexo;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private EstadoCivil estadoCivil;
	
	@Column(nullable = true)
	private String documento;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = true)
	private TipoDocumento tipoDocumento;
	
	@Column(nullable=false)
	private LocalDate fechaNacimiento;
	
	public HijoDeclaradoModificacion() {
		super();
	}

	@Override
	public int hashCode() {
		return Objects.hash(idHijoDeclaradoModificacion);
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_poliza_mod",referencedColumnName="idModificacionPoliza" ,foreignKey= @ForeignKey(name ="MOD_POLIZA_HIJO_FK"))
	private ModificacionPoliza polizaAsociadaModificacion;
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HijoDeclaradoModificacion other = (HijoDeclaradoModificacion) obj;
		return idHijoDeclaradoModificacion == other.idHijoDeclaradoModificacion;
	}

	@Override
	public String toString() {
		return "HijoDeclaradoModificacion [idHijoDeclaradoModificacion=" + idHijoDeclaradoModificacion + ", sexo="
				+ sexo + ", estadoCivil=" + estadoCivil + ", documento=" + documento + ", tipoDocumento="
				+ tipoDocumento + ", fechaNacimiento=" + fechaNacimiento + "]";
	}
	//getters and setters

	public long getIdHijoDeclaradoModificacion() {
		return idHijoDeclaradoModificacion;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public EstadoCivil getEstadoCivil() {
		return estadoCivil;
	}

	public String getDocumento() {
		return documento;
	}

	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setIdHijoDeclaradoModificacion(long idHijoDeclaradoModificacion) {
		this.idHijoDeclaradoModificacion = idHijoDeclaradoModificacion;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public void setEstadoCivil(EstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public ModificacionPoliza getPolizaAsociadaModificacion() {
		return polizaAsociadaModificacion;
	}

	public void setPolizaAsociadaModificacion(ModificacionPoliza polizaAsociadaModificacion) {
		this.polizaAsociadaModificacion = polizaAsociadaModificacion;
	}
	
	
	
}
