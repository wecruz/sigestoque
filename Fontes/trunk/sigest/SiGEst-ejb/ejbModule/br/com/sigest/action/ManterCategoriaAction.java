package br.com.sigest.action;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import br.com.sigest.modelo.Categoria;
import br.com.sigest.modelo.Cliente;
import br.com.sigest.modelo.Endereco;
import br.com.sigest.modelo.Produto;
import br.com.sigest.modelo.UploadedFile;
import br.com.sigest.service.IEstoqueService;

/**
 * 
 * @author Werick Silva
 * 
 */

@Name("manterCategoriaAction")
@AutoCreate
@Scope(ScopeType.CONVERSATION)
public class ManterCategoriaAction {
	
	private Categoria categoria = new Categoria();

	private List<Categoria> listCategoria = new ArrayList<Categoria>();
	
	@In
	private IEstoqueService estoqueService;
	
	private Integer indice;
	
	private Categoria categoriaSelecionada = new Categoria();
	
	public void salvarCategoria(){
		if (indice != null) {
			listCategoria.set(indice, categoria);
		} else {
			listCategoria.add(categoria);
		}
		
		listCategoria.add(categoria);
		
		
		estoqueService.salvarCategoria(categoria);
		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"Operação realizada com sucesso.", ""));
	}
	
	
	public void pesquisarCategoria(){
		if(validarCriterioPesquisa()){
			listCategoria = new ArrayList<Categoria>();
			listCategoria = estoqueService.pesquisarCategoria(this.categoria);
			if (listCategoria.isEmpty()) {
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"Nenhum Registro Localizado.", ""));
			}
		}
		
	}
	
	
	public String novoCadastro(){
		categoria = new Categoria();
		return "salvarCategoria";
	}
	
	public boolean validarCriterioPesquisa(){		
		if(categoria.getNome().isEmpty() && categoria.getDescricao().isEmpty()){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Digite um critério de pesquisa.", ""));
			return false;
		}else{
			return true;
		}
	}
	
	public String alterar(Categoria categoria, int indice) {
		this.setIndice(indice);
		this.categoria = categoria;
		
		return "salvarCategoria";
	}
	
	public void selecionarCategoria(Categoria categoria){
		setCategoriaSelecionada(categoria);
		
	}
	
	public void excluirCategoria(){
		listCategoria.remove(categoriaSelecionada);
		
		estoqueService.excluirCategoria(categoriaSelecionada);
		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"Operação realizada com sucesso.", ""));
		categoria = new Categoria();
	}
	
	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public List<Categoria> getListCategoria() {
		return listCategoria;
	}

	public void setListCategoria(List<Categoria> listCategoria) {
		this.listCategoria = listCategoria;
	}


	public void setIndice(Integer indice) {
		this.indice = indice;
	}


	public Integer getIndice() {
		return indice;
	}


	public void setCategoriaSelecionada(Categoria categoriaSelecionada) {
		this.categoriaSelecionada = categoriaSelecionada;
	}


	public Categoria getCategoriaSelecionada() {
		return categoriaSelecionada;
	}

}
