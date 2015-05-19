package br.com.sigest.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import br.com.sigest.enums.EnumStatusVenda;
import br.com.sigest.modelo.Cliente;
import br.com.sigest.modelo.Fornecedor;
import br.com.sigest.modelo.Produto;
import br.com.sigest.modelo.Venda;
import br.com.sigest.modelo.Venda_Produto;
import br.com.sigest.modelo.VendasClientesDTO;
import br.com.sigest.modelo.dto.PedidoDTO;
import br.com.sigest.service.IEstoqueService;
import br.com.sigest.service.IVendasService;
import br.com.sigest.util.RelatorioUtil;

@Name("manterVendasAction")
@AutoCreate
@Scope(ScopeType.CONVERSATION)
public class ManterVendasAction {

	@In
	private IVendasService vendasService;
	
	@In
	IEstoqueService estoqueService;
	
	private Cliente cliente = new Cliente();
	
	private List<Cliente> clientes;
	
	private List<Produto> produtos;
	
	private Venda venda = new Venda();
	
	private Float valorTotal = 0F ;
	
	@In
	private RelatorioUtil relatorioUtil;
	
	private List<PedidoDTO> listPedidoDTO = new ArrayList<PedidoDTO>();
	
	
	
	private PedidoDTO pedidoDTO = new PedidoDTO();

	private Venda_Produto venda_Produto = new Venda_Produto();
	
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
		
		produtos = vendasService.fildAllProduto();
			
