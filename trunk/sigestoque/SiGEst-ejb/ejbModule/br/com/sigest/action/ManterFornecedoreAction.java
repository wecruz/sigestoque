package br.com.sigest.action;

import java.util.ArrayList;
import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import br.com.sigest.enums.Estado;
import br.com.sigest.modelo.Fornecedor;

@Name("manterFornecedoreAction")
@AutoCreate
@Scope(ScopeType.CONVERSATION)
public class ManterFornecedoreAction {

	
	private Fornecedor fornecedor = new Fornecedor();

	
	
 	
	
	private Integer qntFornecedores = 10;
	@Factory(value="estados" , scope=ScopeType.APPLICATION)
	public Estado[] iniEstados(){
		return Estado.values();
	}
	
	
	public void salvar(){
		getFornecedor();
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


	



}
