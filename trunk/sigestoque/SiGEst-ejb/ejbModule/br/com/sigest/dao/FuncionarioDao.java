package br.com.sigest.dao;

import javax.persistence.EntityManager;

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
}
