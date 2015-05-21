package br.com.sigest.action;

import java.util.ArrayList;
import java.util.List;

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
								"Nenhum registro encontrado.", ""));
			}
		}
	}

	public void selecionarFornecedor(Fornecedor fornecedor) {
		setFornecedorSelecionado(fornecedor);
	}

	public void salvar() {
		fornecedor.getEndereco().setFornecedor(getFornecedor());
		if (getIndice() == null) {
			listFornecedores.add(getFornecedor());
		} else {
			listFornecedores.set(getIndice(), getFornecedor());
		}
		estoqueService.salvar(getFornecedor());
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Operação realizada com sucesso.", ""));
		this.fornecedor = new Fornecedor(new Endereco());
	}

	public boolean validarCriterioPesquisa() {
		if (fornecedor.getNome().isEmpty() && fornecedor.getCnpj().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Digite um critério de pesquisa.", ""));
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
		estoqueService.excluirFornecedor(getFornecedorSelecionado());
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Operação realizada com sucesso.", ""));
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
