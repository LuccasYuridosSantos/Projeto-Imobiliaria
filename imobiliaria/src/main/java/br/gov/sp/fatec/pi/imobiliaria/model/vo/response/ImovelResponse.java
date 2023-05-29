package br.gov.sp.fatec.pi.imobiliaria.model.vo.response;

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

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ImovelResponse {

  private Long id;

 @JsonIgnore
  private Endereco endereco;

  private int numQuartos;

  private int numBanheiros;

  private int numVagasGaragem;

  private BigDecimal area;

  private BigDecimal valorVenda;

  private BigDecimal valorAluguel;

  private TipoImovel tipoImovel;

  @JsonIgnore
  private ClienteResponse proprietario;

  @JsonIgnore
  private ImobiliariaResponse imobiliaria;


  @JsonIgnore
  private ClienteResponse locatario;

  @JsonIgnore
  private List<Imagem> imagens;
}
