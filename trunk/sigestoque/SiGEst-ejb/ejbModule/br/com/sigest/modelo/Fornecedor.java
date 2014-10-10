package br.com.sigest.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.sigest.enums.Cidade;
import br.com.sigest.enums.Estado;


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
	
	@Column(name = "codigo")
	private String codigo;
	
	@Column(name = "nomeFantasia")
	private String nomefantasia;
	
	@Column(name = "cnpj")
	private Long cnpj;
	
	@Column(name = "endereco")
	private String endereco;
	
	@Column(name = "complemento")
	private String complemento; 
	
	@Column(name = "cidade")
	@Enumerated(EnumType.STRING)
	private Cidade cidade;

	@Column(name = "telefone")
	private Integer telefone;
	
	@Column(name = "estado")
	@Enumerated(EnumType.STRING)
	private Estado estado;

	@Column(name = "email")
	private String email;
	
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

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNomefantasia() {
		return nomefantasia;
	}

	public void setNomefantasia(String nomefantasia) {
		this.nomefantasia = nomefantasia;
	}

	public Long getCnpj() {
		return cnpj;
	}

	public void setCnpj(Long cnpj) {
		this.cnpj = cnpj;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getComplemento() {
		return complemento;
	}

	

	public Integer getTelefone() {
		return telefone;
	}

	public void setTelefone(Integer telefone) {
		this.telefone = telefone;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
	
	
}
