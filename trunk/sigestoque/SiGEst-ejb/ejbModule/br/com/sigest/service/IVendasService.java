package br.com.sigest.service;

import java.util.List;

import br.com.sigest.modelo.Cliente;

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

	void destroy();
	
	void remove();
}
