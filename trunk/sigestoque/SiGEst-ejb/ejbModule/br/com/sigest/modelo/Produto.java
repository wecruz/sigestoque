package br.com.sigest.modelo;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="tb_produto")
public class Produto {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id_produto", nullable = false)
	private Long id;
	
	@Column(name = "codigo")
	private BigInteger codigo;
	
	@Column(name = "descricao")
	private String descricao;
	
	@Column(name = "nomeProduto")
	private String nomeProduto;
	
	@Column(name ="quantidade")
	private Integer quantidade;
	
	@Column(name ="quantidadeMini")
	private Integer quantidadeMini;
	
	@Column(name ="precoCusto")
	private Float precoCusto;
	
	@Column(name ="linkImagem")
	private String linkImagem;
	
	@Column(name ="precoVenda")
	private Float precoVenda;

	@Column(name ="dataValidade")
	@Temporal(TemporalType.DATE)
	private Date dataValidade;
	
	@Column(name="categoria")
	private String categoria;
	
	@Column(name = "novaUnidadeMedida")
	private String novaUnidadeMedida;

	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="fornecedor")
	private Fornecedor fornecedor = new Fornecedor();
	
	public Produto() {
		super();
	}
	
	public Produto(Fornecedor fornecedor) {
		super();
		this.fornecedor = fornecedor;
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Integer getQuantidadeMini() {
		return quantidadeMini;
	}

	public void setQuantidadeMini(Integer quantidadeMini) {
		this.quantidadeMini = quantidadeMini;
	}

	public Date getDataValidade() {
		return dataValidade;
	}

	public void setDataValidade(Date dataValidade) {
		this.dataValidade = dataValidade;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getNovaUnidadeMedida() {
		return novaUnidadeMedida;
	}

	public void setNovaUnidadeMedida(String novaUnidadeMedida) {
		this.novaUnidadeMedida = novaUnidadeMedida;
	}

	public String getLinkImagem() {
		return linkImagem;
	}

	public void setLinkImagem(String linkImagem) {
		this.linkImagem = linkImagem;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public Float getPrecoCusto() {
		return precoCusto;
	}

	public void setPrecoCusto(Float precoCusto) {
		this.precoCusto = precoCusto;
	}

	public Float getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(Float precoVenda) {
		this.precoVenda = precoVenda;
	}

	public BigInteger getCodigo() {
		return codigo;
	}

	public void setCodigo(BigInteger codigo) {
		this.codigo = codigo;
	}
	
	
}
