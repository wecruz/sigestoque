package br.com.sigest.service;

import java.util.List;

import javax.ejb.Remove;
import javax.ejb.Stateful;

import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.Destroy;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import br.com.sigest.dao.ClienteDao;
import br.com.sigest.dao.ProdutoDao;
import br.com.sigest.modelo.Cliente;
import br.com.sigest.modelo.Fornecedor;
import br.com.sigest.modelo.Produto;


@Name("vendasService")
@AutoCreate
@Stateful
public class VendasService implements IVendasService{

	@In
	ClienteDao clienteDao;

	@In
	ProdutoDao produtoDao;
	
	public void salvarCliente(Cliente cliente) {
		cliente.getEndereco().setCliente(cliente);
		clienteDao.salvarCliente(cliente);
	
	}
	
	public List<Produto> pesquisarProdutoFornecedor(Fornecedor fornecedor) {
		return produtoDao.pesquisarProdutoFornecedor(fornecedor);
	}
	
	public List<Produto> fildAllProduto() {
		return produtoDao.fildAllProduto();
	}
	
	public void removerCliente(Cliente cliente) {
		clienteDao.removerCliente(cliente);
	}
	
	public List<Cliente> pesquisarClientes(Cliente cliente) {
		return clienteDao.pesquisarClientes(cliente);
	}
	
	@Destroy
	public void destroy() {
		
	}
	
	@Remove
	public void remove() {
		
	}









}
