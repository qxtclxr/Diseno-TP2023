package tp.dao;

import java.util.Optional;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import tp.entidad.Pais;

public class PaisDAO extends AbstractDAO<Pais> {
	public PaisDAO() {
		this.setClase(Pais.class);
	}
	public Optional<Pais> getPaisByNombre(String nombre){
		CriteriaBuilder cb = this.getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Pais> cq = cb.createQuery(Pais.class);
		Root<Pais> from = cq.from(Pais.class);
		
		cq.select(from).where(cb.equal(from.get("nombre"), nombre));
		TypedQuery<Pais> query =this.getEntityManager().createQuery(cq);
		return Optional.ofNullable(query.getSingleResult());
	}
}
