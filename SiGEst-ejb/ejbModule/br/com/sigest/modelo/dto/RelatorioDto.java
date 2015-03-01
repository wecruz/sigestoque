package br.com.sigest.modelo.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.sigest.modelo.Fornecedor;
import br.com.sigest.modelo.Produto;

public class RelatorioDto {

	private Fornecedor fornecedor = new Fornecedor();

	private List<Produto> listProduto = new ArrayList<Produto>();
	
	private Produto produto = new Produto();
	
	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public List<Produto> getListProduto() {
		return listProduto;
	}

	public void setListProduto(List<Produto> listProduto) {
		this.listProduto = listProduto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Produto getProduto() {
		return produto;
	}
}
