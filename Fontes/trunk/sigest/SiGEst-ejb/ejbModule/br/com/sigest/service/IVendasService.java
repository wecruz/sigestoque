package br.com.sigest.service;

import java.util.List;

import br.com.sigest.modelo.Cliente;
import br.com.sigest.modelo.Fornecedor;
import br.com.sigest.modelo.Produto;
import br.com.sigest.modelo.Venda;

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
	
	List<Produto> pesquisarProdutoFornecedor(Fornecedor fornecedor);
	
	List<Produto> fildAllProduto();
	
	List<Produto> pesquisarProdutos(Produto produto);
	
	void salvaPedidoVenda(Venda venda);
	
	List<Venda> fildAllVendas();
	
	List<Venda> pesquisarVendasCliente(Cliente cliente);
	
	void excluirPedido(Venda venda);

	void destroy();
	
	void remove();
}
