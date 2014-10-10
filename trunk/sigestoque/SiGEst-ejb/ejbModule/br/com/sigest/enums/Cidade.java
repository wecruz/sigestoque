/**
 * 
 */
package br.com.sigest.enums;

/**
 * @author rnteixeira
 *
 */
public enum Cidade {
	
	AFONSO_CLAUDIO(1, "Afonso Cláudio", 8),
	AGUA_DOCE_NORTE(2, "Água Doce do Norte", 8),
	AGUIAR_BRANCA(3, "Águia Branca", 8),
	ALEGRE(4, "Alegre", 8),
	ALFREDO_CHAVES(5, "Alfredo Chaves", 8),
	ALTO_RIO_NOVO(6, "Alto Rio Novo", 8),
	AMCHIETA(7, "Anchieta", 8),
	APIACA(8, "Apiacá", 8),
	ARACRUZ(9, "Aracruz", 8),
	ATILIO_VIVACQUA(10, "Atilio Vivacqua", 8);

	private Integer id;
	private String nomeCidade;
	private Integer  estado;
	
	private Cidade(Integer id, String nomeCidade, Integer estado) {
		this.setId(id);
		this.setNomeCidade(nomeCidade);
		this.setEstado(estado);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeCidade() {
		return nomeCidade;
	}

	public void setNomeCidade(String nomeCidade) {
		this.nomeCidade = nomeCidade;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}
	
	
	
}
