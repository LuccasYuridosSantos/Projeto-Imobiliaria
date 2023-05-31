package br.gov.sp.fatec.pi.imobiliaria.model.vo.request;

import br.gov.sp.fatec.pi.imobiliaria.model.TipoImovel;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ImovelRequest {


	@NotNull
	private EnderecoRequest endereco;

	private int numQuartos;

	private int numBanheiros;

	private int numVagasGaragem;

	private BigDecimal area;

	private BigDecimal valorVenda;

	private BigDecimal valorAluguel;

	private TipoImovel tipoImovel;


	@NotNull
	private Long codProprietario;


	private Long codImobiliaria;


	private Long codLocatario;

	@Valid
	private List<ImagemRequest> imagens;



}

