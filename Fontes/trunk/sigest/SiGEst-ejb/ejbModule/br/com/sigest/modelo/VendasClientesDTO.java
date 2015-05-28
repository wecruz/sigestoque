package br.com.sigest.modelo;

import java.util.ArrayList;
import java.util.List;

public class VendasClientesDTO {
	
	private Cliente cliente = new  Cliente();
	
	private List<Venda> vendas  = new ArrayList<Venda>();
	
	private List<Produto> produtos = new ArrayList<Produto>();
	
	private List<Fornecedor> fornecedores = new ArrayList<Fornecedor>();
	
	private Produto produto = new Produto();
	
	private Fornecedor fornecedor = new  Fornecedor();
	
	private Integer quantidadeProduto = 1;
	
	private Integer quantidadeMaxProduto = 1;
	
	private List<VendaProduto> listVendaProduto = new ArrayList<VendaProduto>();
	

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Venda> getVendas() {
		return vendas;
	}

	public void setVendas(List<Venda> vendas) {
		this.vendas = vendas;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public List<Fornecedor> getFornecedores() {
		return fornecedores;
	}

	public void setFornecedores(List<Fornecedor> fornecedores) {
		this.fornecedores = fornecedores;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setQuantidadeProduto(Integer quantidadeProduto) {
		this.quantidadeProduto = quantidadeProduto;
	}

	public Integer getQuantidadeProduto() {
		return quantidadeProduto;
	}

	public void setListVendaProduto(List<VendaProduto> listVendaProduto) {
		this.listVendaProduto = listVendaProduto;
	}

	public List<VendaProduto> getListVendaProduto() {
		return listVendaProduto;
	}

	public void setQuantidadeMaxProduto(Integer quantidadeMaxProduto) {
		this.quantidadeMaxProduto = quantidadeMaxProduto;
	}

	public Integer getQuantidadeMaxProduto() {
		return quantidadeMaxProduto;
	}

	
	
	
}
