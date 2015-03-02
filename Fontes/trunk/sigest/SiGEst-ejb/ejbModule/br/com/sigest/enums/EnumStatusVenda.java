package br.com.sigest.enums;

public enum EnumStatusVenda {

	PAGO("Pago"),
	NAO_PAGO("N�o Pago");
	
	private final String nome;

	private EnumStatusVenda(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}	
}
