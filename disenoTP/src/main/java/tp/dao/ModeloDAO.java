package tp.dao;


import java.util.List;

import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import tp.entidad.Marca;
import tp.entidad.Modelo;


public class ModeloDAO extends AbstractDAO<Modelo> {
	public ModeloDAO() {
		this.setClase(Modelo.class);
	}
	public List<Modelo> getModelosByMarca(Marca m){
		CriteriaBuilder cbuild= this.getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Modelo> cQuery= cbuild.createQuery(Modelo.class);
		Root<Modelo> root=cQuery.from(Modelo.class);
		
		cQuery.select(root).where(cbuild.equal(root.get("marca"), m));
		Query query = this.getEntityManager().createQuery(cQuery);
		List<Modelo> retorno =query.getResultList();
		return retorno;
		
	}
}
