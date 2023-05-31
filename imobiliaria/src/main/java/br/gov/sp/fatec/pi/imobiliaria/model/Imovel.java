package br.gov.sp.fatec.pi.imobiliaria.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * A classe Imovel representa um imóvel.
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Imovel {

	/**
	 * O ID do imóvel.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * O endereço do imóvel.
	 */
	@ManyToOne
	@JoinColumn(name = "endereco_id")
	private Endereco endereco;

	/**
	 * O número de quartos do imóvel.
	 */
	private int numQuartos;

	/**
	 * O número de banheiros do imóvel.
	 */
	private int numBanheiros;

	/**
	 * O número de vagas de garagem do imóvel.
	 */
	private int numVagasGaragem;

	/**
	 * A área do imóvel.
	 */
	private BigDecimal area;

	/**
	 * O valor de venda do imóvel.
	 */
	private BigDecimal valorVenda;

	/**
	 * O valor de aluguel do imóvel.
	 */
	private BigDecimal valorAluguel;

	/**
	 * O tipo de imóvel.
	 */
	@Enumerated(EnumType.STRING)
	private TipoImovel tipoImovel;

	/**
	 * O proprietário do imóvel.
	 */
	@ManyToOne
	@JoinColumn(name = "proprietario_id")
	private Cliente proprietario;

	/**
	 * A imobiliária responsável pelo imóvel.
	 */
	@ManyToOne
	@JoinColumn(name = "imobiliaria_id")
	private Imobiliaria imobiliaria;

	/**
	 * O locatário do imóvel.
	 */
	@ManyToOne
	@JoinColumn(name = "locatario_id")
	private Cliente locatario;

	/**
	 * A lista de imagens do imóvel.
	 */
	@OneToMany
	private List<Imagem> imagens;
}
