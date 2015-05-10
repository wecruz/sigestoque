package br.com.sigest.modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.sigest.enums.EnumStatusVenda;

@Entity
@Table(name = "tb_venda")
public class Venda {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_venda", nullable = false)
	private Long id;

	@Column(name = "dataVenda")
	@Temporal(TemporalType.DATE)
	private Date dataVenda;

	@Column(name = "st_venda")
	@Enumerated(EnumType.STRING)
	private EnumStatusVenda statusVenda;

	@OneToOne
	@JoinColumn(name="cliente")
	private Cliente cliente;
	
	@OneToOne
	@JoinColumn(name="funcionario")
	private Funcionario funcionario;

	@OneToMany(mappedBy = "venda", cascade = CascadeType.ALL)
	private List<Venda_Produto> venda_Produtos = new ArrayList<Venda_Produto>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(Date dataVenda) {
		this.dataVenda = dataVenda;
	}

	public EnumStatusVenda getStatusVenda() {
		return statusVenda;
	}

	public void setStatusVenda(EnumStatusVenda statusVenda) {
		this.statusVenda = statusVenda;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public List<Venda_Produto> getVenda_Produtos() {
		return venda_Produtos;
	}

	public void setVenda_Produtos(List<Venda_Produto> venda_Produtos) {
		this.venda_Produtos = venda_Produtos;
	}
	
	

}
