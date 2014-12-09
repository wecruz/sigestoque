package br.com.sigest.modelo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
	private Endereco endereco = new Endereco();

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
	
	
}
