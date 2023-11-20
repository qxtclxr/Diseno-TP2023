package tp.entidad;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.*;

@Entity
@Table(name="sucursal")
public class Sucursal {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idSucursal")
	private long idSucursal;
	
	@Column(nullable=false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long codigoSucursal;
	
	@Column(nullable=false)
	private long secuenciaDePoliza;
	
	@OneToMany(fetch= FetchType.LAZY, mappedBy="sucursalAsociada",cascade = CascadeType.ALL)
	private List<Usuario> productores;
	
	public Sucursal() {
		super();
		
	}

	@Override
	public int hashCode() {
		return Objects.hash(idSucursal);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sucursal other = (Sucursal) obj;
		return idSucursal == other.idSucursal;
	}

	@Override
	public String toString() {
		return "Sucursal [idSucursal=" + idSucursal + ", codigoSucursal=" + codigoSucursal + ", secuenciaDePoliza="
				+ secuenciaDePoliza + ", productores=" + productores + "]";
	}

	public long getIdSucursal() {
		return idSucursal;
	}

	public long getCodigoSucursal() {
		return codigoSucursal;
	}

	public long getSecuenciaDePoliza() {
		return secuenciaDePoliza;
	}

	public List<Usuario> getProductores() {
		return productores;
	}

	public void setIdSucursal(long idSucursal) {
		this.idSucursal = idSucursal;
	}

	public void setCodigoSucursal(long codigoSucursal) {
		this.codigoSucursal = codigoSucursal;
	}

	public void setSecuenciaDePoliza(long secuenciaDePoliza) {
		this.secuenciaDePoliza = secuenciaDePoliza;
	}

	public void setProductores(List<Usuario> productores) {
		this.productores = productores;
	}
	
	
}
