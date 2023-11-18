package tp.dao;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import tp.entidad.RangoCantSiniestros;

public class RangoCantSiniestrosDAO extends AbstractDAO<RangoCantSiniestros> {
	public RangoCantSiniestrosDAO() {
		this.setClase(RangoCantSiniestros.class);
	}
	
	public Optional<RangoCantSiniestros> getByMinimo(int cant) {
		CriteriaBuilder cbuild = this.getEntityManager().getCriteriaBuilder();
		CriteriaQuery<RangoCantSiniestros> cQuery = cbuild.createQuery(RangoCantSiniestros.class);
		Root<RangoCantSiniestros> root = cQuery.from(RangoCantSiniestros.class);
		
		Predicate cantMin = cbuild.greaterThanOrEqualTo(root.get("desdeCant"),cant);
		
		cQuery.select(root).where(cantMin);
		TypedQuery<RangoCantSiniestros> query = this.getEntityManager().createQuery(cQuery);
		List<RangoCantSiniestros> result=query.getResultList().stream().sorted(Comparator.comparingInt(RangoCantSiniestros::getDesdeCant)).collect(Collectors.toList());
		
		return Optional.ofNullable(result.get(0));


	}
}
