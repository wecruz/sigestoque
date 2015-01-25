package br.com.sigest.modelo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.sigest.enums.EnumStatusVenda;

@Entity
@Table(name="tb_vendas")
public class Venda {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id_venda", nullable = false)
	private Long id;
	
	@Column(name ="dataVenda")
	@Temporal(TemporalType.DATE)
	private Date dataVenda;
	
	@Column(name = "st_venda")
	@Enumerated(EnumType.STRING)
	private EnumStatusVenda statusVenda;
	

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



	
	
}
