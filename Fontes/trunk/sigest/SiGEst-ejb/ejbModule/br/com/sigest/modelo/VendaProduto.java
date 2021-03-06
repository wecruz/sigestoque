package br.com.sigest.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_venda_Produto")
public class VendaProduto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_venda_produto", nullable = false)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "produto_id")
	private Produto produto;
	
	@ManyToOne
	@JoinColumn(name = "venda_id")
	private Venda venda;
	
	@Column(name ="valorUnitario")
	private Float valorUnitario;
	
	@Column(name ="quantidadeProduto")
	private Integer quantidadeProduto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public void setValorUnitario(Float valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public Float getValorUnitario() {
		return valorUnitario;
	}

	public void setQuantidadeProduto(Integer quantidadeProduto) {
		this.quantidadeProduto = quantidadeProduto;
	}

	public Integer getQuantidadeProduto() {
		return quantidadeProduto;
	}

	
	
}
