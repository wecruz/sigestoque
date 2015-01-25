package br.com.sigest.action;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import br.com.sigest.enums.EnumStatusVenda;
import br.com.sigest.modelo.Cliente;
import br.com.sigest.modelo.Venda;

@Name("manterVendasAction")
@AutoCreate
@Scope(ScopeType.CONVERSATION)
public class ManterVendasAction {

	private Cliente cliente;
	
	private Venda venda = new Venda();
	
	@Begin(join = true)
	public String manipulaVendas(Cliente cliente) {
		this.cliente = cliente;
		return "/vendas/vendas.xhtml";
	}
	
	
	@Factory(value="listStatusVenda" , scope=ScopeType.APPLICATION)
	public EnumStatusVenda[] initStatusVenda(){
		
		return EnumStatusVenda.values();
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public Venda getVenda() {
		return venda;
	}
}
