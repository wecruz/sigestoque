package br.com.sigest.service;

import java.util.List;

import br.com.sigest.modelo.Cliente;
import br.com.sigest.modelo.Fornecedor;
import br.com.sigest.modelo.Produto;

public interface IVendasService {
	
	/**
	 * Metodo Responsavel por salvar Cliente
	 * @param cliente
	 */
	void salvarCliente(Cliente cliente);
	
	/**
	 * Metodo Responsavel por Deletar Cliente
	 * @param cliente
	 */
	void removerCliente(Cliente cliente);
	
	
	List<Cliente> pesquisarClientes(Cliente cliente);
	
	public List<Produto> pesquisarProdutoFornecedor(Fornecedor fornecedor);

	void destroy();
	
	void remove();
}
