package br.com.sigest.action;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

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
	
	private List<Fornecedor> listFornecedor = new ArrayList<Fornecedor>();
	
	private List<Produto> listProdutos = new ArrayList<Produto>();

	private Boolean flagMensagen;
	private boolean flagNovoCadastro;
	private boolean flagPesquisar;
	
	
	private Integer indice;

	
	@Factory(value="fidAllFornecedor" , scope=ScopeType.APPLICATION)
	public List<Fornecedor> initFornecedor(){
		return listFornecedor = estoqueService.fidAllFornecedor();
	}
	
	
	public void salvarProduto(){
		listProdutos.add(produto);
		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"Operação realizada com sucesso.", ""));
		setFlagMensagen(true);
		produto = new Produto(new Fornecedor());
	}
	
	public void pesquisarProduto(){
		listProdutos = new ArrayList<Produto>();
		listProdutos = estoqueService.pesquisarProduto(produto);
		if(listProdutos.isEmpty()){
			setFlagMensagen(false);
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"Nem um registro encontrado.", ""));
		}
	}
	
	public void alterar(Produto produto, int indice){
		this.indice = indice;
		this.produto = produto;
	}
	
	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Fornecedor> getListFornecedor() {
		return listFornecedor;
	}




	public void setListFornecedor(List<Fornecedor> listFornecedor) {
		this.listFornecedor = listFornecedor;
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
	
	
	

}
