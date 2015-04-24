package br.com.sigest.modelo.dto;

import br.com.sigest.modelo.Cliente;
import br.com.sigest.modelo.Fornecedor;
import br.com.sigest.modelo.Produto;

public class PedidoDTO {

	private Cliente cliente = new Cliente();
	
	private Produto produto = new Produto();
	
	private Fornecedor fornecedor = new Fornecedor();

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}
	
	
}
