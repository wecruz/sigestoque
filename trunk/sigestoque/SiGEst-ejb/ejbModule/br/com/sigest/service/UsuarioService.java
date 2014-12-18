package br.com.sigest.service;

import java.util.List;

import javax.ejb.Remove;
import javax.ejb.Stateful;

import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.Destroy;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import br.com.sigest.dao.FuncionarioDao;
import br.com.sigest.modelo.Funcionario;

/**
 * @author Werick Silva
 */

@Name("usuarioService")
@AutoCreate
@Stateful
public class UsuarioService implements IUsuarioService{

	@In
	FuncionarioDao funcionarioDao;

	public void salvarFuncionarios(Funcionario funcionario) {
		funcionarioDao.salvarFuncionario(funcionario);
		
	}

	public List<Funcionario> pesquisarFuncionarios(Funcionario funcionario){
		return	funcionarioDao.pesquisarFuncionarios(funcionario);
	}
	
	@Destroy
	public void destroy() {
		
	}
	
	@Remove
	public void remove() {
		
	}

	public void excluirFuncionario(Funcionario funcionario) {
		funcionarioDao.excluirFuncionario(funcionario);
		
	}
}
