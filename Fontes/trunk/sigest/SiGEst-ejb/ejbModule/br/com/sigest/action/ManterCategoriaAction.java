package br.com.sigest.action;

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
	
	public void salvarCategoria(){
		estoqueService.salvarCategoria(categoria);
		listCategoria.add(categoria);
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

}
