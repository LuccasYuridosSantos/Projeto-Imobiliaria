package br.gov.sp.fatec.pi.imobiliaria.model.vo.response;


import br.gov.sp.fatec.pi.imobiliaria.model.Cliente;
import br.gov.sp.fatec.pi.imobiliaria.model.Corretor;
import br.gov.sp.fatec.pi.imobiliaria.model.Imobiliaria;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AgendamentoResponse {

  private Long id;

  private LocalDateTime dataHora;

  @JsonIgnore
  private String descricao;

  @JsonIgnore
  private Cliente cliente;

  @JsonIgnore
  private ImovelResponse imovel;

  @JsonIgnore
  private Imobiliaria imobiliaria;

  @JsonIgnore
  private Corretor corretor;

}
