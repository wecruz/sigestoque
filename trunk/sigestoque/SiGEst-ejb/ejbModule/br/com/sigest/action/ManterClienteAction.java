package br.com.sigest.action;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import br.com.sigest.modelo.Cliente;
import br.com.sigest.modelo.Endereco;

/**
 * 
 * @author Werick Silva
 *
 */

@Name("manterClienteAction")
@AutoCreate
@Scope(ScopeType.CONVERSATION)
public class ManterClienteAction {

	
	private Cliente cliente = new Cliente(new Endereco());
	
	
	
	
	
	
	

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
}
