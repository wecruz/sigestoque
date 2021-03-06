package br.com.sigest.action;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.Create;
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
	
	@Create
	public String create(){
		return "/clientes/clientes.xhtml";
	}
	
	
	public void salvarCliente() {

		if (validEmail(cliente.getEndereco().getEmail())) {
//			cliente.setCpf(cliente.getCpf().replaceAll("[^0-9]", ""));
			
			if (indice == null) {
				
				if(vendasService.pesquisarClientePorCpf(cliente.getCpf()) !=null){
					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR,
									"Cliente com o CPF: "
											+ cliente.getCpf()
											+ " � esta cadastrado", ""));
					return ;
				}else{
					listCliente.add(cliente);
					vendasService.salvarCliente(cliente);
					indice = null;
					pesquisarClientes();
					FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"Opera��o realizada com sucesso.", ""));
					cliente = new Cliente(new Endereco());
					return ;
				}
				
				
				
			} else {
				listCliente.set(indice, cliente);
				vendasService.salvarCliente(cliente);
				indice = null;
				pesquisarClientes();
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"Opera��o realizada com sucesso.", ""));
				cliente = new Cliente(new Endereco());
				return ;
			}
			
		}
	}

	public String alterar(Cliente cliente, int indice) {
		setIndice(indice);
		this.cliente = cliente;
		return "/clientes/salvarCliente.xhtml";
	}

	public void selecionarCliente(Cliente cliente) {
		setClienteSelecionado(cliente);
	}

	public void pesquisarClientes() {
		if (validarCriterioPesquisa()) {
			listCliente = new ArrayList<Cliente>();
			listCliente = vendasService.pesquisarClientes(cliente);
			if (listCliente.isEmpty()) {
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"Registro n�o Localizado.", ""));
			}
		}
	}

	public void excluirCliente() {
		listCliente.remove(getClienteSelecionado());
		vendasService.removerCliente(getClienteSelecionado());
		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"Opera��o realizada com sucesso.", ""));
	}

	
	public String novoCadastro(){
		cliente = new Cliente(new Endereco());
		clienteSelecionado = new Cliente(new Endereco());
		
		return "/clientes/salvarCliente.xhtml";
	}
	
	public String cancelar(){
		cliente = new Cliente();
		indice = null;
		listCliente = new ArrayList<Cliente>();
		return "/clientes/clientes.xhtml";
	}
	
	public boolean validarCriterioPesquisa() {
		if (cliente.getNome().isEmpty() && cliente.getCpf().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"Digite um crit�rio de pesquisa.", ""));
			return false;
		} else {
			return true;
		}
	}

	public boolean validEmail(String email) {
	    Pattern p = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$"); 
	    Matcher m = p.matcher(email); 
	    if (m.find()){
	      return true;
	    }
	    else{
	      FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"O email "+email+" e inv�lido", ""));
	      return false;
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
