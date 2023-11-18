package tp.dao;

import jakarta.persistence.EntityTransaction;
import tp.entidad.Sucursal;

public class SucursalDAO extends AbstractDAO<Sucursal> {
	public SucursalDAO() {
		this.setClase(Sucursal.class);
	}
	public long getSerialPoliza(Sucursal s) {
		long ret;
		EntityTransaction t= this.getEntityManager().getTransaction();
		try {
			t.begin();
			s=this.getById(s.getIdSucursal()).get();
			
			ret=s.getSecuenciaDePoliza();
			s.setSecuenciaDePoliza(ret+1);
			this.getEntityManager().persist(s);
			t.commit();
		} catch(RuntimeException exep) {
			t.rollback();
			throw exep;
		}
		return ret;
		
	}
}
