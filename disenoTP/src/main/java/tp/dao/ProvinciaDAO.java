package tp.dao;

import java.util.List;

import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import tp.entidad.Pais;
import tp.entidad.Provincia;

public class ProvinciaDAO extends AbstractDAO<Provincia> {
	public ProvinciaDAO() {
		this.setClase(Provincia.class);
	}
	public List<Provincia> getProvinciasByPais(Pais p){
		CriteriaBuilder cbuild= this.getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Provincia> cQuery= cbuild.createQuery(Provincia.class);
		Root<Provincia> root = cQuery.from(Provincia.class);
		
		cQuery.select(root).where(cbuild.equal(root.get("pais"), p));
		Query query = this.getEntityManager().createQuery(cQuery);
		List<Provincia> retorno = query.getResultList();
		return retorno;
	}
}
