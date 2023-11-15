package tp.dao;

import java.util.Optional;

import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import tp.entidad.Cliente;

public class ClienteDAO extends AbstractDAO<Cliente> {
	public ClienteDAO() {
		this.setClase(Cliente.class);
	}
	public Optional<Cliente> getClienteByNroCliente(String nroCliente){
		CriteriaBuilder cbuild=this.getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Cliente> cQuery =cbuild.createQuery(Cliente.class);
		
		Root<Cliente> root =cQuery.from(Cliente.class);
		
		cQuery.select(root).where(cbuild.equal(root.get("nroCliente"), nroCliente));
		Query query = this.getEntityManager().createQuery(cQuery);
		return Optional.ofNullable((Cliente)query.getSingleResult());
	}
	
}
