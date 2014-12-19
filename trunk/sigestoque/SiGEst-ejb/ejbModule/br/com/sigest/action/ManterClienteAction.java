package br.com.sigest.action;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import br.com.sigest.modelo.Cliente;
import br.com.sigest.modelo.Endereco;
import br.com.sigest.service.IVendasService;

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

	private List<Cliente> listCliente = new ArrayList<Cliente>();

	private Cliente clienteSelecionado = new Cliente(new Endereco());

	@In
	IVendasService vendasService;

	private Integer qntClientes = 10;

	private Integer indice;

	public void salvarCliente() {
		vendasService.salvarCliente(cliente);
		if (indice == null) {
			listCliente.add(cliente);
		} else {
			listCliente.set(indice, cliente);
		}
		indice = null;
	}

	public void alterar(Cliente cliente, int indice) {
		setIndice(indice);
		this.cliente = cliente;
	}

	public void selecionarCliente(Cliente cliente) {
		setClienteSelecionado(cliente);
	}

	public void pesquisarClientes() {
		if (validarCriterioPesquisa()) {
			listCliente = new ArrayList<Cliente>();
			listCliente = vendasService.pesquisarClientes(cliente);
			if (listCliente.isEmpty()) {
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"Nem um registro encontrado.", ""));
			}
		}
	}

	public void excluirCliente() {
		listCliente.remove(getClienteSelecionado());
		vendasService.removerCliente(getClienteSelecionado());
	}

	public boolean validarCriterioPesquisa() {
		if (cliente.getNome().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Digite um critério de pesquisa.", ""));
			return false;
		} else {
			return true;
		}
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Cliente> getListCliente() {
		return listCliente;
	}

	public void setListCliente(List<Cliente> listCliente) {
		this.listCliente = listCliente;
	}

	public Integer getQntClientes() {
		return qntClientes;
	}

	public void setQntClientes(Integer qntClientes) {
		this.qntClientes = qntClientes;
	}

	public Integer getIndice() {
		return indice;
	}

	public void setIndice(Integer indice) {
		this.indice = indice;
	}

	public Cliente getClienteSelecionado() {
		return clienteSelecionado;
	}

	public void setClienteSelecionado(Cliente clienteSelecionado) {
		this.clienteSelecionado = clienteSelecionado;
	}
}
