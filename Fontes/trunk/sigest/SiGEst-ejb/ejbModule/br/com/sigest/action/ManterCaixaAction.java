package br.com.sigest.action;

import java.util.ArrayList;
import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import br.com.sigest.modelo.Venda;
import br.com.sigest.service.IVendasService;


@Name("manterCaixaAction")
@AutoCreate
@Scope(ScopeType.CONVERSATION)
public class ManterCaixaAction {

	@In
	private IVendasService vendasService;
	
	
	private List<Venda> listVendas = new ArrayList<Venda>();
	
	@Create
	public String create(){
		listVendas = vendasService.fildAllVendas();
		return "/caixa/caixa.xhtml";
	}

	
	public void setListVendas(List<Venda> listVendas) {
		this.listVendas = listVendas;
	}

	public List<Venda> getListVendas() {
		return listVendas;
	}
	
}
