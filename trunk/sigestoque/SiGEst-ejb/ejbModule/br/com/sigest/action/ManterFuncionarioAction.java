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

import br.com.sigest.enums.CargoFuncao;
import br.com.sigest.modelo.Funcionario;
import br.com.sigest.service.IUsuarioService;


@Name("manterFuncionarioAction")
@AutoCreate
@Scope(ScopeType.CONVERSATION)
public class ManterFuncionarioAction {

	
	private Funcionario funcionario = new Funcionario();

	private List<Funcionario> funcionarios = new ArrayList<Funcionario>();
	
	@In
	IUsuarioService usuarioService;
	
	@Factory(value="cargosFuncoes" , scope=ScopeType.APPLICATION)
	public CargoFuncao[] initCargoFuncao(){
		return CargoFuncao.values();
	}
	
	public String novoCadastro(){
		funcionario = new Funcionario();
		return "/funcionarios/funcionarios.xhtml";
	}
	
	public void salvar(){
		if(validarCamposObrigatorios()){
			usuarioService.salvarFuncionarios(funcionario);
			funcionarios.add(funcionario);
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"Operação realizada com sucesso.", ""));
			funcionario = new Funcionario();
		}
	}
	
	public boolean validarCamposObrigatorios(){
		if(funcionario.getNome().isEmpty()){
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"O campo Nome e obrigatorio!.", ""));
			return false;
		}
		return true;
	}
	
	public List<Funcionario> pesquisarFuncioanrios(){
		if (validarCriterioPesquisa()) {
			funcionarios = new ArrayList<Funcionario>();		
			funcionarios = usuarioService.pesquisarFuncionarios(funcionario);
			if (funcionarios.isEmpty()) {
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"Nem um Registro Localizado.", ""));
			}
		}
		return funcionarios;
	}
	
	public void alterar(Funcionario funcionario){
		this.funcionario = funcionario;
	}
	
	public void excluir(Funcionario funcio){
		usuarioService.excluirFuncionario(funcio);
		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"Operação realizada com sucesso.", ""));
	}
	
	public boolean validarCriterioPesquisa(){		
		if(funcionario.getNome().isEmpty() && funcionario.getRg() == null && funcionario.getCpf() == null){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Digite um critério de pesquisa.", ""));
			return false;
		}else{
			return true;
		}
	}
	
	
	
	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}


	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}


	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}


	
}
