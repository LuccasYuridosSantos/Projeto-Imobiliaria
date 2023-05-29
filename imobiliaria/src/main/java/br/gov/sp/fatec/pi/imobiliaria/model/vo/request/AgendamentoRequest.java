package br.gov.sp.fatec.pi.imobiliaria.model.vo.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgendamentoRequest {

  @NotNull
  private LocalDateTime dataHora;

  @NotBlank
  private String descricao;

  @NotNull
  private Long idCliente;

  @NotNull
  private Long idImovel;

  private Long idImobiliaria;

  private Long idCorretor;


}
