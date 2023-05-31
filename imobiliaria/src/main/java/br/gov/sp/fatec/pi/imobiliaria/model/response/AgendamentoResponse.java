package br.gov.sp.fatec.pi.imobiliaria.model.response;

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

/**
 * A classe AgendamentoResponse representa a resposta de um agendamento.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AgendamentoResponse {

  /**
   * O ID do agendamento.
   */
  private Long id;

  /**
   * A data e hora do agendamento.
   */
  private LocalDateTime dataHora;

  /**
   * O cliente associado ao agendamento.
   */
  @JsonIgnore
  private Cliente cliente;

  /**
   * O imóvel associado ao agendamento.
   */
  @JsonIgnore
  private ImovelResponse imovel;

  /**
   * A imobiliária associada ao agendamento.
   */
  @JsonIgnore
  private Imobiliaria imobiliaria;

  /**
   * O corretor associado ao agendamento.
   */
  @JsonIgnore
  private Corretor corretor;
}
