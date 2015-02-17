package br.com.sigest.modelo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="tb_cliente")
public class Cliente {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id_cliente", nullable = false)
	private Long id;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "rg")
	private Integer rg;
	
	@Column(name = "cpf")
	private String cpf;
	
	@OneToOne(mappedBy="cliente", cascade=CascadeType.ALL)
	@JoinColumn(name="tb_endereco")
	private Endereco endereco = new Endereco();
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="tb_venda_cliente", joinColumns={@JoinColumn(name="vendda_id")}, inverseJoinColumns={@JoinColumn(name="cliente_id")})
	private List<Venda> vendas;
	
	
	
	public Cliente() {
		super();
	}
	
	public Cliente(Endereco endereco) {
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

	public Integer getRg() {
		return rg;
	}

	public void setRg(Integer rg) {
		this.rg = rg;
	}
	
	

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public void setVendas(List<Venda> vendas) {
		this.vendas = vendas;
	}

	public List<Venda> getVendas() {
		return vendas;
	}

	

}
