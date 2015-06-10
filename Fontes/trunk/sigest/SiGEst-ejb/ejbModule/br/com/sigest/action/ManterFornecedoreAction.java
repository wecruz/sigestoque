package br.com.sigest.action;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import br.com.sigest.modelo.Endereco;
import br.com.sigest.modelo.Fornecedor;
import br.com.sigest.service.IEstoqueService;

/**
 * 
 * @author Werick Silva
 * 
 */

@Name("manterFornecedoreAction")
@AutoCreate
@Scope(ScopeType.CONVERSATION)
public class ManterFornecedoreAction {

	private Fornecedor fornecedor = new Fornecedor(new Endereco());

	@In
	IEstoqueService estoqueService;

	private List<Fornecedor> listFornecedores = new ArrayList<Fornecedor>();

	private Integer indice;

	private Fornecedor fornecedorSelecionado = new Fornecedor(new Endereco());

	@Create
	public String create() {
		return "/fornecedores/fornecedores.xhtml";
	}

	private Integer qntFornecedores = 10;

	public void pesquisarFornecedor() {
		if (validarCriterioPesquisa()) {
			listFornecedores = new ArrayList<Fornecedor>();
			listFornecedores = estoqueService
					.pesquisarFornecedores(getFornecedor());
			if (listFornecedores.isEmpty()) {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Registro n�o Localizado.", ""));
			}
		}
	}

	public void selecionarFornecedor(Fornecedor fornecedor) {
		setFornecedorSelecionado(fornecedor);
	}

	public String salvar() {
		if(validEmail(getFornecedor().getEndereco().getEmail())){
			
			if (getIndice() == null) {
				listFornecedores.add(getFornecedor());
				getFornecedor().setAtivo(true);
				estoqueService.salvar(getFornecedor());
				indice = null;
				pesquisarFornecedor();
				fornecedor = new Fornecedor(new Endereco());
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"Opera��o realizada com sucesso.", ""));
				return "/fornecedores/salvarFornecedores.xhtml";
				
			} else {
				listFornecedores.set(getIndice(), getFornecedor());
				getFornecedor().setAtivo(true);
				estoqueService.salvar(getFornecedor());
				indice = null;
				pesquisarFornecedor();
				fornecedor = new Fornecedor(new Endereco());
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"Opera��o realizada com sucesso.", ""));
				return "/fornecedores/salvarFornecedores.xhtml";
			}
			
			
		}
		
		return "/fornecedores/salvarFornecedores.xhtml";
	}
	
	
	public boolean validEmail(String email) {
	    Pattern p = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$"); 
	    Matcher m = p.matcher(email); 
	    if (m.find()){
	      return true;
	    }
	    else{
	      FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"O email "+email+" e inv�lido", ""));
	      return false;
	    }  
	 }

	public boolean validarCriterioPesquisa() {
		if (fornecedor.getNome().isEmpty() && fornecedor.getCnpj().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Digite um crit�rio de pesquisa.", ""));
			return false;
		} else {
			return true;
		}
	}

	public String alterar(int indice, Fornecedor fornecedor) {
		this.indice = indice;
		
		this.fornecedor = fornecedor;
		return "/fornecedores/salvarFornecedores.xhtml";
	}

	public void excluir() {
		listFornecedores.remove(getFornecedorSelecionado());
		getFornecedorSelecionado().setAtivo(false);
		estoqueService.salvar(getFornecedorSelecionado());
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Opera��o realizada com sucesso.", ""));
		fornecedor = new Fornecedor();
	}

	public String cancelar() {
		fornecedor = new Fornecedor(new Endereco());
		listFornecedores = new ArrayList<Fornecedor>();
		fornecedorSelecionado = new Fornecedor(new Endereco());
		return "/fornecedores/fornecedores.xhtml";
	}

	public String novoCadastro() {
		fornecedor = new Fornecedor(new Endereco());
		listFornecedores = new ArrayList<Fornecedor>();
		fornecedorSelecionado = new Fornecedor(new Endereco());
		return "/fornecedores/salvarFornecedores.xhtml";
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setQntFornecedores(Integer qntFornecedores) {
		this.qntFornecedores = qntFornecedores;
	}

	public Integer getQntFornecedores() {
		return qntFornecedores;
	}

	public List<Fornecedor> getListFornecedores() {
		return listFornecedores;
	}

	public void setListFornecedores(List<Fornecedor> listFornecedores) {
		this.listFornecedores = listFornecedores;
	}


	public Integer getIndice() {
		return indice;
	}

	public void setIndice(Integer indice) {
		this.indice = indice;
	}

	public Fornecedor getFornecedorSelecionado() {
		return fornecedorSelecionado;
	}

	public void setFornecedorSelecionado(Fornecedor fornecedorSelecionado) {
		this.fornecedorSelecionado = fornecedorSelecionado;
	}



}
