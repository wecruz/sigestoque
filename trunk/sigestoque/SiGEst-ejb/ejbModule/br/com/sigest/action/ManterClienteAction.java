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

	private Boolean flagMensagen;
	private boolean flagNovoCadastro;
	private boolean flagPesquisar;
	
	@Create
	public String create(){
		return "/clientes/clientes.xhtml";
	}
	
	
	public void salvarCliente() {

		if (validEmail(cliente.getEndereco().getEmail())) {
			cliente.setCpf(cliente.getCpf().replaceAll("[^0-9]", "")); 
			vendasService.salvarCliente(cliente);
			if (indice == null) {
				listCliente.add(cliente);
			} else {
				listCliente.set(indice, cliente);
			}
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"Operação realizada com sucesso.", ""));
			setFlagMensagen(true);
			cliente = new Cliente(new Endereco());
			indice = null;
		}
	}

	public String alterar(Cliente cliente, int indice) {
		setIndice(indice);
		setFlagNovoCadastro(true);
		setFlagPesquisar(true);
		this.cliente = cliente;
		return "/clientes/clientes.xhtml";
	}

	public void selecionarCliente(Cliente cliente) {
		setClienteSelecionado(cliente);
	}

	public void pesquisarClientes() {
		if (validarCriterioPesquisa()) {
			listCliente = new ArrayList<Cliente>();
			listCliente = vendasService.pesquisarClientes(cliente);
			if (listCliente.isEmpty()) {
				setFlagMensagen(false);
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"Nem um registro encontrado.", ""));
			}
		}
	}

	public void excluirCliente() {
		listCliente.remove(getClienteSelecionado());
		vendasService.removerCliente(getClienteSelecionado());
		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"Operação realizada com sucesso.", ""));
		setFlagMensagen(true);
	}

	
	public String novoCadastro(){
		setFlagNovoCadastro(true);
		setFlagPesquisar(true);
		cliente = new Cliente(new Endereco());
		clienteSelecionado = new Cliente(new Endereco());
		
		return "/clientes/clientes.xhtml";
	}
	
	public String cancelar(){
		cliente = new Cliente();
		indice = null;
		listCliente = new ArrayList<Cliente>();
		setFlagNovoCadastro(false);
		setFlagPesquisar(false);
		return "/clientes/clientes.xhtml";
	}
	
	public boolean validarCriterioPesquisa() {
		if (cliente.getNome().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"Digite um critério de pesquisa.", ""));
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
	      FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"O email "+email+" e valido", ""));
	      setFlagMensagen(false);
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


	public Boolean getFlagMensagen() {
		return flagMensagen;
	}


	public void setFlagMensagen(Boolean flagMensagen) {
		this.flagMensagen = flagMensagen;
	}


	public boolean isFlagNovoCadastro() {
		return flagNovoCadastro;
	}


	public void setFlagNovoCadastro(boolean flagNovoCadastro) {
		this.flagNovoCadastro = flagNovoCadastro;
	}


	public boolean isFlagPesquisar() {
		return flagPesquisar;
	}


	public void setFlagPesquisar(boolean flagPesquisar) {
		this.flagPesquisar = flagPesquisar;
	}
}
