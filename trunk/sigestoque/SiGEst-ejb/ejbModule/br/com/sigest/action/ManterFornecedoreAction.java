package br.com.sigest.action;

import java.util.ArrayList;
import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import br.com.sigest.modelo.Cidade;
import br.com.sigest.modelo.Estado;
import br.com.sigest.modelo.Fornecedor;
import br.com.sigest.service.IEstoqueService;

@Name("manterFornecedoreAction")
@AutoCreate
@Scope(ScopeType.CONVERSATION)
public class ManterFornecedoreAction {

	
	private Fornecedor fornecedor = new Fornecedor();

	
	
	@In
	IEstoqueService estoqueService;
 	
	
	private List<Estado> estados = new ArrayList<Estado>();
	
	
	private List<Cidade> cidades = new ArrayList<Cidade>();
	
	private List<Fornecedor> listFornecedores = new ArrayList<Fornecedor>();
	
	
	@Create
	public String create(){
		
		return "/fornecedores/fornecedores.xhtml";
	}
	
	private Integer qntFornecedores = 10;
	
	@Factory(value="estados" , scope=ScopeType.APPLICATION)
	public List<Estado> popularEstados(){
		estados = estoqueService.pesquisarTodosEstados();
		return estados;
	}
	
	public List<Cidade> pesquisarCidadesPorEstados(){
//		cidades =	estoqueService.pesquisarCidadesPorEstados(fornecedor.getEstado());
		return cidades;
	}

	
	public void salvar(){
		listFornecedores.add(getFornecedor());
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


	



}
