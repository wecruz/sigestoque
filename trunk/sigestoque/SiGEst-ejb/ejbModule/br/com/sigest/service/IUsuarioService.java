package br.com.sigest.service;


import java.util.List;

import br.com.sigest.modelo.Funcionario;

/**
 * @author Werick Silva
 */
public interface IUsuarioService {

	
	void salvarFuncionarios(Funcionario funcionario);
	
	
	List<Funcionario> pesquisarFuncionarios(Funcionario funcionario);
	
	void excluirFuncionario(Funcionario funcionario);
	
	void destroy();
	
	void remove();
	
}
