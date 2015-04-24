package br.com.sigest.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import br.com.sigest.modelo.Fornecedor;
import br.com.sigest.modelo.dto.RelatorioDto;
import br.com.sigest.service.IEstoqueService;
import br.com.sigest.util.RelatorioUtil;

@Name("manterRelatorioAction")
@AutoCreate
@Scope(ScopeType.CONVERSATION)
public class ManterRelatoriosAction {

	@In
	private RelatorioUtil relatorioUtil;

	@In
	private IEstoqueService estoqueService;
	
	
	private RelatorioDto relatorioDto = new RelatorioDto();
	
	private List<RelatorioDto> listRelatorioDto = new ArrayList<RelatorioDto>();
	
	private List<Fornecedor> fornecedores;
	
	private Fornecedor fornecedor = new Fornecedor();
	
	public List<Fornecedor> pesquisarFornecedorNome(Object autoComplete) {

	List<Fornecedor> listaRetorno = new ArrayList<Fornecedor>();
	if(getRelatorioDto()==null){
			return listaRetorno;
	}
		String texto = (String) autoComplete;
		List<Fornecedor> listFornecedor = fidAllFornecedorNome(fornecedor);

		for (Fornecedor forner : listFornecedor) {
			String idStr = String.valueOf(forner.getId());
			if (forner.getNome().toLowerCase().contains(texto.toLowerCase())
					|| idStr.equalsIgnoreCase(texto)) {
				listaRetorno.add(forner);
			}
		}
		return listaRetorno;
	}
	
	@Factory(value="fidAllFornecedorNome" , scope=ScopeType.CONVERSATION , autoCreate = true)
	public List<Fornecedor> fidAllFornecedorNome(Fornecedor fornecedor){
		setFornecedores(estoqueService.pesquisarFornecedores(fornecedor));
		return getFornecedores();
	}
	
	
	public void pesquisarProduto(){
		relatorioDto.setListProduto(estoqueService.pesquisarProdutoFornecedor(relatorioDto.getFornecedor()));
	}
	

	
	
	
	
	public String gerarRelatorio() {

//		if (!relatorioDto.getFornecedor().getNome().isEmpty()) {

			relatorioDto.setListProduto(estoqueService.pesquisarProdutoFornecedor(relatorioDto.getFornecedor()));
			
			listRelatorioDto.add(relatorioDto);

			final Collection<?> list = listRelatorioDto;
			final Map<String, Object> params = new HashMap<String, Object>();

			try {
				return relatorioUtil.imprimir("teste", params, list);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//		}
//		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"Digite um critério de pesquisa.", ""));
		return "";

	}







	public RelatorioDto getRelatorioDto() {
		return relatorioDto;
	}







	public void setRelatorioDto(RelatorioDto relatorioDto) {
		this.relatorioDto = relatorioDto;
	}

	public List<Fornecedor> getFornecedores() {
		return fornecedores;
	}

	public void setFornecedores(List<Fornecedor> fornecedores) {
		this.fornecedores = fornecedores;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public List<RelatorioDto> getListRelatorioDto() {
		return listRelatorioDto;
	}

	public void setListRelatorioDto(List<RelatorioDto> listRelatorioDto) {
		this.listRelatorioDto = listRelatorioDto;
	}



}
