package br.com.sigest.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name ="tb_estados")
public class Estado {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private int id;
	
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "uf")
	private String uf;
	
	@OneToMany(mappedBy="estado", cascade=CascadeType.ALL)
	private List<Cidade> cidade = new ArrayList<Cidade>();
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public void setCidade(List<Cidade> cidade) {
		this.cidade = cidade;
	}

	public List<Cidade> getCidade() {
		return cidade;
	}

//	public Pais getPais() {
//		return pais;
//	}
//
//	public void setPais(Pais pais) {
//		this.pais = pais;
//	}
}
