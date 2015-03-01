package br.com.sigest.util;

import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import br.com.sigest.enums.EnumStatusVenda;
import br.com.sigest.modelo.Fornecedor;
import br.com.sigest.service.IEstoqueService;

@Name("comboBoxHelper")
@AutoCreate
@Scope(ScopeType.CONVERSATION)
public class ComboBoxHelper {

	@In
	private IEstoqueService estoqueService;
	
	@Factory(value="listStatusVenda" , scope=ScopeType.APPLICATION)
	public EnumStatusVenda[] initStatusVenda(){
		return EnumStatusVenda.values();
	}
	
	@Factory(value="fidAllFornecedor" , autoCreate = true)
	public List<Fornecedor> initFornecedor(){
		return estoqueService.fidAllFornecedor();
	}
	
}
