package br.gov.sp.fatec.pi.imobiliaria.model.response;

import br.gov.sp.fatec.pi.imobiliaria.model.Endereco;
import br.gov.sp.fatec.pi.imobiliaria.model.Imagem;
import br.gov.sp.fatec.pi.imobiliaria.model.TipoImovel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * A classe ImovelResponse representa a resposta de um imóvel.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ImovelResponse {

 /**
  * O ID do imóvel.
  */
 private Long id;

 /**
  * O endereço do imóvel.
  */
 @JsonIgnore
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
 private TipoImovel tipoImovel;

 /**
  * O proprietário do imóvel.
  */
 @JsonIgnore
 private ClienteResponse proprietario;

 /**
  * A imobiliária associada ao imóvel.
  */
 @JsonIgnore
 private ImobiliariaResponse imobiliaria;

 /**
  * O locatário do imóvel.
  */
 @JsonIgnore
 private ClienteResponse locatario;

 /**
  * A lista de imagens do imóvel.
  */
 @JsonIgnore
 private List<Imagem> imagens;
}