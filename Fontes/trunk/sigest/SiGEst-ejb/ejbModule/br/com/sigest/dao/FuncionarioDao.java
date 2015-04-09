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
import org.jboss.seam.annotations.Transactional;

import br.com.sigest.modelo.Funcionario;

/**
 * @author Werick Silva
 */

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
		if (!funcionario.getNome().isEmpty()) {
			criteria.add(Restrictions.like("funcionario.nome", funcionario.getNome(), MatchMode.ANYWHERE)
			.ignoreCase());
		}
		if(!funcionario.getRg().isEmpty()){
			criteria.add(Restrictions.eq("funcionario.rg", funcionario.getRg()));
		}
		if(!funcionario.getCpf().isEmpty()){
			criteria.add(Restrictions.eq("funcionario.cpf", funcionario.getCpf()));
		}
		if(funcionario.getCargoFuncao() != null){
			criteria.add(Restrictions.eq("funcionario.cargoFuncao", funcionario.getCargoFuncao()));
		}
		criteria.createAlias("funcionario.endereco", "endereco", Criteria.INNER_JOIN);
		return criteria.list();
	}
	
	
	public Funcionario login(String login, String senha){
		Session session = (Session) entityManager.getDelegate();
		Criteria criteria = session.createCriteria(Funcionario.class, "funcionario");
		if (!login.isEmpty()) {
			criteria.add(Restrictions.eq("funcionario.cpf", login));
		}
		if(!senha.isEmpty()){
			criteria.add(Restrictions.like("funcionario.senha", senha,MatchMode.ANYWHERE).ignoreCase());
		}
		
		return (Funcionario) criteria.uniqueResult();
	}
	
	
	
	public void excluirFuncionario(Funcionario funcionario){
		entityManager.remove(funcionario);
	}
	
}
