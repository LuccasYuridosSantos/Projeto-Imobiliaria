package br.gov.sp.fatec.pi.imobiliaria.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A classe EnderecoRequest representa a solicitação de um endereço.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EnderecoRequest {

  /**
   * A rua do endereço.
   */
  @NotBlank
  @Size(max = 128)
  private String rua;

  /**
   * O número do endereço.
   */
  @NotNull
  private Long numero;

  /**
   * O complemento do endereço.
   */
  @NotBlank
  @Size(max = 128)
  private String complemento;

  /**
   * O bairro do endereço.
   */
  @NotBlank
  @Size(max = 128)
  private String bairro;

  /**
   * A cidade do endereço.
   */
  @NotBlank
  @Size(max = 128)
  private String cidade;

  /**
   * O estado do endereço.
   */
  @Size(max = 2, message = "ESTADO inválido, ex: SP")
  private String estado;

  /**
   * O CEP do endereço.
   */
  @Size(max = 9, message = "CEP inválido")
  private String cep;
}
