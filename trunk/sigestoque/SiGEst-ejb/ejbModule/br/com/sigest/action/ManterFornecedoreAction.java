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

import br.com.sigest.modelo.Cidade;
import br.com.sigest.modelo.Endereco;
import br.com.sigest.modelo.Estado;
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
 	
	
	private List<Estado> estados = new ArrayList<Estado>();
	
	
	private List<Cidade> cidades = new ArrayList<Cidade>();
	
	private List<Fornecedor> listFornecedores = new ArrayList<Fornecedor>();
	
	
	private Boolean flagMensagen;
	
	
	private Integer indice;
	
	private Fornecedor fornecedorSelecionado = new Fornecedor(new Endereco());
	
	
	@Create
	public String create(){
		return "/fornecedores/fornecedores.xhtml";
	}
	
	private Integer qntFornecedores = 10;
	
	
	public void pesquisarFornecedor() {
		if (validarCriterioPesquisa()) {
			listFornecedores = new ArrayList<Fornecedor>();
			listFornecedores = estoqueService.pesquisarFornecedores(getFornecedor());
			if(listFornecedores.isEmpty()){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Nem um registro encontrado.", ""));
			}
		}
	}
	
	
	
	public void selecionarFornecedor(Fornecedor fornecedor){
		setFornecedorSelecionado(fornecedor);
	}
	
	
	
//	@Factory(value="estados" , scope=ScopeType.APPLICATION)
//	public List<Estado> popularEstados(){
//		estados = estoqueService.pesquisarTodosEstados();
//		return estados;
//	}
	
	public List<Cidade> pesquisarCidadesPorEstados(){
//		cidades =	estoqueService.pesquisarCidadesPorEstados(fornecedor.getEstado());
		return cidades;
	}

	
	public void salvar() {
		if (validarCamposObrigatorios()) {
			fornecedor.getEndereco().setFornecedor(getFornecedor());
			if(getIndice() == null){
				listFornecedores.add(getFornecedor());				
			}else{
				listFornecedores.set(getIndice(), getFornecedor());
			}
			estoqueService.salvar(getFornecedor());
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"Operação realizada com sucesso.", ""));
			this.fornecedor = new Fornecedor(new Endereco());
			setFlagMensagen(true);
		}
	}
	
	public boolean validarCriterioPesquisa(){		
		if(fornecedor.getNome().isEmpty()){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Digite um critério de pesquisa.", ""));
			setFlagMensagen(false);
			return false;
		}else{
			return true;
		}
	}
	
	
	public void alterar(int indice, Fornecedor fornecedor){
		this.indice = indice;
		this.fornecedor = fornecedor;
	}
	
	
	public void excluir(){
		listFornecedores.remove(getFornecedorSelecionado());
		estoqueService.excluirFornecedor(getFornecedorSelecionado());
		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"Operação realizada com sucesso.", ""));
		setFlagMensagen(true);
	}
	public void cancelar(){
		
	}
	
	
	
	public boolean validarCamposObrigatorios(){
		boolean campo = true;
		if(getFornecedor().getNome().isEmpty()){
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"O campo Nome e obrigatorio.", ""));
			setFlagMensagen(false);
			campo = false;
		}
		if(getFornecedor().getCnpj() == null){
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"O campo CNPJ e obrigatorio.", ""));
			setFlagMensagen(false);
			campo = false;
		}
		if(getFornecedor().getEndereco().getEndereco().isEmpty()){
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"O campo Endereço e obrigatorio.", ""));
			setFlagMensagen(false);
			campo = false;
		}
		if(getFornecedor().getEndereco().getCidade().isEmpty()){
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"O campo Cidade e obrigatorio.", ""));
			setFlagMensagen(false);
			campo = false;
		}
		
		return campo;
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


	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	public List<Fornecedor> getListFornecedores() {
		return listFornecedores;
	}

	public void setListFornecedores(List<Fornecedor> listFornecedores) {
		this.listFornecedores = listFornecedores;
	}


	public Boolean getFlagMensagen() {
		return flagMensagen;
	}


	public void setFlagMensagen(Boolean flagMensagen) {
		this.flagMensagen = flagMensagen;
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
