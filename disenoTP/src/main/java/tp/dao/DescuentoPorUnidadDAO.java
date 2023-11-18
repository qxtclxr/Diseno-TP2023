package tp.dao;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import tp.entidad.DescuentoPorUnidad;


public class DescuentoPorUnidadDAO extends AbstractDAO<DescuentoPorUnidad> {
	public DescuentoPorUnidadDAO() {
		this.setClase(DescuentoPorUnidad.class);
	}
	
	public Optional<DescuentoPorUnidad> getByMinimo(int cant){
		CriteriaBuilder cbuild = this.getEntityManager().getCriteriaBuilder();
		CriteriaQuery<DescuentoPorUnidad> cQuery = cbuild.createQuery(DescuentoPorUnidad.class);
		Root<DescuentoPorUnidad> root = cQuery.from(DescuentoPorUnidad.class);
		
		
		
		
		Predicate cantMin = cbuild.greaterThanOrEqualTo(root.get("desdeCantU"),cant);
		

		cQuery.select(root).where(cantMin);
		TypedQuery<DescuentoPorUnidad> query = this.getEntityManager().createQuery(cQuery);
		List<DescuentoPorUnidad> result=query.getResultList().stream().sorted(Comparator.comparingInt(DescuentoPorUnidad::getDesdeCantU)).collect(Collectors.toList());
		
		return Optional.ofNullable(result.get(0));
	}
}
