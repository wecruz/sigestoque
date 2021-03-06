package br.com.sigest.service;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remove;
import javax.ejb.Stateful;

import org.domain.sigest.session.Authenticator;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.Destroy;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import br.com.sigest.dao.ClienteDao;
import br.com.sigest.dao.ProdutoDao;
import br.com.sigest.dao.VendaDao;
import br.com.sigest.modelo.Cliente;
import br.com.sigest.modelo.Fornecedor;
import br.com.sigest.modelo.Produto;
import br.com.sigest.modelo.Venda;


@Name("vendasService")
@AutoCreate
@Stateful
@Local
public class VendasService implements IVendasService{

	@In
	ClienteDao clienteDao;

	@In
	ProdutoDao produtoDao;
	
	@In
	VendaDao vendaDao;
	@In
	Authenticator authenticator;
	
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
	
	public List<Produto> pesquisarProdutos(Produto produto) {
		return produtoDao.pesquisarProduto(produto);
	}
	
	
	public void salvaPedidoVenda(Venda venda) {
		venda.setFuncionario(authenticator.getUsuarioLogado());
		vendaDao.salvaPedidoVenda(venda);
	}
	
	public List<Venda> fildAllVendas() {
		return vendaDao.fildAllVendas();
	}
	
	public List<Venda> pesquisarVendasCliente(Cliente cliente) {
		return vendaDao.pesquisarVendasCliente(cliente);
	}
	
	public void excluirPedido(Venda venda) {
		vendaDao.excluirPedido(venda);
		
	}
	
	public List<Venda> pesquisarVendas(Venda venda) {
		return vendaDao.pesquisarVendas(venda);
	}

	public List<Produto> pesquisarProdutoPorCodigo(Integer codigo) {
		return produtoDao.pesquisarProdutoPorCodigo(codigo);
	}
	
	public Cliente pesquisarClientePorCpf(String cpf) {
		return clienteDao.pesquisarClientePorCpf(cpf);
	}
	@Destroy
	public void destroy() {
		
	}
	
	@Remove
	public void remove() {
		
	}



}