		return produtos;
		
	}
	
	public void adicionarProduto(){
		valorTotal = 0F;
		
		float valorPorProduto  = vendasClientesDTO.getQuantidadeProduto() * vendasClientesDTO.getProduto().getPrecoVenda();
		
		venda_Produto.setQuantidadeProduto(vendasClientesDTO.getQuantidadeProduto());
		venda_Produto.setValorUnitario(valorPorProduto);
		venda_Produto.setProduto(vendasClientesDTO.getProduto());
		vendasClientesDTO.getListVendaProduto().add(venda_Produto);
		
		for (Venda_Produto venda_Produto : vendasClientesDTO.getListVendaProduto()) {
			valorTotal += venda_Produto.getValorUnitario();
		}
		
		vendasClientesDTO.setProduto(new Produto());
		venda_Produto = new Venda_Produto();
		
		
	}
	
	
	public void confimarPedidoVenda(){
		
		venda.setCliente(cliente);
		venda.setDataVenda(new Date());
		venda.setStatusVenda(EnumStatusVenda.NAO_PAGO);
		
		venda.getVenda_Produtos().addAll(vendasClientesDTO.getListVendaProduto());
		venda.setValorTotalVenda(valorTotal);
		
		for (Venda_Produto vendaProdut : vendasClientesDTO.getListVendaProduto()) {
			vendaProdut.setVenda(venda);
			
			int quantidadePrduto = vendaProdut.getProduto().getQuantidade();
			int quantidadePrdutoVendido = vendaProdut.getQuantidadeProduto();
			int quantidaReturn = quantidadePrduto -= quantidadePrdutoVendido;
			
			vendaProdut.getProduto().setQuantidade(quantidaReturn);
			
			estoqueService.salvarProduto(vendaProdut.getProduto());
			
		}
		
		vendasService.salvaPedidoVenda(venda);
		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"Operação realizada com sucesso.", ""));
		
		cliente = new Cliente();
		venda = new Venda();
		venda_Produto = new Venda_Produto();
		vendasClientesDTO = new VendasClientesDTO();
		
	}
	
	public List<Cliente> pesquisarClienterNome(Object autoComplete) {

		List<Cliente> listaRetorno = new ArrayList<Cliente>();
		
			String texto = (String) autoComplete;
			List<Cliente> listCliente = fidAllClientePorNome(cliente);

			for (Cliente client : listCliente) {
				String idStr = String.valueOf(client.getId());
				if (client.getNome().toLowerCase().contains(texto.toLowerCase())
						|| idStr.equalsIgnoreCase(texto)) {
					listaRetorno.add(client);
				}
			}
			return listaRetorno;
		}
	
	public List<Cliente> pesquisarClienterCpf(Object autoComplete) {

		List<Cliente> listaRetorno = new ArrayList<Cliente>();
		
			String texto = (String) autoComplete;
			List<Cliente> listCliente = fidAllClientePorNome(cliente);

			for (Cliente client : listCliente) {
				String idStr = String.valueOf(client.getId());
				if (client.getCpf().toLowerCase().contains(texto.toLowerCase())
						|| idStr.equalsIgnoreCase(texto)) {
					listaRetorno.add(client);
				}
			}
			return listaRetorno;
		}
	
	
	
	public List<Produto> pesquisarProdutoNome(Object autoComplete) {

		List<Produto> listaRetorno = new ArrayList<Produto>();
		
			String texto = (String) autoComplete;
			List<Produto> listProduto = fidAllProdutoPorNome(vendasClientesDTO.getProduto());

			for (Produto produt : listProduto) {
				String idStr = String.valueOf(produt.getId());
				if (produt.getNomeProduto().toLowerCase().contains(texto.toLowerCase())
						|| idStr.equalsIgnoreCase(texto)) {
					listaRetorno.add(produt);
				}
			}
			return listaRetorno;
		}
	
	@Factory(value="fidAllClientePorNome" , scope=ScopeType.CONVERSATION , autoCreate = true)
	public List<Cliente> fidAllClientePorNome(Cliente cliente){
		setClientes(vendasService.pesquisarClientes(cliente));
		return getClientes();
	}
	
	@Factory(value="fidAllClientePorCpf" , scope=ScopeType.CONVERSATION , autoCreate = true)
	public List<Cliente> fidAllClientePorCpf(Cliente cliente){
		setClientes(vendasService.pesquisarClientes(cliente));
		return getClientes();
	}
	
	@Factory(value="fidAllProdutoPorNome" , scope=ScopeType.CONVERSATION , autoCreate = true)
	public List<Produto> fidAllProdutoPorNome(Produto produto){
		setProdutos(vendasService.pesquisarProdutos(produto));
		return getProdutos();
	}
	
	
	public void alterarProduto(Venda_Produto venda_Produto){
		vendasClientesDTO.setProduto(venda_Produto.getProduto());
		
	}
	
	public String gerarRelatorio() {
		
			final Collection<?> list = listPedidoDTO ;
			final Map<String, Object> params = new HashMap<String, Object>();
			pedidoDTO.setCliente(cliente);
			listPedidoDTO.add(pedidoDTO);
		
			
			try {
				return relatorioUtil.imprimir("pedido_de_vendas", params, list);
			} catch (Exception e) {
		
				e.printStackTrace();
			}
		return "";

	}
	
	public void renderdCliente(Cliente cliente){
		this.cliente = cliente;
	}
	
	public void renderdProduto(Produto produto){
		this.vendasClientesDTO.setProduto(produto);
	}
	
	public String limpar(){
		this.cliente = new Cliente();
		return "/vendas/vendas.xhtml";
	}
	
	public String removerProduto(Venda_Produto venda_Produto){
		valorTotal = 0f;
		
		vendasClientesDTO.getListVendaProduto().remove(venda_Produto);
		
		for (Venda_Produto vendaPro : vendasClientesDTO.getListVendaProduto()) {
			valorTotal += vendaPro.getValorUnitario();
		}
		return "/vendas/vendas.xhtml";
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

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setValorTotal(Float valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Float getValorTotal() {
		return valorTotal;
	}

	public void setListPedidoDTO(List<PedidoDTO> listPedidoDTO) {
		this.listPedidoDTO = listPedidoDTO;
	}

	public List<PedidoDTO> getListPedidoDTO() {
		return listPedidoDTO;
	}

	public void setPedidoDTO(PedidoDTO pedidoDTO) {
		this.pedidoDTO = pedidoDTO;
	}

	public PedidoDTO getPedidoDTO() {
		return pedidoDTO;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setVenda_Produto(Venda_Produto venda_Produto) {
		this.venda_Produto = venda_Produto;
	}

	public Venda_Produto getVenda_Produto() {
		return venda_Produto;
	}

	
}
