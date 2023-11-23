package tp.dao;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;


import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
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
	
	public List<Cliente> buscarCliente_legacy(String nroCliente, String apellido, String nombre, TipoDocumento tipoDoc, String nroDoc){
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
	
	public List<Cliente> buscarCliente(
			String nroCliente,
			String[] apellidoTokens,
			String[] nombreTokens,
			TipoDocumento tipoDoc,
			String nroDoc){
	    CriteriaBuilder cbuilder = this.getEntityManager().getCriteriaBuilder();
	    CriteriaQuery<Cliente> cQuery = cbuilder.createQuery(Cliente.class);
	    Root<Cliente> from = cQuery.from(Cliente.class);
	    cQuery.select(from);

	    List<Predicate> predicates = new ArrayList<Predicate>();

	    if (!(nombreTokens.length==0)) {
	        Expression<String> nombreExp = from.get("nombres");
	        for (String token : nombreTokens) {
	            predicates.add(cbuilder.like(nombreExp, "%" + token + "%"));
	        }
	    }

	    if (!(apellidoTokens.length==0)) {
	        Expression<String> apellidoExp = from.get("apellido");
	        for (String token : apellidoTokens) {
	            predicates.add(cbuilder.like(apellidoExp, "%" + token + "%"));
	        }
	    }

	    if (!nroCliente.equals("")) {
	        predicates.add(cbuilder.like(from.get("nroCliente"), "%" + nroCliente + "%"));
	    }
	    
	    if (!nroDoc.equals("") && tipoDoc != null) {
	        predicates.add(cbuilder.equal(from.get("tipoDocumento"), tipoDoc));
	        predicates.add(cbuilder.like(from.get("nroDocumento"), "%" + nroDoc + "%"));
	    } else if (!nroDoc.equals("")) {
	        predicates.add(cbuilder.like(from.get("nroDocumento"), "%" + nroDoc + "%"));
	    } else if (tipoDoc != null) {
	        predicates.add(cbuilder.equal(from.get("tipoDocumento"), tipoDoc));
	    }

	    // Combina todas las condiciones con un AND
	    cQuery.where(predicates.toArray(new Predicate[0]));

	    TypedQuery<Cliente> tq = this.getEntityManager().createQuery(cQuery);
	    List<Cliente> ret = tq.getResultList();
	    return ret;
	}

}
