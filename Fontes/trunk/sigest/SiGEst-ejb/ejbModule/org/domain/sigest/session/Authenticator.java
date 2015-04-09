package org.domain.sigest.session;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.log.Log;
import org.jboss.seam.security.Credentials;
import org.jboss.seam.security.Identity;

import br.com.sigest.modelo.Funcionario;
import br.com.sigest.service.IUsuarioService;

@Name("authenticator")
public class Authenticator
{
	
	@In
	private IUsuarioService usuarioService;
	
    @Logger private Log log;

    @In Identity identity;
    @In Credentials credentials;

    public boolean authenticate()
    {
    	
    	Funcionario funcionario = usuarioService.login(credentials.getUsername(), credentials.getPassword());
    	
    	
    	if(funcionario !=null){
    		log.info("authenticating {0}", credentials.getUsername());
    		/**
    		 *	 escrever sua l�gica de autentica��o aqui,
���				�retornar true se a autentica��o foi
���� 			bem-sucedida, caso contr�rio, false
    		 */
    		
    		credentials.setUsername(funcionario.getNome());
    		identity.addRole(funcionario.getNome());
    		return true;
    		
    	}
        return false;
    }

}
