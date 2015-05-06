package br.com.sigest.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remove;
import javax.ejb.Stateful;

import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.Destroy;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import br.com.sigest.dao.CategoriaDao;
import br.com.sigest.dao.FornecedorDao;
import br.com.sigest.dao.ProdutoDao;
import br.com.sigest.modelo.Categoria;
import br.com.sigest.modelo.Cidade;
import br.com.sigest.modelo.Estado;
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
	
	@In
	CategoriaDao categoriaDao;
	
	
	
	
	public List<Fornecedor> fidAllFornecedor() {
		return fornecedorDao.fidAllFornecedor();
	}
	
	public void salvarCategoria(Categoria categoria) {
		categoriaDao.salvarCategoria(categoria);
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
	public List<Estado> fidAllEstados() {
		return fornecedorDao.fidAllEstados();
	}
	
	public List<Categoria> pesquisarCategoria(Categoria categoria) {
		return categoriaDao.pesquisarCategoria(categoria);
	}

	
	public List<Categoria> fidAllCategoria() {
		return categoriaDao.fidAllCategoria();
	}
	public void excluirCategoria(Categoria categoria) {
		categoriaDao.excluirCategoria(categoria);		
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


	public List<Cidade> fidAllCidade(Estado estado) {
		List<Cidade> cidades = new ArrayList<Cidade>();
		if(estado.getUf() != null){
		return	cidades = fornecedorDao.fidAllCidades(estado);
		}
		return cidades;
	}













	
}
