package br.com.sigest.service;

import javax.ejb.Remove;
import javax.ejb.Stateful;

import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.Destroy;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import br.com.sigest.dao.FuncionarioDao;
import br.com.sigest.modelo.Funcionario;


@Name("usuarioService")
@AutoCreate
@Stateful
public class UsuarioService implements IUsuarioService{

	@In
	FuncionarioDao funcionarioDao;

	public void salvarFuncionarios(Funcionario funcionario) {
		funcionarioDao.salvarFuncionario(funcionario);
		
	}

	@Destroy
	public void destroy() {
		
	}
	
	@Remove
	public void remove() {
		
	}
}
