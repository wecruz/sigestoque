package br.com.sigest.enums;

public enum EnumCategoria {

	ELETRONICOS("Eletr�nicos"),
	LIMPEZA("Limpeza");
	
	private final String nome;

	private EnumCategoria(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}
	
}
