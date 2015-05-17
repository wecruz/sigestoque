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

import br.com.sigest.modelo.Categoria;
import br.com.sigest.modelo.Fornecedor;


/**
 * @author Werick Silva
 */

@Name("fornecedorDao")
@AutoCreate
@Scope(ScopeType.CONVERSATION)
public class FornecedorDao {

	
	@In
	EntityManager entityManager;
	
	
	public void salvar (Fornecedor fornecedor){
		
		entityManager.merge(fornecedor);
	}
	
	public List<Fornecedor> fidAllFornecedor(){
		Session session = (Session) entityManager.getDelegate();
		Criteria criteria = session.createCriteria(Fornecedor.class, "fornecedor");
		return criteria.list();
	}
	
	public List<Fornecedor> pesquisarFornecedores(Fornecedor fornecedor){
		Session session = (Session) entityManager.getDelegate();
		Criteria criteria = session.createCriteria(Fornecedor.class, "fornecedor");
		if (!fornecedor.getNome().isEmpty() &&  fornecedor.getNome() != null) {
			criteria.add(Restrictions.like("fornecedor.nome", fornecedor.getNome(), MatchMode.ANYWHERE).ignoreCase());
		}
		if (fornecedor.getCnpj() != null && !fornecedor.getCnpj().isEmpty()) {
			criteria.add(Restrictions.eq("fornecedor.cnpj", fornecedor.getCnpj()));
		}
		return criteria.list();
	}
	
	public void excluir(Fornecedor fornecedor){
		entityManager.remove(fornecedor);
	}
	
	
	
	
}
