package br.com.sigest.action;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.jboss.beans.metadata.api.annotations.Create;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import br.com.sigest.enums.EnumCategoria;
import br.com.sigest.modelo.Fornecedor;
import br.com.sigest.modelo.Produto;
import br.com.sigest.service.IEstoqueService;

/**
 * 
 * @author werick silva
 *
 */

@Name("manterProdutoAction")
@AutoCreate
@Scope(ScopeType.CONVERSATION)
public class ManterProdutoAction {
	
	@In
	IEstoqueService estoqueService;

	private Produto produto = new Produto(new Fornecedor());
	
//	@In
//	private List<Fornecedor> listFornecedor;
//	
	private List<Produto> listProdutos = new ArrayList<Produto>();

	private Boolean flagMensagen;
	private boolean flagNovoCadastro;
	private boolean flagPesquisar;
	
	private Produto produtoSelecionado = new Produto(new Fornecedor());
	
	private Integer indice;

	@Create
	public String create(){
		return "/produtos/produtos.xhtml";
	}
	
	@Factory(value="fidAllFornecedor" , scope=ScopeType.APPLICATION)
	public List<Fornecedor> initFornecedor(){
		return estoqueService.fidAllFornecedor();
	}
	
	@Factory(value="categorias" , scope=ScopeType.APPLICATION)
	public EnumCategoria[] initCategorias(){
		
		return EnumCategoria.values();
	}
	
	public void salvarProduto() {
		if (indice != null) {
			listProdutos.set(indice, produto);
		} else {
			listProdutos.add(produto);
		}
		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"Operação realizada com sucesso.", ""));
		setFlagMensagen(true);
		produto = new Produto(new Fornecedor());
		setIndice(null);
	}
	
	public void pesquisarProduto(){
		if(validarCriterioPesquisa()){
			listProdutos = new ArrayList<Produto>();
			listProdutos = estoqueService.pesquisarProduto(produto);
			if(listProdutos.isEmpty()){
				setFlagMensagen(false);
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"Nem um registro encontrado.", ""));
			}			
		}
	}
	
	public void alterar(Produto produto, int indice){
		this.indice = indice;
		this.produto = produto;
	}
	
	public void selecionarProduto(Produto produto){
		setProdutoSelecionado(produto);
		
	}
	
	public void excluirProduto(){
		listProdutos.remove(produtoSelecionado);
		estoqueService.deletarProduto(produto);
	}
	
	public void novoCadastro(){
		setFlagNovoCadastro(true);
		setFlagPesquisar(true);
		produto = new Produto(new Fornecedor());
		produtoSelecionado = new Produto(new Fornecedor());
	}
	
	
	public Boolean validarCriterioPesquisa(){
		if (produto.getNomeProduto().isEmpty()
				&& produto.getCodigo() == null
				&& produto.getDescricao().isEmpty()
				&& produto.getCategoria() == null
				&& produto.getFornecedor() == null) {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"Digite um critério de pesquisa.", ""));
			return false;
		}else{
			return true;
		}
	}
	
	public String cancelar(){
		setFlagNovoCadastro(false);
		setFlagPesquisar(false);
		produto = new Produto(new Fornecedor());
		listProdutos = new ArrayList<Produto>();
		return "/produtos/produtos.xhtml";
	}
	
	
	
	
	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Produto> getListProdutos() {
		return listProdutos;
	}

	public void setListProdutos(List<Produto> listProdutos) {
		this.listProdutos = listProdutos;
	}


	public Boolean getFlagMensagen() {
		return flagMensagen;
	}


	public void setFlagMensagen(Boolean flagMensagen) {
		this.flagMensagen = flagMensagen;
	}


	public boolean isFlagNovoCadastro() {
		return flagNovoCadastro;
	}


	public void setFlagNovoCadastro(boolean flagNovoCadastro) {
		this.flagNovoCadastro = flagNovoCadastro;
	}


	public boolean isFlagPesquisar() {
		return flagPesquisar;
	}


	public void setFlagPesquisar(boolean flagPesquisar) {
		this.flagPesquisar = flagPesquisar;
	}


	public Integer getIndice() {
		return indice;
	}


	public void setIndice(Integer indice) {
		this.indice = indice;
	}


	public Produto getProdutoSelecionado() {
		return produtoSelecionado;
	}


	public void setProdutoSelecionado(Produto produtoSelecionado) {
		this.produtoSelecionado = produtoSelecionado;
	}
	
	
	

}
