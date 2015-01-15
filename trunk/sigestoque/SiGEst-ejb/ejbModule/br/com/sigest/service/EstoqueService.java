package br.com.sigest.service;

import java.util.List;

import javax.ejb.Remove;
import javax.ejb.Stateful;

import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.Destroy;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import br.com.sigest.dao.FornecedorDao;
import br.com.sigest.dao.ProdutoDao;
import br.com.sigest.modelo.Fornecedor;
import br.com.sigest.modelo.Produto;


@Name("estoqueService")
@AutoCreate
@Stateful
public class EstoqueService implements IEstoqueService{

	
	@In
	FornecedorDao fornecedorDao;
	
	@In
	ProdutoDao produtoDao;
	
	
	
	
	public List<Fornecedor> fidAllFornecedor() {
		return fornecedorDao.fidAllFornecedor();
	}
	

	public void salvar(Fornecedor fornecedor) {
		fornecedorDao.salvar(fornecedor);		
	}

	public List<Fornecedor> pesquisarFornecedores(Fornecedor fornecedor) {
		return fornecedorDao.pesquisarFornecedores(fornecedor);
	}
	
	public void excluirFornecedor(Fornecedor fornecedor) {
		fornecedorDao.excluir(fornecedor);
	}
	
	public List<Produto> pesquisarProduto(Produto produto) {
		return produtoDao.pesquisarProduto(produto);
	}
	
	public void salvarProduto(Produto produto) {
	
		produtoDao.salvarProduto(produto);
	}
	
	public void deletarProduto(Produto produto) {
		produtoDao.deletarProduto(produto);
	}
	
	public List<Produto> pesquisarProdutoFornecedor(Fornecedor fornecedor) {
		return produtoDao.pesquisarProdutoFornecedor(fornecedor);
	}
//	public List<Estado> pesquisarTodosEstados() {
//		return fornecedorDao.pesquisarTodosEstados();
//	}
//	
//	public List<Cidade> pesquisarCidadesPorEstados(Estado estado) {
//		return fornecedorDao.pesquisarCidadesPorEstados(estado);
//	}

	@Destroy
	public void destroy() {
		
	}
	
	@Remove
	public void remove() {
		
	}





	
}
