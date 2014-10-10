package br.com.sigest.service;

import java.util.List;

import javax.ejb.Remove;
import javax.ejb.Stateful;

import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.Destroy;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import br.com.sigest.dao.FornecedorDao;
import br.com.sigest.modelo.Cidade;
import br.com.sigest.modelo.Estado;


@Name("estoqueService")
@AutoCreate
@Stateful
public class EstoqueService implements IEstoqueService{

	
	@In
	FornecedorDao fornecedorDao;
	
	public List<Estado> pesquisarTodosEstados() {
		return fornecedorDao.pesquisarTodosEstados();
	}
	
	public List<Cidade> pesquisarCidadesPorEstados(Estado estado) {
		return fornecedorDao.pesquisarCidadesPorEstados(estado);
	}
	
	@Destroy
	public void destroy() {
		
	}

	@Remove
	public void remove() {
		
	}



}
