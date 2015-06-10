package br.com.sigest.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
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
	
	public void removerCliente(Cliente cliente){
		entityManager.remove(cliente);
	}
	
	public List<Cliente> pesquisarClientes(Cliente cliente){
		Session session = (Session) entityManager.getDelegate();
		Criteria criteria = session.createCriteria(Cliente.class, "cliente");
		
		if (cliente.getCpf() != null) {
			criteria.add(Restrictions.like("cliente.cpf", cliente.getCpf(), MatchMode.ANYWHERE).ignoreCase());
		}
		
		if (cliente.getNome() != null) {
			criteria.add(Restrictions.like("cliente.nome", cliente.getNome(), MatchMode.ANYWHERE).ignoreCase());
		}
		criteria.createAlias("cliente.endereco", "endereco", Criteria.INNER_JOIN);
		return criteria.list();
	}
	

}
