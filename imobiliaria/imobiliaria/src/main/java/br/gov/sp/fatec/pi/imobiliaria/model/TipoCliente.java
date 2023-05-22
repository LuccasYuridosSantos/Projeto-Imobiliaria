package br.gov.sp.fatec.pi.imobiliaria.model;

public enum TipoCliente {
	
	COMPRADOR("Comprador"), 
	LOCATARIO("Locatário"), 
	INVESTIDOR_IMOBILIARIO("Investidor Imobiliário"),
	EMPRESA("Empresa");

	private String descricao;

	private TipoCliente(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
