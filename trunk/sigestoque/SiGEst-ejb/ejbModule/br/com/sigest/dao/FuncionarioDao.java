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

import br.com.sigest.modelo.Funcionario;


@Name("funcionarioDao")
@AutoCreate
@Scope(ScopeType.CONVERSATION)
public class FuncionarioDao {

	@In
	EntityManager entityManager;
	
	public void salvarFuncionario(Funcionario funcionario){
		entityManager.merge(funcionario);
	}
	
	
	public List<Funcionario> pesquisarFuncionarios(Funcionario funcionario){
		Session session = (Session) entityManager.getDelegate();
		Criteria criteria = session.createCriteria(Funcionario.class, "funcionario");
		if (funcionario.getNome() != null) {
			criteria.add(Restrictions.like("funcionario.nome", funcionario.getNome(), MatchMode.ANYWHERE)
			.ignoreCase());
		}
		if(funcionario.getRg() !=null){
			criteria.add(Restrictions.eq("funcionario.rg", funcionario.getRg()));
		}
		if(funcionario.getCpf() !=null){
			criteria.add(Restrictions.eq("funcionario.cpf", funcionario.getCpf()));
		}
		return criteria.list();
	}
	
	public void excluirFuncionario(Funcionario funcionario){
		entityManager.remove(funcionario);
	}
	
}
