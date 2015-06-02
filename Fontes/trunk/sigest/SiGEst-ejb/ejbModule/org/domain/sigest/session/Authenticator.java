package org.domain.sigest.session;

import java.io.Serializable;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.log.Log;
import org.jboss.seam.security.Credentials;
import org.jboss.seam.security.Identity;

import br.com.sigest.enums.EnumCargoFuncao;
import br.com.sigest.modelo.Funcionario;
import br.com.sigest.service.IUsuarioService;

@Name("authenticator")
@Scope(ScopeType.SESSION)
public class Authenticator implements Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@In
	private IUsuarioService usuarioService;
	
    @Logger private Log log;

    @In Identity identity;
    @In Credentials credentials;
    
    @Out(required = false, scope = ScopeType.SESSION)
	private Funcionario usuarioLogado;
    
    
    private boolean menuCaixa;
    private boolean menuAdministrador;
    private boolean menuVendendor;
    	
    
    public String logout() {
    	usuarioLogado = null;
    	identity.logout();
    	return "sair";
    }
    
    

    public boolean authenticate()
    {
    	
    	usuarioLogado = usuarioService.login(credentials.getUsername(), credentials.getPassword());
    	
    	
    	if(usuarioLogado !=null){
    		log.info("authenticating {0}", credentials.getUsername());
    		/**
    		 *	 escrever sua lógica de autenticação aqui,
   				 retornar true se a autenticação foi
     			bem-sucedida, caso contrário, false
    		 */
    		
    		credentials.setUsername(usuarioLogado.getNome());
    		identity.addRole(usuarioLogado.getNome());
    		
    		if(usuarioLogado.getCargoFuncao().equals(EnumCargoFuncao.ADMINISTRADOR)){
    			menuAdministrador = true;
    			menuCaixa = false;
    			menuVendendor = false;
    		}else if(usuarioLogado.getCargoFuncao().equals(EnumCargoFuncao.CAIXA)){
    			menuCaixa = true;
    			menuAdministrador = false;
    			menuVendendor = false;
    		} else if(usuarioLogado.getCargoFuncao().equals(EnumCargoFuncao.VENDENDO)){
    			menuVendendor = true;
    			menuCaixa = false;
    			menuAdministrador = false;
    		}

    		return true;
    	}
        return false;
    }

    
    
    
	public void setUsuarioLogado(Funcionario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public Funcionario getUsuarioLogado() {
		return usuarioLogado;
	}



	public void setMenuCaixa(boolean menuCaixa) {
		this.menuCaixa = menuCaixa;
	}



	public boolean isMenuCaixa() {
		return menuCaixa;
	}



	public boolean isMenuAdministrador() {
		return menuAdministrador;
	}



	public void setMenuAdministrador(boolean menuAdministrador) {
		this.menuAdministrador = menuAdministrador;
	}



	public boolean isMenuVendendor() {
		return menuVendendor;
	}



	public void setMenuVendendor(boolean menuVendendor) {
		this.menuVendendor = menuVendendor;
	}

	

}
