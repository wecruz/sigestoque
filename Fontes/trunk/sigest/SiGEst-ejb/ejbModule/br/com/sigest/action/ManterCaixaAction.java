package br.com.sigest.action;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import br.com.sigest.enums.EnumStatusVenda;
import br.com.sigest.modelo.Cliente;
import br.com.sigest.modelo.Venda;
import br.com.sigest.modelo.Venda_Produto;
import br.com.sigest.service.IVendasService;


@Name("manterCaixaAction")
@AutoCreate
@Scope(ScopeType.CONVERSATION)
public class ManterCaixaAction {

	@In
	private IVendasService vendasService;
	
	
	private List<Venda> listVendas = new ArrayList<Venda>();
	
	private Venda venda = new Venda();
	
	private List<Venda_Produto> listaVendaProduto = new ArrayList<Venda_Produto>();
	
	private Integer qntFuncionarios = 1;
	
	private Cliente cliente = new Cliente();
	
	private List<Cliente> clientes;
	
	@Create
	public String create(){
		listVendas = vendasService.fildAllVendas();
		return "/caixa/caixa.xhtml";
	}

	public void darBaixaVenda(){
		venda.setStatusVenda(EnumStatusVenda.PAGO);
		vendasService.salvaPedidoVenda(venda);
		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"Operação realizada com sucesso.", ""));
		listVendas.remove(venda);
	}
	
	
	
	
	
	
	
	
	
	public List<Cliente> pesquisarVendaClienterNome(Object autoComplete) {

		List<Cliente> listaRetorno = new ArrayList<Cliente>();
		
			String texto = (String) autoComplete;
			List<Cliente> listCliente = fidAllVendaClientePorNome(cliente);

			for (Cliente client : listCliente) {
				String idStr = String.valueOf(client.getId());
				if (client.getNome().toLowerCase().contains(texto.toLowerCase())
						|| idStr.equalsIgnoreCase(texto)) {
					listaRetorno.add(client);
				}
			}
			return listaRetorno;
		}
	
	public List<Cliente> pesquisarVendaClienterCpf(Object autoComplete) {

		List<Cliente> listaRetorno = new ArrayList<Cliente>();
		
			String texto = (String) autoComplete;
			List<Cliente> listCliente = fidAllVendaClientePorNome(cliente);

			for (Cliente client : listCliente) {
				String idStr = String.valueOf(client.getId());
				if (client.getCpf().toLowerCase().contains(texto.toLowerCase())
						|| idStr.equalsIgnoreCase(texto)) {
					listaRetorno.add(client);
				}
			}
			return listaRetorno;
		}
	
	
	public String renderdVendaCliente(Cliente cliente){
		
		this.cliente = cliente;
		listVendas = new ArrayList<Venda>();
		listVendas = vendasService.pesquisarVendasCliente(this.cliente);
		this.cliente = new Cliente();
		
		return "/caixa/caixa.xhtml";
	}
	
	
	@Factory(value="fidAllVendaClientePorNome" , scope=ScopeType.CONVERSATION , autoCreate = true)
	public List<Cliente> fidAllVendaClientePorNome(Cliente cliente){
		setClientes(vendasService.pesquisarClientes(cliente));
		return getClientes();
	}
	
	
	
	public void selecinarVenda(Venda venda){
		this.venda = venda;
		
	}
	
	public void setListVendas(List<Venda> listVendas) {
		this.listVendas = listVendas;
	}

	public List<Venda> getListVendas() {
		return listVendas;
	}


	public IVendasService getVendasService() {
		return vendasService;
	}


	public void setVendasService(IVendasService vendasService) {
		this.vendasService = vendasService;
	}


	public Venda getVenda() {
		return venda;
	}


	public void setVenda(Venda venda) {
		this.venda = venda;
	}



	public List<Venda_Produto> getListaVendaProduto() {
		return listaVendaProduto;
	}



	public void setListaVendaProduto(List<Venda_Produto> listaVendaProduto) {
		this.listaVendaProduto = listaVendaProduto;
	}



	public void setQntFuncionarios(Integer qntFuncionarios) {
		this.qntFuncionarios = qntFuncionarios;
	}



	public Integer getQntFuncionarios() {
		return qntFuncionarios;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	
	
	
	
}
