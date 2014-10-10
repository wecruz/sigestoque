package br.com.sigest.service;

import java.util.List;

import br.com.sigest.modelo.Cidade;
import br.com.sigest.modelo.Estado;

public interface IEstoqueService {
	
	
	
	List<Estado> pesquisarTodosEstados();
	
	List<Cidade> pesquisarCidadesPorEstados(Estado estado);
	
	void destroy();
	
	void remove();

}
