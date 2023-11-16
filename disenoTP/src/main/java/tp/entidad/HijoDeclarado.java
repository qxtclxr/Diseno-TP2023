package tp.entidad;

import tp.entidad.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.*;


@Entity
@Table(name="hijoDeclarado")
public class HijoDeclarado {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idHijoDeclarado")
	private long idHijoDeclarado;
	
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
	
	public HijoDeclarado() {
		super();
	}

	@Override
	public int hashCode() {
		return Objects.hash(idHijoDeclarado);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HijoDeclarado other = (HijoDeclarado) obj;
		return idHijoDeclarado == other.idHijoDeclarado;
	}

	@Override
	public String toString() {
		return "HijoDeclarado [idHijoDeclarado=" + idHijoDeclarado + ", sexo=" + sexo + ", estadoCivil=" + estadoCivil
				+ ", documento=" + documento + ", tipoDocumento=" + tipoDocumento + ", fechaNacimiento="
				+ fechaNacimiento + "]";
	}
	
	//getters and setters
	public long getIdHijoDeclarado() {
		return idHijoDeclarado;
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

	public void setIdHijoDeclarado(long idHijoDeclarado) {
		this.idHijoDeclarado = idHijoDeclarado;
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
	
	
	
}
