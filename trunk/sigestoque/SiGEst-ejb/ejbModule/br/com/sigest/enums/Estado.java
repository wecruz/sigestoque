package br.com.sigest.enums;

public enum Estado {

	Acre("Acre"), Alagoas("Alagoas"), Amapa("Amapa"), 
    Amazonas("Amazonas"), Bahia("Bahia"), Ceara("Ceará"), Distrito_Federal("Distrito Federal"), 
    Espirito_Santo("Espirito Santo"), Goias("Goiás"), Maranhao("Maranhão"), Mato_Grosso("Mato Grosso"), 
    Mato_Grosso_do_Sul("Mato Grosso do Sul"), Minas_Gerais("Minas Gerais"), 
    Para("Pará"), Paraiba("Paraíba"), Parana("Paraná"), Pernambuco("Pernambuco"), Piaui("Piauí"), 
    Rio_de_Janeiro("Rio de Janeiro"), Rio_Grande_do_Norte("Rio Grande do Norte"), 
    Rio_Grande_do_Sul("Rio Grande do Sul"), Rondonia("Rondonia"), Roraima("Roraima"), 
    Santa_Catarina("Santa Catarina"), Sao_Paulo("São Paulo"), Sergipe("Sergipe"), Tocantins("Tocantins");
	
	
	private final String nome;

	private Estado(String nome) {
		this.nome = nome;
	}
	public String getNome() {
		return nome;
	}	
}
