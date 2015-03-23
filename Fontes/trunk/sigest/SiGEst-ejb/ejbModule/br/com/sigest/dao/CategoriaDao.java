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


@Name("categoriaDao")
@AutoCreate
@Scope(ScopeType.CONVERSATION)
public class CategoriaDao {
	
	@In
	EntityManager entityManager;
	
	public List<Categoria> pesquisarCategoria(Categoria categoria){
		Session session = (Session) entityManager.getDelegate();
		Criteria criteria = session.createCriteria(Categoria.class, "categoria");
		
		if(!categoria.getNome().isEmpty()){
			criteria.add(Restrictions.like("categoria.nome", categoria.getNome(), MatchMode.ANYWHERE).ignoreCase());
		}
		
		if(!categoria.getDescricao().isEmpty()){
			criteria.add(Restrictions.like("categoria.descricao", categoria.getDescricao()));
		}
		
		return criteria.list();
	}
	
	
	public List<Categoria> fidAllCategoria(){
		Session session = (Session) entityManager.getDelegate();
		Criteria criteria = session.createCriteria(Categoria.class, "categoria");
		
		return criteria.list();
	}
	
	public void salvarCategoria(Categoria categoria){
		entityManager.merge(categoria);
	}

}
