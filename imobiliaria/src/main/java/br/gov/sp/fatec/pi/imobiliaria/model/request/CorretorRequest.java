package br.gov.sp.fatec.pi.imobiliaria.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A classe CorretorRequest representa a solicitação de criação de um corretor.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CorretorRequest {

  /**
   * O nome do corretor.
   */
  @NotBlank
  private String nome;

  /**
   * O e-mail do corretor.
   */
  @NotBlank
  private String email;

  /**
   * O telefone do corretor.
   */
  private String telefone;

  /**
   * O código da imobiliária associada ao corretor.
   */
  @NotNull
  private Long codImobiliaria;
}
