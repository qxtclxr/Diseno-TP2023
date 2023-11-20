package tp.dao;

import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.ParameterExpression;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import tp.entidad.Cuota;
import tp.entidad.Poliza;

public class PolizaDAO extends AbstractDAO<Poliza> {
	public PolizaDAO() {
		this.setClase(Poliza.class);
	}
	
	public List<Poliza> getByMotor(String motor){
		CriteriaBuilder cbuild=this.getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Poliza> cQuery =cbuild.createQuery(Poliza.class);
		Root<Poliza> root=cQuery.from(Poliza.class);
		
		Predicate p = cbuild.conjunction();
		ParameterExpression<String> motorP= cbuild.parameter(String.class,"motor");
		p=cbuild.and(p,cbuild.equal(root.get("vehiculoAsegurado").get("motor"), motorP));
		cQuery.where(p);
		TypedQuery<Poliza> tq = this.getEntityManager().createQuery(cQuery).setParameter("motor", motor);
		List<Poliza> ret = tq.getResultList();
		return ret;
	}
	
	public List<Poliza> getByChasis(String chasis){
		CriteriaBuilder cbuild=this.getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Poliza> cQuery =cbuild.createQuery(Poliza.class);
		Root<Poliza> root=cQuery.from(Poliza.class);
		
		Predicate p = cbuild.conjunction();
		ParameterExpression<String> chasisP= cbuild.parameter(String.class,"chasis");
		p=cbuild.and(p,cbuild.equal(root.get("vehiculoAsegurado").get("chasis"), chasisP));
		cQuery.where(p);
		TypedQuery<Poliza> tq = this.getEntityManager().createQuery(cQuery).setParameter("chasis", chasis);
		List<Poliza> ret = tq.getResultList();
		return ret;
	}
	
	public List<Poliza> getByPatente(String patente){
		CriteriaBuilder cbuild=this.getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Poliza> cQuery =cbuild.createQuery(Poliza.class);
		Root<Poliza> root=cQuery.from(Poliza.class);
		
		Predicate p = cbuild.conjunction();
		ParameterExpression<String> patenteP= cbuild.parameter(String.class,"patente");
		p=cbuild.and(p,cbuild.equal(root.get("vehiculoAsegurado").get("patente"), patenteP));
		cQuery.where(p);
		TypedQuery<Poliza> tq = this.getEntityManager().createQuery(cQuery).setParameter("patente", patente);
		List<Poliza> ret = tq.getResultList();
		return ret;
	}
	public Optional<Poliza> getByNumero(String numPoliza){
		CriteriaBuilder cb=this.getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Poliza> cq= cb.createQuery(Poliza.class);
		Root<Poliza> from = cq.from(Poliza.class);
		cq.select(from).where(cb.equal(from.get("nroPoliza"), numPoliza));
		TypedQuery<Poliza> query=this.getEntityManager().createQuery(cq);
		return Optional.ofNullable(query.getSingleResult());
	}
	public void altaPoliza(Poliza p) {
		EntityManager em =this.getEntityManager();
		EntityTransaction t=this.getEntityManager().getTransaction();
		try {
			t.begin();
			//em.merge(p.getVehiculoAsegurado().getAnioModelo());
			
			for(Cuota c:p.getCuotasAsociadas()) {
				c.setPolizaAsociada(p);
			}
			p.getCliente().getPolizas().add(p);
			em.persist(p);
			em.merge(p.getCliente());
			t.commit();
		}
		catch(RuntimeException ex) {
			t.rollback();
			throw ex;
		}
	}
}
