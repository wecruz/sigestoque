package br.com.sigest.enums;

public enum EnumCargoFuncao {

	ADMINISTRADOR("Administrador"),
	FUNCIONARIO("Funcionario");
	
	private final String nome;

	private EnumCargoFuncao(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}	
	
}
