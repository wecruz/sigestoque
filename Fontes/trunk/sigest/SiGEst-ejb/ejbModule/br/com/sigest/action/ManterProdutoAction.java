package br.com.sigest.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.jboss.beans.metadata.api.annotations.Create;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;

import br.com.sigest.modelo.Categoria;
import br.com.sigest.modelo.Fornecedor;
import br.com.sigest.modelo.Produto;
import br.com.sigest.modelo.UploadedFile;
import br.com.sigest.service.IEstoqueService;
import br.com.sigest.util.UploadFileUtil;

/**
 * 
 * @author werick silva
 *
 */

@Name("manterProdutoAction")
@AutoCreate
@Scope(ScopeType.CONVERSATION)
public class ManterProdutoAction {
	
	@In
	IEstoqueService estoqueService;

	private Produto produto = new Produto(new Fornecedor());
	
	private Categoria categoria = new Categoria();
	
//	
//	@In
//	private List<Fornecedor> listFornecedor;
//	
	private List<Produto> listProdutos = new ArrayList<Produto>();

	
	private Produto produtoSelecionado = new Produto(new Fornecedor());
	
	private Integer indice;

	
	private UploadedFile file;
	
	@In(required = false)
	private UploadFileUtil fileUtil;

	private List<Fornecedor> fornecedores;
	
	private Integer qntProdutos = 10;
	
	
	@Create
	public String create(){
		return "/produtos/produtos.xhtml";
	}
	
	
	public void uploadFileImagem(UploadEvent event) throws Exception {

		UploadItem item = event.getUploadItem();

		this.file = new UploadedFile();
		this.file.setDados(fileToByteArray(item.getFile()));
		this.file.setNome(item.getFileName());
		this.file.setMime(item.getContentType());
		this.file.setCaminho(item.getFile().getPath());
		this.file.setTamanho(item.getFile().length());
	}
	
	public String removerFileUpload(){
		file = new UploadedFile();
		return "salvarProdutos";
	}
	
	public void salvarCategoria(){
		estoqueService.salvarCategoria(categoria);
		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"Operação realizada com sucesso.", ""));
	}
	
	public void salvarProduto() {
	
		if (indice != null) {
			listProdutos.set(indice, produto);
			salveProduto();
		} else {
			if(estoqueService.pesquisarProdutoPorCodigo(produto.getCodigo()).isEmpty()){
				listProdutos.add(produto);
				salveProduto();
			}else{
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"Produto já Cadastrada!", ""));
			}
		}
		
	}


	private void salveProduto() {
		UploadedFile upFile = new UploadedFile();
		
			try {

				fileUtil = new UploadFileUtil();
				if(file.getMime() !=null  && file.getCaminho() != null){
					upFile = fileUtil.salvarArquivo(file, null);					
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		if(upFile.getCaminho() !=null){
			produto.setLinkImagem(upFile.getCaminho());			
		}
		estoqueService.salvarProduto(produto);
		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"Operação realizada com sucesso.", ""));
		produto = new Produto(new Fornecedor());
		file = new UploadedFile();
		fileUtil = new UploadFileUtil();
		setIndice(null);
	}
	
	public void pesquisarProduto(){
		if(validarCriterioPesquisa()){
			listProdutos = new ArrayList<Produto>();
			listProdutos = estoqueService.pesquisarProduto(produto);
			if(listProdutos.isEmpty()){
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"Registro não Localizado.", ""));
			}			
		}
	}
	
	public String alterar(Produto produto, int indice) {
		this.indice = indice;
		this.produto = produto;
		file = new UploadedFile();

		try {
			if(produto.getLinkImagem() != null){
				file.setDados(fileToByteArray(new File(produto.getLinkImagem())));				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "salvarProdutos";
	}
	
	public void selecionarProduto(Produto produto){
		setProdutoSelecionado(produto);
		
	}
	
	public void excluirProduto(){
		listProdutos.remove(produtoSelecionado);
		estoqueService.deletarProduto(produtoSelecionado);
		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"Operação realizada com sucesso.", ""));
		produto = new Produto();
	}
	
	public String novoCadastro(){
		produto = new Produto(new Fornecedor());
		produtoSelecionado = new Produto(new Fornecedor());
		file = new UploadedFile();
		fileUtil = new UploadFileUtil();
		return "/produtos/salvarProdutos.xhtml";
	}
	
	
	public Boolean validarCriterioPesquisa(){
		if (produto.getNomeProduto().isEmpty()
				&& produto.getCodigo() == null
				&& produto.getCategoria() == null
				&& produto.getFornecedor() == null) {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"Digite um critério de pesquisa.", ""));
			return false;
		}else{
			return true;
		}
	}
	
	public String cancelar(){
		produto = new Produto(new Fornecedor());
		listProdutos = new ArrayList<Produto>();
		file = new UploadedFile();
		fileUtil = new UploadFileUtil();
		return "/produtos/produtos.xhtml";
	}
	
	
	public byte[] fileToByteArray(File file) throws IOException {
		InputStream is = new FileInputStream(file);
		long length = file.length();
		byte[] bytes = new byte[(int) length];
		int offset = 0, n = 0;
		while (offset < bytes.length
				&& (n = is.read(bytes, offset, bytes.length - offset)) >= 0) {
			offset += n;
		}
		is.close();
		return bytes;
	}
	
	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Produto> getListProdutos() {
		return listProdutos;
	}

	public void setListProdutos(List<Produto> listProdutos) {
		this.listProdutos = listProdutos;
	}


	public Integer getIndice() {
		return indice;
	}

	public void setIndice(Integer indice) {
		this.indice = indice;
	}


	public Produto getProdutoSelecionado() {
		return produtoSelecionado;
	}


	public void setProdutoSelecionado(Produto produtoSelecionado) {
		this.produtoSelecionado = produtoSelecionado;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public List<Fornecedor> getFornecedores() {
		return fornecedores;
	}


	public void setFornecedores(List<Fornecedor> fornecedores) {
		this.fornecedores = fornecedores;
	}


	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}


	public Categoria getCategoria() {
		return categoria;
	}


	public void setQntProdutos(Integer qntProdutos) {
		this.qntProdutos = qntProdutos;
	}


	public Integer getQntProdutos() {
		return qntProdutos;
	}


	
	

}
