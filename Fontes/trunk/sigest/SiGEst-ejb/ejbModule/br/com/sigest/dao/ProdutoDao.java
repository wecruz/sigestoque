package br.com.sigest.dao;

import java.math.BigInteger;
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

import br.com.sigest.modelo.Fornecedor;
import br.com.sigest.modelo.Produto;

@Name("produtoDao")
@AutoCreate
@Scope(ScopeType.CONVERSATION)
public class ProdutoDao {

	@In
	EntityManager entityManager;
	
	
	public void salvarProduto(Produto produto){
		entityManager.merge(produto);
	}
	
	
	public List<Produto> pesquisarProduto(Produto produto){
		Session session = (Session) entityManager.getDelegate();
		Criteria criteria = session.createCriteria(Produto.class, "produto");
		if (produto.getNomeProduto() != null) {
			criteria.add(Restrictions.like("produto.nomeProduto", produto.getNomeProduto(), MatchMode.ANYWHERE).ignoreCase());
		}
		if (produto.getCodigo() != null) {
			criteria.add(Restrictions.eq("produto.codigo", produto.getCodigo()));
		}
		if (produto.getFornecedor() != null) {
			criteria.add(Restrictions.eq("produto.fornecedor", produto.getFornecedor()));
		}
		if (produto.getCategoria() != null) {
			criteria.add(Restrictions.eq("produto.categoria", produto.getCategoria()));
		}
		return criteria.list();
	}
	
	
	public List<Produto> pesquisarProdutoPorCodigo(Integer codigo){
		Session session = (Session) entityManager.getDelegate();
		Criteria criteria = session.createCriteria(Produto.class, "produto");
		
		if (codigo != null) {
			criteria.add(Restrictions.eq("produto.codigo", codigo));
		}
		return criteria.list();
	}
	
	
	public List<Produto> pesquisarProdutoVenda(Produto produto){
		Session session = (Session) entityManager.getDelegate();
		Criteria criteria = session.createCriteria(Produto.class, "produto");
		if (produto.getNomeProduto() != null) {
			criteria.add(Restrictions.like("produto.nomeProduto", produto.getNomeProduto(), MatchMode.ANYWHERE).ignoreCase());
		}
		return criteria.list();
	}
	
	public List<Produto> fildAllProduto(){
		Session session = (Session) entityManager.getDelegate();
		Criteria criteria = session.createCriteria(Produto.class, "produto");
		
		return criteria.list();
	}
	
	
	public List<Produto> pesquisarProdutoFornecedor(Fornecedor fornecedor){
		Session session = (Session) entityManager.getDelegate();
		
		Criteria criteria = session.createCriteria(Produto.class, "produto");
		
		criteria.createAlias("produto.fornecedor", "fornecedor", Criteria.INNER_JOIN);
		if(fornecedor.getId()!= null){
			criteria.add(Restrictions.eq("produto.fornecedor.id",fornecedor.getId()));
		}
		return criteria.list();
	}
 	
	public void deletarProduto(Produto produto){
		entityManager.remove(produto);
	}
}
