package br.com.sigest.service;

import java.util.List;

import br.com.sigest.modelo.Fornecedor;

public interface IEstoqueService {
	
	
	void salvar (Fornecedor fornecedor);
	
	List<Fornecedor> pesquisarFornecedores(Fornecedor fornecedor);
	
	
//	List<Estado> pesquisarTodosEstados();
//	
//	List<Cidade> pesquisarCidadesPorEstados(Estado estado);
	
	void destroy();
	
	void remove();

}
