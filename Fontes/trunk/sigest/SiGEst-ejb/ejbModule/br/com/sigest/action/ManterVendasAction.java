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

import com.sun.net.httpserver.Authenticator;

import br.com.sigest.enums.EnumStatusVenda;
import br.com.sigest.modelo.Cliente;
import br.com.sigest.modelo.Fornecedor;
import br.com.sigest.modelo.Produto;
import br.com.sigest.modelo.Venda;
import br.com.sigest.modelo.VendaProduto;
import br.com.sigest.modelo.VendasClientesDTO;
import br.com.sigest.modelo.dto.PedidoDTO;
import br.com.sigest.service.IEstoqueService;
import br.com.sigest.service.IVendasService;
import br.com.sigest.service.UsuarioService;
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
	
	private Venda vendaSelecionada = new Venda();
	
	private boolean flagRenderProduto;
	
	private PedidoDTO pedidoDTO = new PedidoDTO();

	private VendaProduto vendaProduto = new VendaProduto();
	
	private Integer indice;
	
	private Integer qntprodutoAdd = 10;
	
	private Float valorDesconto = 0F ;
	
	
	
	@Begin(join = true)
	public String manipulaVendas(Cliente cliente) {
		this.cliente = cliente;
		return "/vendas/vendas.xhtml";
	}
	
	
	/**
	 * DESCONTO
	 * resultado = (valor*porcentagem)/100;
	 */
	
	private VendasClientesDTO vendasClientesDTO = new VendasClientesDTO();
	
	private List<VendasClientesDTO> listVendasClientesDTO = new ArrayList<VendasClientesDTO>();
	
	
	private List<Venda> listPedidoVenda = new ArrayList<Venda>();
	
	
	private Produto produto = new Produto(new Fornecedor());
	
	public List<Produto> comboBoxListProduro(){
		List<Produto> produtos = new ArrayList<Produto>();
		
		produtos = vendasService.fildAllProduto();
			
		return produtos;
		
	}
	
	public void Calculardesconto(){
		float resultado = (valorTotal *valorDesconto)/100;
		valorTotal = 0f;
		valorTotal = resultado;
	}
	
	public String alterarPedidoVenda(Venda venda){
			cliente = venda.getCliente();
			vendasClientesDTO.setListVendaProduto(venda.getVenda_Produtos());
			valorTotal = venda.getValorTotalVenda();
			this.venda = venda;
		return "/vendas/vendas.xhtml";
	}
	
	public void adicionarProduto(){
		
		if(vendasClientesDTO.getProduto().getNomeProduto() == null ||  vendasClientesDTO.getProduto().getCodigo() == null){
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, "Selecione Um produto." , ""));
		}
		else{
			
			for (VendaProduto vendaProduto: vendasClientesDTO.getListVendaProduto()) {
				if(vendaProduto.getProduto().getId().equals(vendasClientesDTO.getProduto().getId()) && indice == null){
					FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, 
							"O produto " + vendasClientesDTO.getProduto().getNomeProduto() + " Ja esta adicionado" , ""));
					return;
				}
			}
			
				valorTotal = 0F;
				float valorPorProduto  = vendasClientesDTO.getQuantidadeProduto() * vendasClientesDTO.getProduto().getPrecoVenda();
				
				vendaProduto.setQuantidadeProduto(vendasClientesDTO.getQuantidadeProduto());
				vendaProduto.setValorUnitario(valorPorProduto);
				vendaProduto.setProduto(vendasClientesDTO.getProduto());
				if(indice == null){
					vendasClientesDTO.getListVendaProduto().add(vendaProduto);					
				}else{
					vendasClientesDTO.getListVendaProduto().set(indice, vendaProduto);	
				}
				
				for (VendaProduto venda_Produto : vendasClientesDTO.getListVendaProduto()) {
					valorTotal += venda_Produto.getValorUnitario();
				}
				
				vendasClientesDTO.setProduto(new Produto());
				vendaProduto = new VendaProduto();
				indice = null;
			
			
			
		}
		
		
		
	}
	
	
	public void selecionarVenda(Venda venda){
		setVendaSelecionada(venda);
	}
	
	
	public void ecluirPedidoVenda(){
		
		
		for (VendaProduto vendaProdut : vendaSelecionada.getVenda_Produtos()) {
			vendaProdut.setVenda(vendaSelecionada);
			
			int quantidadePrduto = vendaProdut.getProduto().getQuantidade();
			int quantidadePrdutoVendido = vendaProdut.getQuantidadeProduto();
			int quantidaReturn = quantidadePrduto += quantidadePrdutoVendido;
			
			vendaProdut.getProduto().setQuantidade(quantidaReturn);
			
			estoqueService.salvarProduto(vendaProdut.getProduto());
			
		}
		
		
		listPedidoVenda.remove(vendaSelecionada);
		vendasService.excluirPedido(vendaSelecionada);
		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"Operação realizada com sucesso.", ""));
		vendaSelecionada = new Venda();
		venda = new Venda();
	}
	
	public void confimarPedidoVenda(){
		
		if(cliente.getCpf().isEmpty() || cliente.getNome().isEmpty()){
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, "Selecione Um Cliente." , ""));
		}else{
	
		venda.setCliente(cliente);
		venda.setDataVenda(new Date());
		venda.setStatusVenda(EnumStatusVenda.NAO_PAGO);
		
		venda.getVenda_Produtos().addAll(vendasClientesDTO.getListVendaProduto());
		venda.setValorTotalVenda(valorTotal);
		
		Authenticator authenticator;
		
		for (VendaProduto vendaProdut : vendasClientesDTO.getListVendaProduto()) {
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
		vendaProduto = new VendaProduto();
		vendasClientesDTO = new VendasClientesDTO();
		}
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
	
	public List<Produto> pesquisarProdutoCodigo(Object autoComplete) {

		List<Produto> listaRetorno = new ArrayList<Produto>();
		
			String texto = (String) autoComplete;
			List<Produto> listProduto = fidAllProdutoPorCpf(vendasClientesDTO.getProduto().getCodigo());

			for (Produto produt : listProduto) {
				String idStr = String.valueOf(produt.getId());
				if (produt.getNomeProduto().toLowerCase().contains(texto.toLowerCase())
						|| idStr.equalsIgnoreCase(texto)) {
					listaRetorno.add(produt);
				}
			}
			return listaRetorno;
		}
	
	
	
	
	public void pesquisarPedidoVendaCliente(){
		listPedidoVenda = vendasService.pesquisarVendasCliente(cliente);
		if(listPedidoVenda.isEmpty()){
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"Registro não Localizado.", ""));
		}
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
	
	@Factory(value="fidAllProdutoPorCpf" , scope=ScopeType.CONVERSATION , autoCreate = true)
	public List<Produto> fidAllProdutoPorCpf(Integer codigo){
		setProdutos(vendasService.pesquisarProdutoPorCodigo(codigo));
		return getProdutos();
	}
	
	
	public String alterarProduto(VendaProduto vendaProduto, int indice){
		vendasClientesDTO.setProduto(vendaProduto.getProduto());
		this.indice = indice;
		return "/vendas/vendas.xhtml";
		
	}
	
	public String gerarRelatorio(Venda venda) {
		
			final Collection<?> list = listPedidoDTO ;
			final Map<String, Object> params = new HashMap<String, Object>();
			pedidoDTO.setVenda(venda);
			pedidoDTO.setListaVendaProduto(venda.getVenda_Produtos());
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
		
		if(produto.getQuantidade() == 0){
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, 
					"O produto " + produto.getNomeProduto() + " Estar Com estoque 0." , ""));
			setFlagRenderProduto(false);
			return;
		}else{
		setFlagRenderProduto(true);
		this.vendasClientesDTO.setQuantidadeMaxProduto(produto.getQuantidade());
		this.vendasClientesDTO.setProduto(produto);
		}
	}
	
	public String limpar(){
		this.cliente = new Cliente();
		return "/vendas/vendas.xhtml";
	}
	
	public String limparProduto(){
		this.vendasClientesDTO.setProduto(new Produto());
		return "/vendas/vendas.xhtml";
	}
	
	public String removerProduto(VendaProduto vendaProduto, int indice){
		valorTotal = 0f;
		
		vendasClientesDTO.getListVendaProduto().remove(vendaProduto);
		
		for (VendaProduto vendaPro : vendasClientesDTO.getListVendaProduto()) {
			valorTotal += vendaPro.getValorUnitario();
		}
		
		vendasClientesDTO.setProduto(new Produto());
		vendaProduto = new VendaProduto();
		this.indice = null;
		
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

	public void setVenda_Produto(VendaProduto venda_Produto) {
		this.vendaProduto = venda_Produto;
	}

	public VendaProduto getVenda_Produto() {
		return vendaProduto;
	}

	public void setListPedidoVenda(List<Venda> listPedidoVenda) {
		this.listPedidoVenda = listPedidoVenda;
	}

	public List<Venda> getListPedidoVenda() {
		return listPedidoVenda;
	}

	public void setVendaSelecionada(Venda vendaSelecionada) {
		this.vendaSelecionada = vendaSelecionada;
	}

	public Venda getVendaSelecionada() {
		return vendaSelecionada;
	}

	public void setIndice(Integer indice) {
		this.indice = indice;
	}

	public Integer getIndice() {
		return indice;
	}

	public void setFlagRenderProduto(boolean flagRenderProduto) {
		this.flagRenderProduto = flagRenderProduto;
	}

	public boolean isFlagRenderProduto() {
		return flagRenderProduto;
	}

	public void setQntprodutoAdd(Integer qntprodutoAdd) {
		this.qntprodutoAdd = qntprodutoAdd;
	}

	public Integer getQntprodutoAdd() {
		return qntprodutoAdd;
	}

	public void setValorDesconto(Float valorDesconto) {
		this.valorDesconto = valorDesconto;
	}

	public Float getValorDesconto() {
		return valorDesconto;
	}

	
}
