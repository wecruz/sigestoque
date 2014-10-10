package br.com.sigest.action;

import java.util.ArrayList;
import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import br.com.sigest.enums.Cidade;
import br.com.sigest.enums.Estado;
import br.com.sigest.modelo.Fornecedor;

@Name("manterFornecedoreAction")
@AutoCreate
@Scope(ScopeType.CONVERSATION)
public class ManterFornecedoreAction {

	
	private Fornecedor fornecedor = new Fornecedor();

	
	private Estado estado;
	
	private Cidade cidade;
 	
	
	@Create
	public String create(){
		return "/fornecedores/fornecedores.xhtml";
	}
	
	private Integer qntFornecedores = 10;
	
	@Factory(value="estados" , scope=ScopeType.APPLICATION)
	public Estado[] iniEstados(){
		return Estado.values();
	}
	
	@Factory(value="cidades" , scope=ScopeType.APPLICATION)
	public Cidade[] iniCidades(){
		return Cidade.values();
	}
	
	public List<Cidade> recuperarCidadesPorId(){
		List<Cidade> cidaddes = new ArrayList<Cidade>();
		for (Cidade cidade : iniCidades()) {
			if(cidade.getEstado() == fornecedor.getEstado().getId()){
				cidaddes.add(cidade);
			}
		}
		return cidaddes;
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

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}


	



}
