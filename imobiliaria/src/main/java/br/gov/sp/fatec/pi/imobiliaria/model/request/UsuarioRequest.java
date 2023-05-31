package br.gov.sp.fatec.pi.imobiliaria.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * A classe UsuarioRequest representa a solicitação de criação de um usuário.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UsuarioRequest {

  /**
   * O nome do usuário.
   */
  @NotBlank
  @JsonProperty
  private String name;

  /**
   * O nome de usuário do usuário.
   */
  @NotBlank
  @JsonProperty
  private String username;

  /**
   * A senha do usuário.
   */
  @NotBlank
  @JsonProperty
  private String password;

  /**
   * As autoridades do usuário.
   */
  private String authorities;

  /**
   * A solicitação de imobiliária associada ao usuário.
   */
  private ImobiliariaRequest imobiliaria;

  /**
   * A solicitação de cliente associada ao usuário.
   */
  private ClienteRequest cliente;
}
