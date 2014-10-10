package br.com.sigest.enums;

public enum Estado {

	Acre(1, "Acre"), 
	Alagoas(2, "Alagoas"), 
	Amapa(3, "Amapa"), 
    Amazonas(4, "Amazonas"), 
    Bahia(5, "Bahia"), 
    Ceara(6, "Cear�"), 
    Distrito_Federal(7, "Distrito Federal"), 
    Espirito_Santo(8, "Espirito Santo"), 
    Goias(9, "Goi�s"), 
    Maranhao(10, "Maranh�o"), 
    Mato_Grosso(11, "Mato Grosso"), 
    Mato_Grosso_do_Sul(12, "Mato Grosso do Sul"), 
    Minas_Gerais(13, "Minas Gerais"), 
    Para(14, "Par�"), 
    Paraiba(15, "Para�ba"), 
    Parana(16, "Paran�"), 
    Pernambuco(17, "Pernambuco"), 
    Piaui(18, "Piau�"), 
    Rio_de_Janeiro(19, "Rio de Janeiro"), 
    Rio_Grande_do_Norte(20, "Rio Grande do Norte"), 
    Rio_Grande_do_Sul(21, "Rio Grande do Sul"), 
    Rondonia(22, "Rondonia"), 
    Roraima(23, "Roraima"), 
    Santa_Catarina(24, "Santa Catarina"), 
    Sao_Paulo(25, "S�o Paulo"), 
    Sergipe(26, "Sergipe"), 
    Tocantins(27, "Tocantins");
	
	private Integer id;
	private final String nome;

	private Estado(Integer id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
