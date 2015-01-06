package br.com.sigest.service;

import java.util.List;

import br.com.sigest.modelo.Fornecedor;
import br.com.sigest.modelo.Produto;

public interface IEstoqueService {
	
	
	void salvar (Fornecedor fornecedor);
	
	List<Fornecedor> pesquisarFornecedores(Fornecedor fornecedor);
	
	void excluirFornecedor(Fornecedor fornecedor);
	
	List<Fornecedor> fidAllFornecedor();
	
	void salvarProduto(Produto produto);
	
	List<Produto> pesquisarProduto(Produto produto);
	 
	void deletarProduto(Produto produto);
	
//	List<Estado> pesquisarTodosEstados();
//	
//	List<Cidade> pesquisarCidadesPorEstados(Estado estado);
	
	void destroy();
	
	void remove();

}
