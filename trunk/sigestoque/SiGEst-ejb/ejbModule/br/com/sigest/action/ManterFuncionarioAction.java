package br.com.sigest.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import br.com.sigest.modelo.Endereco;
import br.com.sigest.modelo.Funcionario;
import br.com.sigest.service.IUsuarioService;
import br.com.sigest.util.RelatorioUtil;

/**
 * @author Werick Silva
 */

@Name("manterFuncionarioAction")
@AutoCreate
@Scope(ScopeType.CONVERSATION)
public class ManterFuncionarioAction {

	
	private Funcionario funcionario = new Funcionario(new Endereco());

	private List<Funcionario> funcionarios = new ArrayList<Funcionario>();
	
	@In
	IUsuarioService usuarioService;
	
	private boolean flagNovoCadastro;
	private boolean flagPesquisar;
	
	private Boolean flagMensagen;
	
	private Integer indice;
	
	private Funcionario funcionarioSelecionado = new Funcionario(new Endereco());
	

	@In
	private RelatorioUtil relatorioUtil;
	
	
	@Factory(value="cargosFuncoes" , scope=ScopeType.APPLICATION)
	public CargoFuncao[] initCargoFuncao(){
		
		return CargoFuncao.values();
	}
	
	
	@Create
	public String create(){
		
		return "/funcionarios/funcionarios.xhtml";
	}
	
	public void novoCadastro(){
		setFlagNovoCadastro(true);
		setFlagPesquisar(true);
		funcionario = new Funcionario();
	}
	
	public String cancelar(){
		setFlagNovoCadastro(false);
		setFlagPesquisar(false);
		funcionario = new Funcionario();
		return "/funcionarios/funcionarios.xhtml";
	}
	
	public void salvar(){
		if(validarCamposObrigatorios()){
			funcionario.getEndereco().setFuncionario(funcionario);
			if(getIndice() == null){
				funcionarios.add(funcionario);				
			}else{
				funcionarios.set(indice, funcionario);
			}
			usuarioService.salvarFuncionarios(funcionario);
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"Opera��o realizada com sucesso.", ""));
			setFlagMensagen(true);
			funcionario = new Funcionario(new Endereco());
			setFlagMensagen(null);
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
//			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"O campo Endere�o e obrigatorio!.", ""));
//			campo =  false;
//		}
//		if(funcionario.getCargoFuncao() == null){
//			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"O campo Cargo/Fun��o e obrigatorio!.", ""));
//			campo =  false;
//		}
		return campo;
	}
	
	public void pesquisarFuncioanrios(){
		if (validarCriterioPesquisa()) {
			funcionarios = new ArrayList<Funcionario>();		
			funcionarios = usuarioService.pesquisarFuncionarios(funcionario);
			if (funcionarios.isEmpty()) {
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"Nem um Registro Localizado.", ""));
				setFlagMensagen(false);
			}
		}
	}
	
	public void alterar(Funcionario funcionario, int indice){
		this.setIndice(indice);
		setFlagNovoCadastro(true);
		setFlagPesquisar(true);
		this.funcionario = funcionario;
	}
	
	public void selecionarFuncionario(Funcionario funcio){
		setFuncionarioSelecionado(funcio);
	}
	
	public void excluir(){
		funcionarios.remove(getFuncionarioSelecionado());
		usuarioService.excluirFuncionario(getFuncionarioSelecionado());
		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"Opera��o realizada com sucesso.", ""));
		setFlagMensagen(true);
		funcionarioSelecionado = new Funcionario();
	}
	
	public boolean validarCriterioPesquisa(){		
		if(funcionario.getNome().isEmpty() && funcionario.getRg() == null && funcionario.getCpf() == null){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Digite um crit�rio de pesquisa.", ""));
			setFlagMensagen(false);
			return false;
		}else{
			return true;
		}
	}
	
	
	
	public String gerarRelatorio() {
		
		List<String> teste = new ArrayList<String>();
		final Collection<?> list = teste;
        final Map<String, Object> params = new HashMap<String, Object>();
		
		
		try {
			return	relatorioUtil.imprimir("teste", params, list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
		
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


	public Funcionario getFuncionarioSelecionado() {
		return funcionarioSelecionado;
	}


	public void setFuncionarioSelecionado(Funcionario funcionarioSelecionado) {
		this.funcionarioSelecionado = funcionarioSelecionado;
	}



}
