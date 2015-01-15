package br.com.sigest.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 * @author Werick Silva
 */

@Entity
@Table(name ="tb_fornecedor")
public class Fornecedor {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id_fornecedor", nullable = false)
	private Long id;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "cnpj")
	private Long cnpj;
	
	@OneToOne(mappedBy = "fornecedor", cascade=CascadeType.ALL)
	@JoinColumn(name="tb_endereco")
	private Endereco endereco;
	
	@OneToMany(mappedBy="fornecedor", cascade=CascadeType.ALL)
	private List<Produto> produtos = new ArrayList<Produto>();
	
	public Fornecedor() {
		super();
	}

	public Fornecedor(Endereco endereco) {
		super();
		this.endereco = endereco;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getCnpj() {
		return cnpj;
	}

	public void setCnpj(Long cnpj) {
		this.cnpj = cnpj;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}



}
