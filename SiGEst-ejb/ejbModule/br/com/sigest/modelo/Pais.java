package br.com.sigest.modelo;



//@Entity
//@Table(name ="pais")
public class Pais {

	
//	@Id
//	@GeneratedValue(strategy=GenerationType.AUTO)
//	@Column(name = "id", nullable = false)
	private int id;
	
//	@Column(name = "nome")
	private String nome;
	
//	@Column(name = "sigla")
	private String sigla;

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

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	
	
	
}
