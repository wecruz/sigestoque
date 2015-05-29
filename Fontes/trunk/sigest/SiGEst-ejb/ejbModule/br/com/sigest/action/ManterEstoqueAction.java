package br.com.sigest.action;

import java.util.ArrayList;
import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import br.com.sigest.modelo.Produto;
import br.com.sigest.service.IEstoqueService;

@Name("manterEstoqueAction")
@AutoCreate
@Scope(ScopeType.CONVERSATION)
public class ManterEstoqueAction {

	@In
	IEstoqueService estoqueService;
	
	private Integer quantidadeProdutoEstoque = 0;
	
	private List<Produto> listaProdutos = new ArrayList<Produto>();
	
	@Create
	public String create(){
		listaProdutos = estoqueService.fildAllProduto();
		
		for (Produto produto : listaProdutos) {
			quantidadeProdutoEstoque += produto.getQuantidade();
		}
		return "/estoque/estoque.xhtml";
		
	}


	public void setQuantidadeProdutoEstoque(Integer quantidadeProdutoEstoque) {
		this.quantidadeProdutoEstoque = quantidadeProdutoEstoque;
	}


	public Integer getQuantidadeProdutoEstoque() {
		return quantidadeProdutoEstoque;
	}


	public void setListaProdutos(List<Produto> listaProdutos) {
		this.listaProdutos = listaProdutos;
	}


	public List<Produto> getListaProdutos() {
		return listaProdutos;
	}
}
