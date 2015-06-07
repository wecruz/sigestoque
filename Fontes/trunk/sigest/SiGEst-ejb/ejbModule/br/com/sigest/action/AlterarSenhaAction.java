package br.com.sigest.action;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import org.domain.sigest.session.Authenticator;

import br.com.sigest.modelo.Fornecedor;
import br.com.sigest.modelo.Funcionario;
import br.com.sigest.service.IUsuarioService;

/**
 * @author Werick Silva
 */

@Name("alterarSenhaAction")
@AutoCreate
@Scope(ScopeType.CONVERSATION)
public class AlterarSenhaAction {

	@In
	private IUsuarioService usuarioService;
	
	@In
	Authenticator authenticator;
	
	private String senhaAtual;
    private String novaSenha;
    private String confimarNovaSenha;
    
    private Funcionario usuarioLogado = new Funcionario();
    
    public void alterarSenha(){
    	if(!authenticator.getUsuarioLogado().getSenha().equals(senhaAtual)){
    		  FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Senha Atual Incorreta.", ""));
    		  return;
    	}else if(!novaSenha.equals(confimarNovaSenha)){
    		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Nova Senha não Pode ser diferente Comfirmar Nova Senha.", ""));
    		return;
    	}
    	
    	authenticator.getUsuarioLogado().setSenha(novaSenha);
    	
    	setUsuarioLogado(authenticator.getUsuarioLogado());
    	
    	usuarioService.salvarFuncionarios(getUsuarioLogado());
    	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Senha Alterada Com Sucesso.", ""));
    	
    	setNovaSenha(null);
    	setConfimarNovaSenha(null);
    	setSenhaAtual(null);
    	
    	
    }



	public String getSenhaAtual() {
		return senhaAtual;
	}



	public void setSenhaAtual(String senhaAtual) {
		this.senhaAtual = senhaAtual;
	}



	public String getNovaSenha() {
		return novaSenha;
	}



	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}



	public String getConfimarNovaSenha() {
		return confimarNovaSenha;
	}



	public void setConfimarNovaSenha(String confimarNovaSenha) {
		this.confimarNovaSenha = confimarNovaSenha;
	}



	public Funcionario getUsuarioLogado() {
		return usuarioLogado;
	}



	public void setUsuarioLogado(Funcionario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}



	
    
    
}
