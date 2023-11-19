package tp.dao;

import java.util.List;
import java.util.Optional;


import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import tp.entidad.Cliente;
import tp.entidad.TipoDocumento;

public class ClienteDAO extends AbstractDAO<Cliente> {
	public ClienteDAO() {
		this.setClase(Cliente.class);
	}
	public Optional<Cliente> getClienteByNroCliente(String nroCliente){
		CriteriaBuilder cbuild=this.getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Cliente> cQuery =cbuild.createQuery(Cliente.class);
		
		Root<Cliente> root =cQuery.from(Cliente.class);
		
		cQuery.select(root).where(cbuild.equal(root.get("nroCliente"), nroCliente));
		TypedQuery<Cliente> query = this.getEntityManager().createQuery(cQuery);
		return Optional.ofNullable(query.getSingleResult());
	}
	
	public List<Cliente> buscarCliente(String nroCliente, String apellido, String nombre, TipoDocumento tipoDoc, String nroDoc){
		CriteriaBuilder cbuilder = this.getEntityManager().getCriteriaBuilder();
	    CriteriaQuery<Cliente> cQuery = cbuilder.createQuery(Cliente.class);
	    Root<Cliente> from = cQuery.from(Cliente.class);
	    cQuery.select(from);
	    if(!nombre.equals("")) {
	    	cQuery.where(cbuilder.equal(from.get("nombres"),nombre ));
	    }
	    if(!apellido.equals("")) {
	    	cQuery.where(cbuilder.equal(from.get("apellido"), apellido));
	    }
	    if(!nroCliente.equals("")) {
	    	cQuery.where(cbuilder.equal(from.get("nroCliente"), nroCliente));
	    }
	    if(!nroDoc.equals("")) {
	    	cQuery.where(cbuilder.equal(from.get("tipoDocumento"), tipoDoc),cbuilder.equal(from.get("nroDocumento"),nroDoc ));
	    }
	    
	    TypedQuery<Cliente> tq =this.getEntityManager().createQuery(cQuery);
	    List<Cliente> ret= tq.getResultList();
	    return ret;
	    
	    
	    
	}
}
