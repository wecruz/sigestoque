package br.com.sigest.dao;

import javax.persistence.EntityManager;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import br.com.sigest.modelo.Venda;

@Name("vendaDao")
@AutoCreate
@Scope(ScopeType.CONVERSATION)
public class VendaDao {
	
	@In
	EntityManager entityManager;
	
	
	public void salvaPedidoVenda(Venda venda){
		entityManager.merge(venda);
	}

}
