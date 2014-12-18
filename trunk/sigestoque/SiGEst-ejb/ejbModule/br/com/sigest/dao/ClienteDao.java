package br.com.sigest.dao;

import javax.persistence.EntityManager;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import br.com.sigest.modelo.Cliente;


/**
 * @author Werick Silva
 */

@Name("clienteDao")
@AutoCreate
@Scope(ScopeType.CONVERSATION)
public class ClienteDao {
	
	@In
	EntityManager entityManager;
	
	public void salvarCliente(Cliente cliente){
		entityManager.merge(cliente);
	}

}
