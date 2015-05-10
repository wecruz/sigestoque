package org.domain.sigest.session;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.log.Log;
import org.jboss.seam.security.Credentials;
import org.jboss.seam.security.Identity;

import br.com.sigest.modelo.Funcionario;
import br.com.sigest.service.IUsuarioService;

@Name("authenticator")
@Scope(ScopeType.SESSION)
public class Authenticator
{
	
	@In
	private IUsuarioService usuarioService;
	
    @Logger private Log log;

    @In Identity identity;
    @In Credentials credentials;
    
    @Out(required = false, scope = ScopeType.SESSION)
	private Funcionario usuarioLogado;
    
    
    
    

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


	

}
