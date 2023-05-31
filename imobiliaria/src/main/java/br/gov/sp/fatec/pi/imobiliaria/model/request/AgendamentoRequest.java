package br.gov.sp.fatec.pi.imobiliaria.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * A classe AgendamentoRequest representa a solicitação de criação de um agendamento.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgendamentoRequest {

  /**
   * A data e hora do agendamento.
   */
  @NotNull
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private LocalDateTime dataHora;

  /**
   * A descrição do agendamento.
   */
  @NotBlank
  private String descricao;

  /**
   * O ID do cliente associado ao agendamento.
   */
  @NotNull
  private Long idCliente;

  /**
   * O ID do imóvel associado ao agendamento.
   */
  @NotNull
  private Long idImovel;

  /**
   * O ID da imobiliária associada ao agendamento.
   */
  private Long idImobiliaria;

  /**
   * O ID do corretor associado ao agendamento.
   */
  private Long idCorretor;
}
