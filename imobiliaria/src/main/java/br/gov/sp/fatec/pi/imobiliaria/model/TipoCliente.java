package br.gov.sp.fatec.pi.imobiliaria.model;

/**
 * A enumeração TipoCliente representa os diferentes tipos de cliente.
 */
public enum TipoCliente {

	/**
	 * Representa um comprador.
	 */
	COMPRADOR("Comprador"),

	/**
	 * Representa um locatário.
	 */
	LOCATARIO("Locatário"),

	/**
	 * Representa um investidor imobiliário.
	 */
	INVESTIDOR_IMOBILIARIO("Investidor Imobiliário"),

	/**
	 * Representa uma empresa.
	 */
	EMPRESA("Empresa");

	private String descricao;

	private TipoCliente(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * Obtém a descrição do tipo de cliente.
	 *
	 * @return A descrição do tipo de cliente.
	 */
	public String getDescricao() {
		return descricao;
	}
}
