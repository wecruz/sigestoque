package br.com.sigest.service;

import br.com.sigest.modelo.Funcionario;

public interface IUsuarioService {

	
	void salvarFuncionarios(Funcionario funcionario);
	
	void destroy();
	
	void remove();
}
