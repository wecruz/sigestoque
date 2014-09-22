package br.com.sigest.enums;

public enum CargoFuncao {

	ADMINISTRADOR("Administrador"),
	FUNCIONARIO("Funcionario");
	
	private final String nome;

	private CargoFuncao(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}	
	
}
