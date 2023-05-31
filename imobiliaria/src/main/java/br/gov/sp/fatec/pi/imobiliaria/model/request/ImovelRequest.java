package br.gov.sp.fatec.pi.imobiliaria.model.request;

import br.gov.sp.fatec.pi.imobiliaria.model.TipoImovel;
import com.fasterxml.jackson.annotation.JsonInclude;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * A classe ImovelRequest representa a solicitação de criação de um imóvel.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ImovelRequest {

	/**
	 * O endereço do imóvel.
	 */
	@NotNull
	private EnderecoRequest endereco;

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
	private TipoImovel tipoImovel;

	/**
	 * O código do proprietário do imóvel.
	 */
	@NotNull
	private Long codProprietario;

	/**
	 * O código da imobiliária do imóvel.
	 */
	private Long codImobiliaria;

	/**
	 * O código do locatário do imóvel.
	 */
	private Long codLocatario;

	/**
	 * A lista de imagens do imóvel.
	 */
	@Valid
	private List<ImagemRequest> imagens;
}
