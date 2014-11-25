package br.com.sigest.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import br.com.sigest.modelo.Cidade;
import br.com.sigest.modelo.Estado;


/**
 * @author Werick Silva
 */

@Name("fornecedorDao")
@AutoCreate
@Scope(ScopeType.CONVERSATION)
public class FornecedorDao {

	
	@In
	EntityManager entityManager;
	
	
	
	public List<Estado> pesquisarTodosEstados(){
		Session session = (Session) entityManager.getDelegate();
		Criteria criteria = session.createCriteria(Estado.class, "estado");
		return criteria.list();
	}
	
	public List<Cidade> pesquisarCidadesPorEstados(Estado estado){
		
		Session session = (Session) entityManager.getDelegate();
		Criteria criteria = session.createCriteria(Cidade.class, "cidade");
		criteria.add(Restrictions.eq("cidade.estado.id", estado.getId()));
		return criteria.list();
	}
}