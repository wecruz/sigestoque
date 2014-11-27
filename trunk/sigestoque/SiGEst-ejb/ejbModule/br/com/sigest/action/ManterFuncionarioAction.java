package br.com.sigest.action;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import br.com.sigest.enums.CargoFuncao;
import br.com.sigest.modelo.Funcionario;
import br.com.sigest.service.IUsuarioService;

/**
 * @author Werick Silva
 */

@Name("manterFuncionarioAction")
@AutoCreate
@Scope(ScopeType.CONVERSATION)
public class ManterFuncionarioAction {

	
	private Funcionario funcionario = new Funcionario();

	private List<Funcionario> funcionarios = new ArrayList<Funcionario>();
	
	@In
	IUsuarioService usuarioService;
	
	private boolean flagNovoCadastro;
	private boolean flagPesquisar;
	
	private Boolean flagMensagen;
	
	private Integer indice;

	@Factory(value="cargosFuncoes" , scope=ScopeType.APPLICATION)
	public CargoFuncao[] initCargoFuncao(){
		
		return CargoFuncao.values();
	}
	
	
	@Create
	public String create(){
		
		return "/funcionarios/funcionarios.xhtml";
	}
	
	public String novoCadastro(){
		setFlagNovoCadastro(true);
		setFlagPesquisar(true);
		funcionario = new Funcionario();
		return "/funcionarios/funcionarios.xhtml";
	}
	
	public String cancelar(){
		setFlagNovoCadastro(false);
		setFlagPesquisar(false);
		funcionario = new Funcionario();
		return "/funcionarios/funcionarios.xhtml";
	}
	
	public void salvar(){
		if(validarCamposObrigatorios()){
			if(getIndice() == null){
				funcionarios.add(funcionario);				
			}else{
				funcionarios.set(indice, funcionario);
			}
			usuarioService.salvarFuncionarios(funcionario);
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"Operação realizada com sucesso.", ""));
			setFlagMensagen(true);
			funcionario = new Funcionario();
		}
	}
	
	public boolean validarCamposObrigatorios(){
		boolean campo = true;
		if(funcionario.getNome().isEmpty()){
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"O campo Nome e obrigatorio.", ""));
			setFlagMensagen(false);
			campo = false;
		}
		if(funcionario.getRg() == null ){
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"O campo RG e obrigatorio!.", ""));
			campo = false;
		}
		if(funcionario.getCpf() == null ){
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"O campo CPF e obrigatorio!.", ""));
			campo = false;
		}
		if(funcionario.getSenha() == null){
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"O campo Senha e obrigatorio!.", ""));
			campo = false;
		}
//		if(funcionario.getEndereco() == null){
//			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"O campo Endereço e obrigatorio!.", ""));
//			campo =  false;
//		}
//		if(funcionario.getCargoFuncao() == null){
//			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"O campo Cargo/Função e obrigatorio!.", ""));
//			campo =  false;
//		}
		return campo;
	}
	
	public List<Funcionario> pesquisarFuncioanrios(){
		if (validarCriterioPesquisa()) {
			funcionarios = new ArrayList<Funcionario>();		
			funcionarios = usuarioService.pesquisarFuncionarios(funcionario);
			if (funcionarios.isEmpty()) {
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"Nem um Registro Localizado.", ""));
				setFlagMensagen(false);
			}
		}
		return funcionarios;
	}
	
	public void alterar(Funcionario funcionario, int indice){
		this.setIndice(indice);
		setFlagNovoCadastro(true);
		setFlagPesquisar(true);
		this.funcionario = funcionario;
	}
	
	public void excluir(Funcionario funcio){
		funcionarios.remove(funcio);
		usuarioService.excluirFuncionario(funcio);
		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"Operação realizada com sucesso.", ""));
		setFlagMensagen(true);
	}
	
	public boolean validarCriterioPesquisa(){		
		if(funcionario.getNome().isEmpty() && funcionario.getRg() == null && funcionario.getCpf() == null){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Digite um critério de pesquisa.", ""));
			setFlagMensagen(false);
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



}
