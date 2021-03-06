package br.com.sigest.modelo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.sigest.enums.EnumCargoFuncao;
import br.com.sigest.enums.EnumEstado;

@Entity
@Table(name ="tb_endereco")
public class Endereco {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id_endereco", nullable = false)
	private Long id;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="fornecedor")
	private Fornecedor fornecedor;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="funcionario")
	private Funcionario funcionario;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="cliente")
	private Cliente cliente;
	
	@Column(name = "endereco")
	private String  endereco ;
	
	@Column(name = "cidade")
	private String cidade ;
	
	@Column(name = "codigo")
	private String codigo;
	
	@Column(name = "complemento")
	private String complemento;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "estado")
	@Enumerated(EnumType.STRING)
	private EnumEstado estado;
	
	@Column(name = "telefone")
	private String telefone;
	
	@Column(name = "celular")
	private String celular;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public EnumEstado getEstado() {
		return estado;
	}

	public void setEstado(EnumEstado estado) {
		this.estado = estado;
	}
	
	
	
	
}
