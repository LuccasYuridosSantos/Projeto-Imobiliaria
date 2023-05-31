package br.gov.sp.fatec.pi.imobiliaria.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A classe ImobiliariaPutRequest representa a solicitação de atualização de uma imobiliária.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImobiliariaPutRequest {

  /**
   * O nome da imobiliária.
   */
  @NotBlank
  private String nome;

  /**
   * O telefone da imobiliária.
   */
  private String telefone;

  /**
   * O email da imobiliária.
   */
  private String email;

  /**
   * O endereço da imobiliária.
   */
  private EnderecoRequest endereco;
}
