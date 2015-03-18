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

/**
 * @author Werick Silva
 */

@Entity
@Table(name ="tb_funcionario")
public class Funcionario {

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id_funcionario", nullable = false)
	private Long id;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "rg")
	private String rg;
	
	@Column(name = "cpf")
	private String cpf;
	
	@Column(name = "login")
	private String login;
	
	@Column(name = "senha")
	private String senha;
	
	
	@Column(name = "cargoFuncao")
	@Enumerated(EnumType.STRING)
	private EnumCargoFuncao cargoFuncao;
	
	@OneToOne(mappedBy="funcionario", cascade=CascadeType.ALL)
	@JoinColumn(name="tb_endereco")
	private Endereco endereco = new Endereco();
	
	public Funcionario() {
		super();
	}


	public Funcionario(Endereco endereco) {
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

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public EnumCargoFuncao getCargoFuncao() {
		return cargoFuncao;
	}

	public void setCargoFuncao(EnumCargoFuncao cargoFuncao) {
		this.cargoFuncao = cargoFuncao;
	}



	public Endereco getEndereco() {
		return endereco;
	}



	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}


	public String getRg() {
		return rg;
	}


	public void setRg(String rg) {
		this.rg = rg;
	}


	public String getCpf() {
		return cpf;
	}


	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	
	
}
