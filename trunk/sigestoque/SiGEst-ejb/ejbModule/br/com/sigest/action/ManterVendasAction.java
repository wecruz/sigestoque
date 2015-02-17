package br.com.sigest.action;

import java.util.ArrayList;
import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import br.com.sigest.modelo.Cliente;
import br.com.sigest.modelo.Fornecedor;
import br.com.sigest.modelo.Produto;
import br.com.sigest.modelo.Venda;
import br.com.sigest.modelo.VendasClientesDTO;
import br.com.sigest.service.IVendasService;

@Name("manterVendasAction")
@AutoCreate
@Scope(ScopeType.CONVERSATION)
public class ManterVendasAction {

	@In
	private IVendasService vendasService;
	
	private Cliente cliente = new Cliente();
	
	private Venda venda = new Venda();
	
	@Begin(join = true)
	public String manipulaVendas(Cliente cliente) {
		this.cliente = cliente;
		return "/vendas/vendas.xhtml";
	}
	
	private VendasClientesDTO vendasClientesDTO = new VendasClientesDTO();
	
	private List<VendasClientesDTO> listVendasClientesDTO = new ArrayList<VendasClientesDTO>();
	
	
	
	private Produto produto = new Produto(new Fornecedor());
	
	public List<Produto> comboBoxListProduro(){
		List<Produto> produtos = new ArrayList<Produto>();
		if(vendasClientesDTO.getFornecedor() !=null){
			produtos = vendasService.pesquisarProdutoFornecedor(vendasClientesDTO.getFornecedor());
			
		}
		return produtos;
		
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

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setVendasClientesDTO(VendasClientesDTO vendasClientesDTO) {
		this.vendasClientesDTO = vendasClientesDTO;
	}

	public VendasClientesDTO getVendasClientesDTO() {
		return vendasClientesDTO;
	}

	public void setListVendasClientesDTO(List<VendasClientesDTO> listVendasClientesDTO) {
		this.listVendasClientesDTO = listVendasClientesDTO;
	}

	public List<VendasClientesDTO> getListVendasClientesDTO() {
		return listVendasClientesDTO;
	}
}
