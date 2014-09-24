package br.com.sigest.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.sigest.enums.CargoFuncao;

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
	private Integer rg;
	
	@Column(name = "cpf")
	private Long cpf;
	
	@Column(name = "login")
	private String login;
	
	@Column(name = "senha")
	private String senha;
	
	@Column(name = "endereco")
	private String endereco;
	
	@Column(name = "telefone")
	private Integer telefone;
	
	@Column(name ="celular")
	private Integer celular;
	
	@Column(name = "cargoFuncao")
	@Enumerated(EnumType.STRING)
	private CargoFuncao cargoFuncao;

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

	public Integer getRg() {
		return rg;
	}

	public void setRg(Integer rg) {
		this.rg = rg;
	}

	public Long getCpf() {
		return cpf;
	}

	public void setCpf(Long cpf) {
		this.cpf = cpf;
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

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Integer getTelefone() {
		return telefone;
	}

	public void setTelefone(Integer telefone) {
		this.telefone = telefone;
	}

	public Integer getCelular() {
		return celular;
	}

	public void setCelular(Integer celular) {
		this.celular = celular;
	}

	public CargoFuncao getCargoFuncao() {
		return cargoFuncao;
	}

	public void setCargoFuncao(CargoFuncao cargoFuncao) {
		this.cargoFuncao = cargoFuncao;
	}
	
}
