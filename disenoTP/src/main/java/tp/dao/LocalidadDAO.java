package tp.dao;

import java.util.List;



import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import tp.entidad.Localidad;
import tp.entidad.Provincia;
import jakarta.persistence.Query;

public class LocalidadDAO extends AbstractDAO<Localidad> {
	public LocalidadDAO() {
		this.setClase(Localidad.class);
	}
	
	public List<Localidad> getLocalidadesByProvincia(Provincia p){
		CriteriaBuilder cbuild= this.getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Localidad> cQuery= cbuild.createQuery(Localidad.class);
		Root<Localidad> root = cQuery.from(Localidad.class);
		
		cQuery.select(root).where(cbuild.equal(root.get("idProvincia"), p.getIdProvincia()));
		Query query = this.getEntityManager().createQuery(cQuery);
		List<Localidad> retorno = query.getResultList();
		return retorno;
	}
}
